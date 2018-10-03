package cn.sduonline.wings.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sduonline.wings.dao.mapper.SettingMapper;
import cn.sduonline.wings.model.Setting;
import cn.sduonline.wings.model.condition.SettingCondition;
import cn.sduonline.wings.service.SettingService;
import cn.sduonline.wings.util.BeanUtil;
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
    public Result getAllSettings() {
        List<Setting> settingList = settingMapper.selectByCondition(new SettingCondition());
        return Result.ok(settingList.stream().collect(Collectors.toMap(Setting::getSettingName, Function.identity())));
    }

    @Override
    public Result updateSetting(Setting setting) {
        Setting dbSetting = settingMapper.selectByPrimaryKey(setting.getId());
        int num = settingMapper.updateByPrimaryKeyWithBLOBs(BeanUtil.parseObject(dbSetting, setting, Setting.class));
        if (num > 0) {
            return Result.ok(num);
        } else {
            return Result.err("保存失败", num);
        }
    }
}
