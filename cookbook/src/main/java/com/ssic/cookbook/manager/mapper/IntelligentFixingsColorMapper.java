package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.IntelligentFixingsColor;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsColorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntelligentFixingsColorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int countByExample(IntelligentFixingsColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int deleteByExample(IntelligentFixingsColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int insert(IntelligentFixingsColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int insertSelective(IntelligentFixingsColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    List<IntelligentFixingsColor> selectByExample(IntelligentFixingsColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    IntelligentFixingsColor selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") IntelligentFixingsColor record, @Param("example") IntelligentFixingsColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByExample(@Param("record") IntelligentFixingsColor record, @Param("example") IntelligentFixingsColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByPrimaryKeySelective(IntelligentFixingsColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_color
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByPrimaryKey(IntelligentFixingsColor record);
}