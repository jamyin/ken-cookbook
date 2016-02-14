package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.Nutrition;
import com.ssic.cookbook.manager.pojo.NutritionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NutritionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int countByExample(NutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int deleteByExample(NutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int insert(Nutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int insertSelective(Nutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    List<Nutrition> selectByExample(NutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    Nutrition selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int updateByExampleSelective(@Param("record") Nutrition record, @Param("example") NutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int updateByExample(@Param("record") Nutrition record, @Param("example") NutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int updateByPrimaryKeySelective(Nutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_nutrition
     *
     * @mbggenerated Wed Jan 13 11:47:51 CST 2016
     */
    int updateByPrimaryKey(Nutrition record);
}