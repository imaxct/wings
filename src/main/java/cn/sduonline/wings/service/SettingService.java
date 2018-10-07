package cn.sduonline.wings.service;

import java.util.List;

import cn.sduonline.wings.model.Setting;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
public interface SettingService {

    /**
     * 获取所有设置
     * 
     * @return
     */
    List<Setting> getAllSettings();

    /**
     * 更新设置
     * 
     * @param setting
     * @return
     */
    Result updateSetting(Setting setting);

    /**
     * 获取设置
     * 
     * @param name
     * @return
     */
    String getSettingByName(String name);
}
