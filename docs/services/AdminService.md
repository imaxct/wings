# AdminService

## 用途

`AdminService` 负责系统管理员相关的所有后台操作，包括：

- 管理员登录认证
- 管理员登出
- 学生数据的批量导入
- 学生信息的分页查询与更新

该 Service 是后台管理模块的核心入口，仅供管理员角色使用。

---

## 接口定义

**文件路径：** `src/main/java/cn/sduonline/wings/service/AdminService.java`

| 方法 | 返回值 | 说明 |
|------|--------|------|
| `login(String username, String password)` | `Result` | 管理员登录验证 |
| `getAdminByUsername(String username)` | `Admin` | 根据用户名查询管理员 |
| `logout()` | `Result` | 管理员登出 |
| `importStudent(List<Student> students)` | `Result` | 批量导入学生数据 |
| `getStudent(int pageNum, int pageSize)` | `Result` | 分页获取学生列表 |
| `updateStudent(Student student)` | `Result` | 更新学生信息 |

---

## 实现方式

**实现类文件路径：** `src/main/java/cn/sduonline/wings/service/impl/AdminServiceImpl.java`

### login

1. 通过 `AdminCondition` 构造查询条件，调用 `adminMapper` 按用户名查询管理员。
2. 若查询结果为空或结果数量不唯一，返回错误提示。
3. 比对传入密码与数据库中存储的密码是否一致。
4. 验证通过后返回包含管理员信息的 `Result` 对象。

### getAdminByUsername

1. 构造 `AdminCondition` 并设置用户名。
2. 调用 `adminMapper.selectByCondition()` 执行查询。
3. 若结果列表大小不等于 1，返回 `null`；否则返回第一条记录。

### logout

当前为桩实现（stub），返回 `null`，实际登出逻辑由 Apache Shiro 框架处理。

### importStudent

1. 调用 `studentMapper.deleteAll()` 删除所有现有学生数据。
2. 调用 `selectMapper.deleteAll()` 删除所有选课记录（保证数据一致性）。
3. 将学生列表按每批 50 条进行分批，调用 `studentMapper.insertBatch()` 批量插入。
4. 返回包含导入统计信息（总数、成功数等）的 `Result` 对象。

### getStudent

1. 使用 `PageHelper.startPage(pageNum, pageSize)` 开启分页。
2. 调用 `studentMapper.selectAll()` 查询全部学生。
3. 将结果封装为 `PageInfo<Student>` 并返回。

### updateStudent

1. 调用 `studentMapper.updateByPrimaryKeySelective()` 按主键选择性更新学生字段。
2. 返回受影响的行数。

---

## 依赖

| 依赖 | 类型 | 说明 |
|------|------|------|
| `AdminMapper` | MyBatis Mapper | 管理员表的 CRUD 操作 |
| `StudentMapper` | MyBatis Mapper | 学生表的增删改查及批量操作 |
| `SelectMapper` | MyBatis Mapper | 选课记录表的操作 |
| `CourseMapper` | MyBatis Mapper | 课程表操作（预留扩展） |
