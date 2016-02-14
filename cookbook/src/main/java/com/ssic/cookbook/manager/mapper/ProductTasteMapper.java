package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.pojo.ProductTasteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTasteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int countByExample(ProductTasteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int deleteByExample(ProductTasteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int insert(ProductTaste record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int insertSelective(ProductTaste record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    List<ProductTaste> selectByExample(ProductTasteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    ProductTaste selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByExampleSelective(@Param("record") ProductTaste record, @Param("example") ProductTasteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByExample(@Param("record") ProductTaste record, @Param("example") ProductTasteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByPrimaryKeySelective(ProductTaste record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product_taste
     *
     * @mbggenerated Tue Dec 15 14:37:30 CST 2015
     */
    int updateByPrimaryKey(ProductTaste record);
}