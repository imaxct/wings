package cn.sduonline.wings.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Course implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程时间
     */
    private String courseTime;

    /**
     * 非贫困生数量
     */
    private Integer notPoorNum;

    /**
     * 总数
     */
    private Integer totalNum;

    /**
     * 允许报名的年级
     */
    private String gradeLimit;

    /**
     * 当前剩余总数
     */
    private Integer availableNum;

    /**
     * 报名截止日期
     */
    private Date deadline;

    /**
     * 上课地点
     */
    private String courseLocation;

    /**
     * 课程描述
     */
    private String courseDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getNotPoorNum() {
        return notPoorNum;
    }

    public void setNotPoorNum(Integer notPoorNum) {
        this.notPoorNum = notPoorNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getGradeLimit() {
        return gradeLimit;
    }

    public void setGradeLimit(String gradeLimit) {
        this.gradeLimit = gradeLimit;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCourseTime() == null ? other.getCourseTime() == null : this.getCourseTime().equals(other.getCourseTime()))
            && (this.getNotPoorNum() == null ? other.getNotPoorNum() == null : this.getNotPoorNum().equals(other.getNotPoorNum()))
            && (this.getTotalNum() == null ? other.getTotalNum() == null : this.getTotalNum().equals(other.getTotalNum()))
            && (this.getGradeLimit() == null ? other.getGradeLimit() == null : this.getGradeLimit().equals(other.getGradeLimit()))
            && (this.getAvailableNum() == null ? other.getAvailableNum() == null : this.getAvailableNum().equals(other.getAvailableNum()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getCourseLocation() == null ? other.getCourseLocation() == null : this.getCourseLocation().equals(other.getCourseLocation()))
            && (this.getCourseDesc() == null ? other.getCourseDesc() == null : this.getCourseDesc().equals(other.getCourseDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCourseTime() == null) ? 0 : getCourseTime().hashCode());
        result = prime * result + ((getNotPoorNum() == null) ? 0 : getNotPoorNum().hashCode());
        result = prime * result + ((getTotalNum() == null) ? 0 : getTotalNum().hashCode());
        result = prime * result + ((getGradeLimit() == null) ? 0 : getGradeLimit().hashCode());
        result = prime * result + ((getAvailableNum() == null) ? 0 : getAvailableNum().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getCourseLocation() == null) ? 0 : getCourseLocation().hashCode());
        result = prime * result + ((getCourseDesc() == null) ? 0 : getCourseDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseTime=").append(courseTime);
        sb.append(", notPoorNum=").append(notPoorNum);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", gradeLimit=").append(gradeLimit);
        sb.append(", availableNum=").append(availableNum);
        sb.append(", deadline=").append(deadline);
        sb.append(", courseLocation=").append(courseLocation);
        sb.append(", courseDesc=").append(courseDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}