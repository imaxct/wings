# StudentService

## 用途

`StudentService` 负责学生端的核心业务逻辑，包括：

- 学生信息的查询、创建与更新
- 通过教务系统爬虫自动获取学生信息
- 系统公告的读取
- 学生选课与退课（含复杂的名额控制与事务管理）
- 已选课程列表的查询

该 Service 是学生操作模块的核心，包含最关键的并发选课逻辑。

---

## 接口定义

**文件路径：** `src/main/java/cn/sduonline/wings/service/StudentService.java`

| 方法 | 返回值 | 说明 |
|------|--------|------|
| `getStudentByNo(String stuNo)` | `Student` | 根据学号查询学生 |
| `getStudentByCrawler(String stuNo, String password)` | `Student` | 爬取教务系统获取学生信息 |
| `saveStudent(Student student)` | `Result` | 更新或合并学生信息 |
| `createStudent(Student student)` | `Result` | 创建新学生记录 |
| `getAnnouncement()` | `Result` | 获取系统公告 |
| `getSelectedCourse(Long studentId)` | `Result` | 获取学生已选课程列表 |
| `selectCourse(Student student, Long courseId)` | `Result` | 学生选课 |
| `deselectCourse(Student student, Long courseId)` | `Result` | 学生退课 |
| `getCourseList()` | `Result` | 获取全部可选课程列表 |

---

## 实现方式

**实现类文件路径：** `src/main/java/cn/sduonline/wings/service/impl/StudentServiceImpl.java`

### getStudentByNo

1. 构造 `StudentCondition` 并设置学号。
2. 调用 `studentMapper.selectByCondition()` 查询。
3. 若结果不唯一，返回 `null`；否则返回第一条记录。

### getStudentByCrawler

调用 `AcademyUtil` 工具类，通过 jsoup 向大学教务系统发起 HTTP 请求，使用学号和密码登录并抓取学生基本信息，返回 `Student` 对象。

### saveStudent

1. 调用 `getStudentByNo()` 获取数据库中已有的学生记录。
2. 使用 `BeanUtil.merge()` 将传入对象的非空字段合并到已有记录中。
3. 调用 `studentMapper.updateByPrimaryKeySelective()` 选择性更新。

### createStudent

调用 `studentMapper.insert()` 插入新学生记录，返回受影响行数。

### getAnnouncement

1. 调用 `settingMapper` 按名称 `ANNOUNCEMENT` 查询系统设置。
2. 若该配置不存在，则自动创建默认值 `"无公告"` 并保存。
3. 返回公告内容。

### getSelectedCourse

调用 `selectMapper.selectJoinCourse(studentId)` 执行连表查询，将选课记录与课程信息关联，返回 `List<SelectionVO>`。

### selectCourse

此方法包含最复杂的业务逻辑，使用 `TransactionTemplate` 进行手动事务管理：

1. **选课数量限制校验**：读取 `SELECTION_LIMIT` 设置，统计当前学生已选课程数，超限则拒绝。
2. **课程存在性与名额校验**：使用 `selectByPrimaryKeyForUpdate()` **悲观锁**查询课程，确保并发安全；若课程不存在或 `availableNum <= 0` 则拒绝。
3. **截止时间校验**：若课程设有截止时间且已过期，则拒绝选课。
4. **年级限制校验**：若课程设有年级要求（gradeLimit），且与学生年级不匹配，则拒绝。
5. **贫困生名额分离**：
   - 若学生为贫困生（`isPoor = true`），占用贫困生专属名额（`poorNum`）。
   - 否则，贫困生专属名额不可用于非贫困生（`availableNum - poorNum <= 0` 时拒绝）。
6. **更新课程名额**：调用 `courseMapper.updateByPrimaryKeyWithBLOBs()` 减少可用名额。
7. **创建选课记录**：调用 `selectMapper.insert()` 插入记录。
8. **重复选课处理**：捕获 `DuplicateKeyException`，事务回滚并返回错误提示。

### deselectCourse

使用 `TransactionTemplate` 进行手动事务管理：

1. 查询选课记录，确认该选课存在。
2. 根据学生是否为贫困生，恢复对应名额（`poorNum` 或普通名额）。
3. 调用 `courseMapper.updateByPrimaryKeyWithBLOBs()` 增加可用名额。
4. 调用 `selectMapper.deleteByPrimaryKey()` 删除选课记录。
5. 任何异常均触发事务回滚。

### getCourseList

调用 `courseMapper.selectByCondition()` 查询全部课程并返回。

---

## 依赖

| 依赖 | 类型 | 说明 |
|------|------|------|
| `StudentMapper` | MyBatis Mapper | 学生数据的 CRUD 操作 |
| `SettingMapper` | MyBatis Mapper | 读取系统配置（选课限制、公告等） |
| `SelectMapper` | MyBatis Mapper | 选课记录的查询、插入与删除 |
| `CourseMapper` | MyBatis Mapper | 课程信息查询（含悲观锁） |
| `TransactionTemplate` | Spring 事务 | 手动控制选课/退课事务 |
| `AcademyUtil` | 工具类 | 爬取教务系统获取学生信息 |
| `BeanUtil` | 工具类 | 合并对象非空字段 |
