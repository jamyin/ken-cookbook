package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.FixingsResultTypeProduct;
import com.ssic.cookbook.manager.pojo.FixingsResultTypeProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FixingsResultTypeProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int countByExample(FixingsResultTypeProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int deleteByExample(FixingsResultTypeProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int insert(FixingsResultTypeProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int insertSelective(FixingsResultTypeProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    List<FixingsResultTypeProduct> selectByExample(FixingsResultTypeProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    FixingsResultTypeProduct selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByExampleSelective(@Param("record") FixingsResultTypeProduct record, @Param("example") FixingsResultTypeProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByExample(@Param("record") FixingsResultTypeProduct record, @Param("example") FixingsResultTypeProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByPrimaryKeySelective(FixingsResultTypeProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result_type_product
     *
     * @mbggenerated Thu Dec 17 16:30:53 CST 2015
     */
    int updateByPrimaryKey(FixingsResultTypeProduct record);
}