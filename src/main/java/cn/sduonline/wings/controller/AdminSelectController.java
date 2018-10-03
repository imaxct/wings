package cn.sduonline.wings.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sduonline.wings.constant.RoleName;
import cn.sduonline.wings.service.SelectionService;
import cn.sduonline.wings.vo.Result;

/**
 * Created by imaxct on 18-10-3.
 */
@RestController
@RequestMapping("/Admin")
public class AdminSelectController {

    private final SelectionService selectionService;

    @Autowired
    public AdminSelectController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @GetMapping("/exportSelect")
    @RequiresRoles(RoleName.ROLE_ADMIN)
    public Result exportCourseSelection(@RequestParam Long courseId) {
        return selectionService.exportSelection(courseId);
    }

    @GetMapping("/exportAll")
    @RequiresRoles(RoleName.ROLE_ADMIN)
    public Result exportAllSelection() {
        return selectionService.exportAllSelection();
    }
}
