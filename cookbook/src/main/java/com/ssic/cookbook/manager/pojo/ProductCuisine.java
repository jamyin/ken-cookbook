package com.ssic.cookbook.manager.pojo;

import java.util.Date;

public class ProductCuisine {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_cuisine.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_cuisine.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_cuisine.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_cuisine.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_product_cuisine.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_cuisine.id
     *
     * @return the value of t_cb_product_cuisine.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_cuisine.id
     *
     * @param id the value for t_cb_product_cuisine.id
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_cuisine.name
     *
     * @return the value of t_cb_product_cuisine.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_cuisine.name
     *
     * @param name the value for t_cb_product_cuisine.name
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_cuisine.create_time
     *
     * @return the value of t_cb_product_cuisine.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_cuisine.create_time
     *
     * @param createTime the value for t_cb_product_cuisine.create_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_cuisine.last_update_time
     *
     * @return the value of t_cb_product_cuisine.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_cuisine.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_product_cuisine.last_update_time
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_product_cuisine.stat
     *
     * @return the value of t_cb_product_cuisine.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_product_cuisine.stat
     *
     * @param stat the value for t_cb_product_cuisine.stat
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}