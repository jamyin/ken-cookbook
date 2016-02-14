package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.IntelligentFixingsShape;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsShapeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntelligentFixingsShapeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int countByExample(IntelligentFixingsShapeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int deleteByExample(IntelligentFixingsShapeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int insert(IntelligentFixingsShape record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int insertSelective(IntelligentFixingsShape record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    List<IntelligentFixingsShape> selectByExample(IntelligentFixingsShapeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    IntelligentFixingsShape selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") IntelligentFixingsShape record, @Param("example") IntelligentFixingsShapeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByExample(@Param("record") IntelligentFixingsShape record, @Param("example") IntelligentFixingsShapeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByPrimaryKeySelective(IntelligentFixingsShape record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_shape
     *
     * @mbggenerated Wed Jan 06 17:02:53 CST 2016
     */
    int updateByPrimaryKey(IntelligentFixingsShape record);
}