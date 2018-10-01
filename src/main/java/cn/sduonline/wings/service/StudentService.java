package cn.sduonline.wings.service;

import java.util.List;

import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.vo.Result;
import cn.sduonline.wings.vo.SelectionVO;

/**
 * Created by imaxct on 18-9-26.
 */
public interface StudentService {

	/**
	 * 通过学号获取
	 *
	 * @param stuNo
	 * @return
	 */
	Student getStudentByNo(String stuNo);

	/**
	 * 爬教务获取
	 *
	 * @param stuNo
	 * @param password
	 * @return
	 */
	Student getStudentByCrawler(String stuNo, String password);

	/**
	 * 保存
	 * 
	 * @param student
	 * @return
	 */
	Student saveStudent(Student student);

	/**
	 * 获取公告
	 *
	 * @return
	 */
	Result getAnnouncement();

	/**
	 * 获取已选课程
	 *
	 * @return
	 */
	Result<List<SelectionVO>> getSelectedCourse(Long studentId);

	/**
	 * 选课
	 *
	 * @param student
	 * @param courseId
	 * @return
	 */
	Result selectCourse(Student student, Long courseId);

	/**
	 * 退选
	 *
	 * @return
	 */
	Result deselectCourse(Student student, Long courseId);

	/**
	 * 获取课程列表
	 *
	 * @return
	 */
	Result<List<Course>> getCourseList();
}
