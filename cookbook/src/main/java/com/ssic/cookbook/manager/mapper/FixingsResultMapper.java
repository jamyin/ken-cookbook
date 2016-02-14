package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.pojo.FixingsResult;
import com.ssic.cookbook.manager.pojo.FixingsResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FixingsResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int countByExample(FixingsResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int deleteByExample(FixingsResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int insert(FixingsResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int insertSelective(FixingsResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    List<FixingsResult> selectByExample(FixingsResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    FixingsResult selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int updateByExampleSelective(@Param("record") FixingsResult record, @Param("example") FixingsResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int updateByExample(@Param("record") FixingsResult record, @Param("example") FixingsResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int updateByPrimaryKeySelective(FixingsResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cb_fixings_result
     *
     * @mbggenerated Mon Jan 04 14:25:06 CST 2016
     */
    int updateByPrimaryKey(FixingsResult record);
}