package com.ssic.cookbook.manager.pojo;

import java.util.Date;

public class Material {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.img_url
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String imgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.type
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.cost
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Long cost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.big_category_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String bigCategoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.brand_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String brandId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.is_sensitive_material
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Integer isSensitiveMaterial;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.remark
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_material.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.id
     *
     * @return the value of t_cb_material.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.id
     *
     * @param id the value for t_cb_material.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.img_url
     *
     * @return the value of t_cb_material.img_url
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.img_url
     *
     * @param imgUrl the value for t_cb_material.img_url
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.name
     *
     * @return the value of t_cb_material.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.name
     *
     * @param name the value for t_cb_material.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.type
     *
     * @return the value of t_cb_material.type
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.type
     *
     * @param type the value for t_cb_material.type
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.cost
     *
     * @return the value of t_cb_material.cost
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Long getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.cost
     *
     * @param cost the value for t_cb_material.cost
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setCost(Long cost) {
        this.cost = cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.big_category_id
     *
     * @return the value of t_cb_material.big_category_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getBigCategoryId() {
        return bigCategoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.big_category_id
     *
     * @param bigCategoryId the value for t_cb_material.big_category_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setBigCategoryId(String bigCategoryId) {
        this.bigCategoryId = bigCategoryId == null ? null : bigCategoryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.brand_id
     *
     * @return the value of t_cb_material.brand_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.brand_id
     *
     * @param brandId the value for t_cb_material.brand_id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.is_sensitive_material
     *
     * @return the value of t_cb_material.is_sensitive_material
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Integer getIsSensitiveMaterial() {
        return isSensitiveMaterial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.is_sensitive_material
     *
     * @param isSensitiveMaterial the value for t_cb_material.is_sensitive_material
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setIsSensitiveMaterial(Integer isSensitiveMaterial) {
        this.isSensitiveMaterial = isSensitiveMaterial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.remark
     *
     * @return the value of t_cb_material.remark
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.remark
     *
     * @param remark the value for t_cb_material.remark
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.create_time
     *
     * @return the value of t_cb_material.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.create_time
     *
     * @param createTime the value for t_cb_material.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.last_update_time
     *
     * @return the value of t_cb_material.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_material.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_material.stat
     *
     * @return the value of t_cb_material.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_material.stat
     *
     * @param stat the value for t_cb_material.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}