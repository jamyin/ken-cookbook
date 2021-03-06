package com.ssic.cookbook.manager.pojo;

import java.util.Date;

public class ProductMaterial {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.product_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.material_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private String materialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.material_weight
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private Integer materialWeight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.create_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.last_update_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_material.stat
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.id
     *
     * @return the value of t_cb_product_material.id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.id
     *
     * @param id the value for t_cb_product_material.id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.product_id
     *
     * @return the value of t_cb_product_material.product_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.product_id
     *
     * @param productId the value for t_cb_product_material.product_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.material_id
     *
     * @return the value of t_cb_product_material.material_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.material_id
     *
     * @param materialId the value for t_cb_product_material.material_id
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.material_weight
     *
     * @return the value of t_cb_product_material.material_weight
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public Integer getMaterialWeight() {
        return materialWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.material_weight
     *
     * @param materialWeight the value for t_cb_product_material.material_weight
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setMaterialWeight(Integer materialWeight) {
        this.materialWeight = materialWeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.create_time
     *
     * @return the value of t_cb_product_material.create_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.create_time
     *
     * @param createTime the value for t_cb_product_material.create_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.last_update_time
     *
     * @return the value of t_cb_product_material.last_update_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_product_material.last_update_time
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_material.stat
     *
     * @return the value of t_cb_product_material.stat
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_material.stat
     *
     * @param stat the value for t_cb_product_material.stat
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}