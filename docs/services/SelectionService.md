# SelectionService

## 用途

`SelectionService` 负责选课数据的导出与汇总，面向管理员提供报表功能，包括：

- 按课程导出该课程的全部选课记录（含学生信息）
- 导出全系统所有选课记录（含学生信息）

该 Service 主要用于管理员进行数据统计、名单导出等报表场景。

---

## 接口定义

**文件路径：** `src/main/java/cn/sduonline/wings/service/SelectionService.java`

| 方法 | 返回值 | 说明 |
|------|--------|------|
| `exportSelection(Long courseId)` | `Result` | 导出指定课程的选课名单 |
| `exportAllSelection()` | `Result` | 导出全部选课记录 |

---

## 实现方式

**实现类文件路径：** `src/main/java/cn/sduonline/wings/service/impl/SelectionServiceImpl.java`

### exportSelection

1. 构造 `SelectCondition` 并设置 `courseId` 作为过滤条件。
2. 调用 `selectMapper.selectByConditionJoinStudent()` 执行 **连表查询**，将选课记录与学生信息关联。
3. 返回包含 `List<SelectionFatVO>` 的 `Result` 对象，`SelectionFatVO` 包含选课信息及对应的学生完整信息。

### exportAllSelection

1. 构造空的 `SelectCondition`（不附加过滤条件）。
2. 调用 `selectMapper.selectByConditionJoinStudent()` 执行 **全量连表查询**。
3. 返回系统所有选课记录（含学生信息）的 `Result` 对象。

---

## 依赖

| 依赖 | 类型 | 说明 |
|------|------|------|
| `SelectMapper` | MyBatis Mapper | 选课记录查询，支持与学生表的连表查询（`selectByConditionJoinStudent`） |
