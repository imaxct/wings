package cn.sduonline.wings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sduonline.wings.dao.mapper.SelectMapper;
import cn.sduonline.wings.model.condition.SelectCondition;
import cn.sduonline.wings.service.SelectionService;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionFatVO;

/**
 * Created by imaxct on 18-9-30.
 */
@Service
@SuppressWarnings("unchecked")
public class SelectionServiceImpl implements SelectionService {
    private final SelectMapper selectMapper;

    @Autowired
    public SelectionServiceImpl(SelectMapper selectMapper) {
        this.selectMapper = selectMapper;
    }

    @Override
    public Result<List<SelectionFatVO>> exportSelection(Long courseId) {
        SelectCondition condition = new SelectCondition();
        condition.setCourseId(courseId);
        List<SelectionFatVO> list = selectMapper.selectByConditionJoinStudent(condition);
        return Result.ok(list);
    }

    @Override
    public Result<List<SelectionFatVO>> exportAllSelection() {
        return Result.ok(selectMapper.selectByConditionJoinStudent(new SelectCondition()));
    }
}
