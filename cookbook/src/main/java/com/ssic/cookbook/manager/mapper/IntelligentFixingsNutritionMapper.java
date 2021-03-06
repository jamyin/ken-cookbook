package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.IntelligentFixingsNutrition;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsNutritionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntelligentFixingsNutritionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int countByExample(IntelligentFixingsNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int deleteByExample(IntelligentFixingsNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int insert(IntelligentFixingsNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int insertSelective(IntelligentFixingsNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    List<IntelligentFixingsNutrition> selectByExample(IntelligentFixingsNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    IntelligentFixingsNutrition selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByExampleSelective(@Param("record") IntelligentFixingsNutrition record, @Param("example") IntelligentFixingsNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByExample(@Param("record") IntelligentFixingsNutrition record, @Param("example") IntelligentFixingsNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByPrimaryKeySelective(IntelligentFixingsNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_intelligent_fixings_nutrition
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByPrimaryKey(IntelligentFixingsNutrition record);
}