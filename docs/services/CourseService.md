# CourseService

## 用途

`CourseService` 负责课程信息的管理，面向管理员提供完整的课程 CRUD 操作，包括：

- 查询全部课程列表
- 创建新课程
- 更新课程信息（含名额动态计算）
- 删除课程

该 Service 仅在管理后台使用，负责维护课程基础数据。

---

## 接口定义

**文件路径：** `src/main/java/cn/sduonline/wings/service/CourseService.java`

| 方法 | 返回值 | 说明 |
|------|--------|------|
| `getCourseList()` | `Result` | 查询所有课程 |
| `updateCourse(Course course)` | `Result` | 更新课程信息 |
| `createCourse(Course course)` | `Result` | 创建新课程 |
| `deleteCourse(Course course)` | `Result` | 删除课程 |

---

## 实现方式

**实现类文件路径：** `src/main/java/cn/sduonline/wings/service/impl/CourseServiceImpl.java`

### getCourseList

1. 构造空的 `CourseCondition`（不附加过滤条件）。
2. 调用 `courseMapper.selectByCondition()` 查询全部课程。
3. 将结果封装为 `Result<List<Course>>` 返回。

### updateCourse

1. 调用 `courseMapper.selectByPrimaryKey()` 从数据库取出当前课程信息。
2. **校验容量合法性**：若新设置的总名额（`totalNum`）小于当前已选人数（`totalNum - availableNum`），则返回错误，防止总名额缩减至已选人数以下。
3. **重新计算可用名额**：
   ```
   新可用名额 = 新总名额 - 旧总名额 + 旧可用名额
   ```
   即在原有可用名额基础上叠加容量变化量，保留已选人数不变。
4. 调用 `courseMapper.updateByPrimaryKeyWithBLOBs()` 更新课程（支持 BLOB 字段）。
5. 返回受影响行数。

### createCourse

调用 `courseMapper.insert()` 插入新课程记录，返回受影响行数。

### deleteCourse

调用 `courseMapper.deleteByPrimaryKey()` 按课程 ID 删除，返回受影响行数。

---

## 依赖

| 依赖 | 类型 | 说明 |
|------|------|------|
| `CourseMapper` | MyBatis Mapper | 课程表的全部 CRUD 操作 |
