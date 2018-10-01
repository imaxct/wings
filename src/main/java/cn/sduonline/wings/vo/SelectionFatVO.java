package cn.sduonline.wings.vo;

import java.io.Serializable;
import java.util.Date;

import cn.sduonline.wings.model.Student;
import lombok.Data;

/**
 * Created by imaxct on 18-9-26.
 */
@Data
public class SelectionFatVO implements Serializable {
	private Long id;
	private Date gmtCreate;
	private Long courseId;
	private Long studentId;
	private Student student;
	private String courseName;
}
