package com.ssic.cookbook.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntelligentFixingsCategoryExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public IntelligentFixingsCategoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("category_id like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("category_id not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryCountIsNull() {
            addCriterion("category_count is null");
            return (Criteria) this;
        }

        public Criteria andCategoryCountIsNotNull() {
            addCriterion("category_count is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryCountEqualTo(Long value) {
            addCriterion("category_count =", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountNotEqualTo(Long value) {
            addCriterion("category_count <>", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountGreaterThan(Long value) {
            addCriterion("category_count >", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountGreaterThanOrEqualTo(Long value) {
            addCriterion("category_count >=", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountLessThan(Long value) {
            addCriterion("category_count <", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountLessThanOrEqualTo(Long value) {
            addCriterion("category_count <=", value, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountIn(List<Long> values) {
            addCriterion("category_count in", values, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountNotIn(List<Long> values) {
            addCriterion("category_count not in", values, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountBetween(Long value1, Long value2) {
            addCriterion("category_count between", value1, value2, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andCategoryCountNotBetween(Long value1, Long value2) {
            addCriterion("category_count not between", value1, value2, "categoryCount");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdIsNull() {
            addCriterion("intelligent_fixings_id is null");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdIsNotNull() {
            addCriterion("intelligent_fixings_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdEqualTo(String value) {
            addCriterion("intelligent_fixings_id =", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdNotEqualTo(String value) {
            addCriterion("intelligent_fixings_id <>", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdGreaterThan(String value) {
            addCriterion("intelligent_fixings_id >", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdGreaterThanOrEqualTo(String value) {
            addCriterion("intelligent_fixings_id >=", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdLessThan(String value) {
            addCriterion("intelligent_fixings_id <", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdLessThanOrEqualTo(String value) {
            addCriterion("intelligent_fixings_id <=", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdLike(String value) {
            addCriterion("intelligent_fixings_id like", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdNotLike(String value) {
            addCriterion("intelligent_fixings_id not like", value, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdIn(List<String> values) {
            addCriterion("intelligent_fixings_id in", values, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdNotIn(List<String> values) {
            addCriterion("intelligent_fixings_id not in", values, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdBetween(String value1, String value2) {
            addCriterion("intelligent_fixings_id between", value1, value2, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andIntelligentFixingsIdNotBetween(String value1, String value2) {
            addCriterion("intelligent_fixings_id not between", value1, value2, "intelligentFixingsId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andStatIsNull() {
            addCriterion("stat is null");
            return (Criteria) this;
        }

        public Criteria andStatIsNotNull() {
            addCriterion("stat is not null");
            return (Criteria) this;
        }

        public Criteria andStatEqualTo(Integer value) {
            addCriterion("stat =", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotEqualTo(Integer value) {
            addCriterion("stat <>", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThan(Integer value) {
            addCriterion("stat >", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatGreaterThanOrEqualTo(Integer value) {
            addCriterion("stat >=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThan(Integer value) {
            addCriterion("stat <", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatLessThanOrEqualTo(Integer value) {
            addCriterion("stat <=", value, "stat");
            return (Criteria) this;
        }

        public Criteria andStatIn(List<Integer> values) {
            addCriterion("stat in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotIn(List<Integer> values) {
            addCriterion("stat not in", values, "stat");
            return (Criteria) this;
        }

        public Criteria andStatBetween(Integer value1, Integer value2) {
            addCriterion("stat between", value1, value2, "stat");
            return (Criteria) this;
        }

        public Criteria andStatNotBetween(Integer value1, Integer value2) {
            addCriterion("stat not between", value1, value2, "stat");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated do_not_delete_during_merge Tue Dec 15 14:37:30 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_cb_intelligent_fixings_category
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
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