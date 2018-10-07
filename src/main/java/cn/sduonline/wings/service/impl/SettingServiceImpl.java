package cn.sduonline.wings.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import cn.sduonline.wings.constant.SettingName;
import cn.sduonline.wings.dao.mapper.SettingMapper;
import cn.sduonline.wings.model.Setting;
import cn.sduonline.wings.model.condition.SettingCondition;
import cn.sduonline.wings.service.SettingService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
@Service
public class SettingServiceImpl implements SettingService {
    private final SettingMapper settingMapper;

    @Autowired
    public SettingServiceImpl(SettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Override
    public List<Setting> getAllSettings() {
        List<Setting> settings = settingMapper.selectByCondition(new SettingCondition());
        Set<String> settingKey = new HashSet<>();
        for (Setting setting : settings) {
            settingKey.add(setting.getSettingName());
        }
        for (String key : SettingName.SETTING_NAME) {
            if (!settingKey.contains(key)) {
                Setting setting = new Setting();
                setting.setSettingName(key);
                settings.add(setting);
                settingMapper.insert(setting);
            }
        }
        return settings;
    }

    @Override
    public Result updateSetting(Setting setting) {
        Setting dbSetting = settingMapper.selectByPrimaryKey(setting.getId());
        Assert.notNull(dbSetting, "配置不存在");
        dbSetting.setSettingValue(setting.getSettingValue());
        int num = settingMapper.updateByPrimaryKeyWithBLOBs(dbSetting);
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("保存失败", num);
        }
    }

    @Override
    public String getSettingByName(String name) {
        SettingCondition condition = new SettingCondition();
        condition.setSettingName(name);
        List<Setting> settingList = settingMapper.selectByCondition(condition);
        if (CollectionUtils.isEmpty(settingList)) {
            return null;
        }
        return settingList.get(0).getSettingValue();
    }
}
