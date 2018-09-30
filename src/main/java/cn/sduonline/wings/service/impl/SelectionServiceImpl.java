package cn.sduonline.wings.service.impl;

import org.springframework.stereotype.Service;

import cn.sduonline.wings.service.SelectionService;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionFatVO;

/**
 * Created by imaxct on 18-9-30.
 */
@Service
public class SelectionServiceImpl implements SelectionService {
	@Override
	public Result<SelectionFatVO> exportSelection(Long courseId) {
		return null;
	}

	@Override
	public Result<SelectionFatVO> exportAllSelection() {
		return null;
	}
}
