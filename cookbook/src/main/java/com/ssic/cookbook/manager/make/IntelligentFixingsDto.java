package com.ssic.cookbook.manager.make;

import java.util.Date;

public class IntelligentFixingsDto {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.set_meal_name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String setMealName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.fixings_master_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String fixingsMasterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.lunch_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer lunchCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.dinner_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer dinnerCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.product_taste_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String productTasteId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.product_color_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String productColorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.product_cuisine_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String productCuisineId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.product_style_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String productStyleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.product_shape_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String productShapeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.total_cost_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String totalCostOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.total_cost
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Long totalCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.total_fixed_price_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String totalFixedPriceOperator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.total_fixed_price
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Long totalFixedPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.is_sensitive_ingredients
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer isSensitiveIngredients;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.fixings_start_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date fixingsStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.fixings_end_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date fixingsEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_intelligent_fixings.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.id
     *
     * @return the value of t_cb_intelligent_fixings.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.id
     *
     * @param id the value for t_cb_intelligent_fixings.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.set_meal_name
     *
     * @return the value of t_cb_intelligent_fixings.set_meal_name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getSetMealName() {
        return setMealName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.set_meal_name
     *
     * @param setMealName the value for t_cb_intelligent_fixings.set_meal_name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setSetMealName(String setMealName) {
        this.setMealName = setMealName == null ? null : setMealName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.fixings_master_id
     *
     * @return the value of t_cb_intelligent_fixings.fixings_master_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getFixingsMasterId() {
        return fixingsMasterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.fixings_master_id
     *
     * @param fixingsMasterId the value for t_cb_intelligent_fixings.fixings_master_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setFixingsMasterId(String fixingsMasterId) {
        this.fixingsMasterId = fixingsMasterId == null ? null : fixingsMasterId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.lunch_count
     *
     * @return the value of t_cb_intelligent_fixings.lunch_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getLunchCount() {
        return lunchCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.lunch_count
     *
     * @param lunchCount the value for t_cb_intelligent_fixings.lunch_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setLunchCount(Integer lunchCount) {
        this.lunchCount = lunchCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.dinner_count
     *
     * @return the value of t_cb_intelligent_fixings.dinner_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getDinnerCount() {
        return dinnerCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.dinner_count
     *
     * @param dinnerCount the value for t_cb_intelligent_fixings.dinner_count
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setDinnerCount(Integer dinnerCount) {
        this.dinnerCount = dinnerCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.product_taste_id
     *
     * @return the value of t_cb_intelligent_fixings.product_taste_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getProductTasteId() {
        return productTasteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.product_taste_id
     *
     * @param productTasteId the value for t_cb_intelligent_fixings.product_taste_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setProductTasteId(String productTasteId) {
        this.productTasteId = productTasteId == null ? null : productTasteId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.product_color_id
     *
     * @return the value of t_cb_intelligent_fixings.product_color_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getProductColorId() {
        return productColorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.product_color_id
     *
     * @param productColorId the value for t_cb_intelligent_fixings.product_color_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setProductColorId(String productColorId) {
        this.productColorId = productColorId == null ? null : productColorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.product_cuisine_id
     *
     * @return the value of t_cb_intelligent_fixings.product_cuisine_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getProductCuisineId() {
        return productCuisineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.product_cuisine_id
     *
     * @param productCuisineId the value for t_cb_intelligent_fixings.product_cuisine_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setProductCuisineId(String productCuisineId) {
        this.productCuisineId = productCuisineId == null ? null : productCuisineId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.product_style_id
     *
     * @return the value of t_cb_intelligent_fixings.product_style_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getProductStyleId() {
        return productStyleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.product_style_id
     *
     * @param productStyleId the value for t_cb_intelligent_fixings.product_style_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setProductStyleId(String productStyleId) {
        this.productStyleId = productStyleId == null ? null : productStyleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.product_shape_id
     *
     * @return the value of t_cb_intelligent_fixings.product_shape_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getProductShapeId() {
        return productShapeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.product_shape_id
     *
     * @param productShapeId the value for t_cb_intelligent_fixings.product_shape_id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setProductShapeId(String productShapeId) {
        this.productShapeId = productShapeId == null ? null : productShapeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.total_cost_operator
     *
     * @return the value of t_cb_intelligent_fixings.total_cost_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getTotalCostOperator() {
        return totalCostOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.total_cost_operator
     *
     * @param totalCostOperator the value for t_cb_intelligent_fixings.total_cost_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setTotalCostOperator(String totalCostOperator) {
        this.totalCostOperator = totalCostOperator == null ? null : totalCostOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.total_cost
     *
     * @return the value of t_cb_intelligent_fixings.total_cost
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Long getTotalCost() {
        return totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.total_cost
     *
     * @param totalCost the value for t_cb_intelligent_fixings.total_cost
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.total_fixed_price_operator
     *
     * @return the value of t_cb_intelligent_fixings.total_fixed_price_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getTotalFixedPriceOperator() {
        return totalFixedPriceOperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.total_fixed_price_operator
     *
     * @param totalFixedPriceOperator the value for t_cb_intelligent_fixings.total_fixed_price_operator
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setTotalFixedPriceOperator(String totalFixedPriceOperator) {
        this.totalFixedPriceOperator = totalFixedPriceOperator == null ? null : totalFixedPriceOperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.total_fixed_price
     *
     * @return the value of t_cb_intelligent_fixings.total_fixed_price
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Long getTotalFixedPrice() {
        return totalFixedPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.total_fixed_price
     *
     * @param totalFixedPrice the value for t_cb_intelligent_fixings.total_fixed_price
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setTotalFixedPrice(Long totalFixedPrice) {
        this.totalFixedPrice = totalFixedPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.is_sensitive_ingredients
     *
     * @return the value of t_cb_intelligent_fixings.is_sensitive_ingredients
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getIsSensitiveIngredients() {
        return isSensitiveIngredients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.is_sensitive_ingredients
     *
     * @param isSensitiveIngredients the value for t_cb_intelligent_fixings.is_sensitive_ingredients
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setIsSensitiveIngredients(Integer isSensitiveIngredients) {
        this.isSensitiveIngredients = isSensitiveIngredients;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.fixings_start_time
     *
     * @return the value of t_cb_intelligent_fixings.fixings_start_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getFixingsStartTime() {
        return fixingsStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.fixings_start_time
     *
     * @param fixingsStartTime the value for t_cb_intelligent_fixings.fixings_start_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setFixingsStartTime(Date fixingsStartTime) {
        this.fixingsStartTime = fixingsStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.fixings_end_time
     *
     * @return the value of t_cb_intelligent_fixings.fixings_end_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getFixingsEndTime() {
        return fixingsEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.fixings_end_time
     *
     * @param fixingsEndTime the value for t_cb_intelligent_fixings.fixings_end_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setFixingsEndTime(Date fixingsEndTime) {
        this.fixingsEndTime = fixingsEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.create_time
     *
     * @return the value of t_cb_intelligent_fixings.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.create_time
     *
     * @param createTime the value for t_cb_intelligent_fixings.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.last_update_time
     *
     * @return the value of t_cb_intelligent_fixings.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_intelligent_fixings.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_intelligent_fixings.stat
     *
     * @return the value of t_cb_intelligent_fixings.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_intelligent_fixings.stat
     *
     * @param stat the value for t_cb_intelligent_fixings.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}