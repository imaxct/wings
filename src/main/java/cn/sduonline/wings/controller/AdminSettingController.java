package cn.sduonline.wings.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
@SuppressWarnings("unchecked")
public class AdminSettingController {

    private final SettingService settingService;

    @Autowired
    public AdminSettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/getSetting")
    public Result<Map<String, String>> getAllSettings() {
        List<Setting> settingList = settingService.getAllSettings();
        return Result.ok(settingList.stream().collect(Collectors.toMap(Setting::getSettingName, Function.identity())));
    }

    @PostMapping("/updateSetting")
    public Result updateSetting(@RequestBody Setting setting) {
        return settingService.updateSetting(setting);
    }
}
