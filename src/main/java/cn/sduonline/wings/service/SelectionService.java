package cn.sduonline.wings.service;

import java.util.List;

import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionFatVO;

/**
 * Created by imaxct on 18-9-26.
 */
public interface SelectionService {

	/**
	 * 导出一门课的选课信息
	 *
	 * @param courseId
	 * @return
	 */
	Result<List<SelectionFatVO>> exportSelection(Long courseId);

	/**
	 * 导出所有选课信息
	 *
	 * @return
	 */
	Result<List<SelectionFatVO>> exportAllSelection();
}
