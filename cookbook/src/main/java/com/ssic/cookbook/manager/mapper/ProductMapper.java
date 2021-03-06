package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    Product selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_product
     *
     * @mbggenerated Thu Dec 17 14:12:00 CST 2015
     */
    int updateByPrimaryKey(Product record);
}