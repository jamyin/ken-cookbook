package com.ssic.cookbook.manager.dto;

import java.util.Date;

public class FixingsMasterDto {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.fixings _name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private String fixingsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.fixings _type
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer fixingsType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Date lastUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cb_fixings_master.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    private Integer stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.id
     *
     * @return the value of t_cb_fixings_master.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.id
     *
     * @param id the value for t_cb_fixings_master.id
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.fixings _name
     *
     * @return the value of t_cb_fixings_master.fixings _name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public String getFixingsName() {
        return fixingsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.fixings _name
     *
     * @param fixingsName the value for t_cb_fixings_master.fixings _name
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setFixingsName(String fixingsName) {
        this.fixingsName = fixingsName == null ? null : fixingsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.fixings _type
     *
     * @return the value of t_cb_fixings_master.fixings _type
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getFixingsType() {
        return fixingsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.fixings _type
     *
     * @param fixingsType the value for t_cb_fixings_master.fixings _type
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setFixingsType(Integer fixingsType) {
        this.fixingsType = fixingsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.create_time
     *
     * @return the value of t_cb_fixings_master.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.create_time
     *
     * @param createTime the value for t_cb_fixings_master.create_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.last_update_time
     *
     * @return the value of t_cb_fixings_master.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.last_update_time
     *
     * @param lastUpdateTime the value for t_cb_fixings_master.last_update_time
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cb_fixings_master.stat
     *
     * @return the value of t_cb_fixings_master.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public Integer getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cb_fixings_master.stat
     *
     * @param stat the value for t_cb_fixings_master.stat
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    public void setStat(Integer stat) {
        this.stat = stat;
    }
}