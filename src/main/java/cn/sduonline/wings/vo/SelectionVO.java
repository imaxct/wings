package cn.sduonline.wings.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 已选课程VO
 * Created by imaxct on 18-9-26.
 */
@Data
public class SelectionVO implements Serializable {

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 上课时间
     */
    private String courseTime;

    /**
     * 上课地点
     */
    private String courseLocation;

    /**
     * 选课时间
     */
    private Date gmtCreate;
}
