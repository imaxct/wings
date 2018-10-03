package cn.sduonline.wings.service;

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
    Result getAllSettings();

    /**
     * 更新设置
     * 
     * @param setting
     * @return
     */
    Result updateSetting(Setting setting);
}
