package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.MaterialNutrition;
import com.ssic.cookbook.manager.pojo.MaterialNutritionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialNutritionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int countByExample(MaterialNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int deleteByExample(MaterialNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int insert(MaterialNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int insertSelective(MaterialNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    List<MaterialNutrition> selectByExample(MaterialNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    MaterialNutrition selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int updateByExampleSelective(@Param("record") MaterialNutrition record, @Param("example") MaterialNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int updateByExample(@Param("record") MaterialNutrition record, @Param("example") MaterialNutritionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int updateByPrimaryKeySelective(MaterialNutrition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_material_nutrition
     *
     * @mbggenerated Tue Dec 22 17:18:05 CST 2015
     */
    int updateByPrimaryKey(MaterialNutrition record);
}