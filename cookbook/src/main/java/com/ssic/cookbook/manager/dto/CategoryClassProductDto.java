package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class CategoryClassProductDto {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.category_class_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String categoryClassId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.product_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String productId;
    
    @Getter
    @Setter
    private String productIds; // 以逗号分隔的字符串

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_category_class_product.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.id
     *
     * @return the value of t_cb_category_class_product.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.id
     *
     * @param id the value for t_cb_category_class_product.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.category_class_id
     *
     * @return the value of t_cb_category_class_product.category_class_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getCategoryClassId() {
        return categoryClassId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.category_class_id
     *
     * @param categoryClassId the value for t_cb_category_class_product.category_class_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setCategoryClassId(String categoryClassId) {
        this.categoryClassId = categoryClassId == null ? null : categoryClassId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.product_id
     *
     * @return the value of t_cb_category_class_product.product_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.product_id
     *
     * @param productId the value for t_cb_category_class_product.product_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.create_time
     *
     * @return the value of t_cb_category_class_product.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.create_time
     *
     * @param createTime the value for t_cb_category_class_product.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.last_update_time
     *
     * @return the value of t_cb_category_class_product.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_category_class_product.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_category_class_product.stat
     *
     * @return the value of t_cb_category_class_product.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_category_class_product.stat
     *
     * @param stat the value for t_cb_category_class_product.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}