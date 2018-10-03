package cn.sduonline.wings.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.model.Setting;
import cn.sduonline.wings.service.SettingService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
@RestController
@RequestMapping("/Admin")
@RequiresRoles(RoleName.ROLE_ADMIN)
public class AdminSettingController {

    private final SettingService settingService;

    @Autowired
    public AdminSettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/getSetting")
    public Result getAllSettings() {
        return settingService.getAllSettings();
    }

    @PostMapping("/updateSetting")
    public Result updateSetting(@RequestBody Setting setting) {
        return settingService.updateSetting(setting);
    }
}
