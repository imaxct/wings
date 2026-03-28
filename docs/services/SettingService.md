# SettingService

## 用途

`SettingService` 负责系统配置项的管理，包括：

- 查询所有系统配置（自动补全缺失的默认配置项）
- 更新指定配置项的值
- 按名称读取单个配置项

系统配置用于控制选课限制数量、公告文本等全局参数。该 Service 由管理员操作，部分配置也被其他 Service（如 `StudentService`）读取。

---

## 接口定义

**文件路径：** `src/main/java/cn/sduonline/wings/service/SettingService.java`

| 方法 | 返回值 | 说明 |
|------|--------|------|
| `getAllSettings()` | `Result` | 查询所有配置项（自动补全缺失项） |
| `updateSetting(Setting setting)` | `Result` | 更新指定配置项的值 |
| `getSettingByName(String name)` | `String` | 根据名称获取配置值 |

---

## 实现方式

**实现类文件路径：** `src/main/java/cn/sduonline/wings/service/impl/SettingServiceImpl.java`

### getAllSettings

1. 调用 `settingMapper.selectAll()` 从数据库查询所有已存在的配置项。
2. 遍历 `SettingName` 常量类中预定义的所有配置键（如 `ANNOUNCEMENT`、`SELECTION_LIMIT` 等）。
3. 对于数据库中不存在的配置键，自动创建一条值为 `null` 的默认配置项并插入数据库。
4. 返回完整的配置列表（确保所有预定义配置项都存在）。

### updateSetting

1. 调用 `settingMapper.selectByName()` 查询当前配置项，若不存在则返回错误。
2. 更新 `Setting` 对象的值字段。
3. 调用 `settingMapper.updateByPrimaryKeyWithBLOBs()` 执行更新（支持 BLOB 字段存储长文本）。
4. 返回受影响行数。

### getSettingByName

1. 调用 `settingMapper.selectByName()` 按名称查询。
2. 若未找到，返回 `null`；否则返回配置的 `value` 字符串。

---

## 预定义配置项

配置键定义在 `SettingName` 常量类中，当前系统包含以下配置：

| 配置键 | 说明 |
|--------|------|
| `ANNOUNCEMENT` | 系统公告文本，展示在学生端首页 |
| `SELECTION_LIMIT` | 每位学生最多可选课程数量上限 |

---

## 依赖

| 依赖 | 类型 | 说明 |
|------|------|------|
| `SettingMapper` | MyBatis Mapper | 系统配置表的 CRUD 操作 |
| `SettingName` | 常量类 | 预定义所有合法的配置键名称 |
