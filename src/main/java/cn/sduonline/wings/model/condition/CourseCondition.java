package cn.sduonline.wings.model.condition;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by imaxct on 18-9-29.
 */
@Data
public class CourseCondition implements Serializable {
	private Date deadline;
}
