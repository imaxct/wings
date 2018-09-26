package cn.sduonline.wings.vo;

import cn.sduonline.wings.model.Course;
import cn.sduonline.wings.model.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by imaxct on 18-9-26.
 */
@Data
public class SelectionFatVO implements Serializable {
    private Date gmtCreate;
    private Student student;
    private Course course;
}
