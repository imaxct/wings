package cn.sduonline.wings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sduonline.wings.dao.mapper.BlacklistMapper;
import cn.sduonline.wings.model.Blacklist;
import cn.sduonline.wings.vo.Result;

/**
 * @author imaxct 2019-03-24 11:31
 */
@RestController
@RequestMapping("/Admin")
public class AdminBlacklistController {
    private final BlacklistMapper blacklistMapper;

    @Autowired
    public AdminBlacklistController(BlacklistMapper blacklistMapper) {
        this.blacklistMapper = blacklistMapper;
    }

    @PostMapping("/checkBlack")
    public Result checkBlacklist(@RequestParam(name = "no") String studentNo) {
        boolean res = blacklistMapper.existsByStuNo(studentNo);
        if (res) {
            return Result.ok(studentNo);
        } else {
            return Result.err("用户不在黑名单中", null);
        }
    }

    @PostMapping("/removeBlack")
    public Result removeFromBlacklist(@RequestParam(name = "no") String studentNo) {
        int number = blacklistMapper.deleteByStudentNo(studentNo);
        if (number > 0) {
            return Result.ok(number);
        } else {
            return Result.err("删除失败", null);
        }
    }

    @PostMapping("/addBlack")
    public Result addBlacklist(@RequestParam("no") String studentNo) {
        Blacklist blacklist = new Blacklist();
        blacklist.setStudentNo(studentNo);
        int n = blacklistMapper.insert(blacklist);
        if (n > 0) {
            return Result.ok(n);
        } else {
            return Result.err("添加失败, 请先检查该学号是否在黑名单中", null);
        }
    }
}
