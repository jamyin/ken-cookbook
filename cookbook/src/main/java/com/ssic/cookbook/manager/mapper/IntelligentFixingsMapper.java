package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.IntelligentFixings;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntelligentFixingsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int countByExample(IntelligentFixingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int deleteByExample(IntelligentFixingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int insert(IntelligentFixings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int insertSelective(IntelligentFixings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    List<IntelligentFixings> selectByExample(IntelligentFixingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    IntelligentFixings selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByExampleSelective(@Param("record") IntelligentFixings record, @Param("example") IntelligentFixingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByExample(@Param("record") IntelligentFixings record, @Param("example") IntelligentFixingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByPrimaryKeySelective(IntelligentFixings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByPrimaryKey(IntelligentFixings record);
}