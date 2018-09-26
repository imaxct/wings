package cn.sduonline.wings.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIsNull() {
            addCriterion("course_time is null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIsNotNull() {
            addCriterion("course_time is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTimeEqualTo(String value) {
            addCriterion("course_time =", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotEqualTo(String value) {
            addCriterion("course_time <>", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeGreaterThan(String value) {
            addCriterion("course_time >", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("course_time >=", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeLessThan(String value) {
            addCriterion("course_time <", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeLessThanOrEqualTo(String value) {
            addCriterion("course_time <=", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeLike(String value) {
            addCriterion("course_time like", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotLike(String value) {
            addCriterion("course_time not like", value, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeIn(List<String> values) {
            addCriterion("course_time in", values, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotIn(List<String> values) {
            addCriterion("course_time not in", values, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeBetween(String value1, String value2) {
            addCriterion("course_time between", value1, value2, "courseTime");
            return (Criteria) this;
        }

        public Criteria andCourseTimeNotBetween(String value1, String value2) {
            addCriterion("course_time not between", value1, value2, "courseTime");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumIsNull() {
            addCriterion("not_poor_num is null");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumIsNotNull() {
            addCriterion("not_poor_num is not null");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumEqualTo(Integer value) {
            addCriterion("not_poor_num =", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumNotEqualTo(Integer value) {
            addCriterion("not_poor_num <>", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumGreaterThan(Integer value) {
            addCriterion("not_poor_num >", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("not_poor_num >=", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumLessThan(Integer value) {
            addCriterion("not_poor_num <", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumLessThanOrEqualTo(Integer value) {
            addCriterion("not_poor_num <=", value, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumIn(List<Integer> values) {
            addCriterion("not_poor_num in", values, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumNotIn(List<Integer> values) {
            addCriterion("not_poor_num not in", values, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumBetween(Integer value1, Integer value2) {
            addCriterion("not_poor_num between", value1, value2, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andNotPoorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("not_poor_num not between", value1, value2, "notPoorNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNull() {
            addCriterion("total_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("total_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(Integer value) {
            addCriterion("total_num =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(Integer value) {
            addCriterion("total_num <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(Integer value) {
            addCriterion("total_num >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_num >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(Integer value) {
            addCriterion("total_num <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(Integer value) {
            addCriterion("total_num <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<Integer> values) {
            addCriterion("total_num in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<Integer> values) {
            addCriterion("total_num not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(Integer value1, Integer value2) {
            addCriterion("total_num between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_num not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andGradeLimitIsNull() {
            addCriterion("grade_limit is null");
            return (Criteria) this;
        }

        public Criteria andGradeLimitIsNotNull() {
            addCriterion("grade_limit is not null");
            return (Criteria) this;
        }

        public Criteria andGradeLimitEqualTo(String value) {
            addCriterion("grade_limit =", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitNotEqualTo(String value) {
            addCriterion("grade_limit <>", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitGreaterThan(String value) {
            addCriterion("grade_limit >", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("grade_limit >=", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitLessThan(String value) {
            addCriterion("grade_limit <", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitLessThanOrEqualTo(String value) {
            addCriterion("grade_limit <=", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitLike(String value) {
            addCriterion("grade_limit like", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitNotLike(String value) {
            addCriterion("grade_limit not like", value, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitIn(List<String> values) {
            addCriterion("grade_limit in", values, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitNotIn(List<String> values) {
            addCriterion("grade_limit not in", values, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitBetween(String value1, String value2) {
            addCriterion("grade_limit between", value1, value2, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andGradeLimitNotBetween(String value1, String value2) {
            addCriterion("grade_limit not between", value1, value2, "gradeLimit");
            return (Criteria) this;
        }

        public Criteria andAvailableNumIsNull() {
            addCriterion("available_num is null");
            return (Criteria) this;
        }

        public Criteria andAvailableNumIsNotNull() {
            addCriterion("available_num is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableNumEqualTo(Integer value) {
            addCriterion("available_num =", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumNotEqualTo(Integer value) {
            addCriterion("available_num <>", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumGreaterThan(Integer value) {
            addCriterion("available_num >", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("available_num >=", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumLessThan(Integer value) {
            addCriterion("available_num <", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumLessThanOrEqualTo(Integer value) {
            addCriterion("available_num <=", value, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumIn(List<Integer> values) {
            addCriterion("available_num in", values, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumNotIn(List<Integer> values) {
            addCriterion("available_num not in", values, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumBetween(Integer value1, Integer value2) {
            addCriterion("available_num between", value1, value2, "availableNum");
            return (Criteria) this;
        }

        public Criteria andAvailableNumNotBetween(Integer value1, Integer value2) {
            addCriterion("available_num not between", value1, value2, "availableNum");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Date value) {
            addCriterion("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Date value) {
            addCriterion("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Date value) {
            addCriterion("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Date value) {
            addCriterion("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Date> values) {
            addCriterion("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Date> values) {
            addCriterion("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Date value1, Date value2) {
            addCriterion("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("deadline not between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andCourseLocationIsNull() {
            addCriterion("course_location is null");
            return (Criteria) this;
        }

        public Criteria andCourseLocationIsNotNull() {
            addCriterion("course_location is not null");
            return (Criteria) this;
        }

        public Criteria andCourseLocationEqualTo(String value) {
            addCriterion("course_location =", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationNotEqualTo(String value) {
            addCriterion("course_location <>", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationGreaterThan(String value) {
            addCriterion("course_location >", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationGreaterThanOrEqualTo(String value) {
            addCriterion("course_location >=", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationLessThan(String value) {
            addCriterion("course_location <", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationLessThanOrEqualTo(String value) {
            addCriterion("course_location <=", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationLike(String value) {
            addCriterion("course_location like", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationNotLike(String value) {
            addCriterion("course_location not like", value, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationIn(List<String> values) {
            addCriterion("course_location in", values, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationNotIn(List<String> values) {
            addCriterion("course_location not in", values, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationBetween(String value1, String value2) {
            addCriterion("course_location between", value1, value2, "courseLocation");
            return (Criteria) this;
        }

        public Criteria andCourseLocationNotBetween(String value1, String value2) {
            addCriterion("course_location not between", value1, value2, "courseLocation");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}