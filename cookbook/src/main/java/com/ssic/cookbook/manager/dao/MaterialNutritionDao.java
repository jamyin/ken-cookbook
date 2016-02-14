/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.mapper.MaterialNutritionMapper;
import com.ssic.cookbook.manager.pojo.MaterialNutritionExample.Criteria;
import com.ssic.cookbook.manager.pojo.MaterialNutrition;
import com.ssic.cookbook.manager.pojo.MaterialNutritionExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: MaterialNutritionDao </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月22日 下午5:20:59	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月22日 下午5:20:59</p>
 * <p>修改备注：</p>
 */
@Repository
public class MaterialNutritionDao
{
    @Autowired
    private MaterialNutritionMapper mapper;

    public List<MaterialNutrition> findForList(MaterialNutrition param)
    {
        MaterialNutritionExample example = new MaterialNutritionExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        if (!StringUtils.isEmpty(param.getMaterialId()))
        {
            criteria.andMaterialIdEqualTo(param.getMaterialId());
        }
        if (!StringUtils.isEmpty(param.getNutritionId()))
        {
            criteria.andNutritionIdEqualTo(param.getNutritionId());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
    }

    public MaterialNutrition findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(MaterialNutrition param)
    {
        param.setStat(CookbookFields.Enable);
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBN(MaterialNutrition param)
    {
        param.setStat(CookbookFields.Enable);
        mapper.insert(param);
    }

    /**     
     * findByMaterialId：通过原料id查询当前原料下的所有营养
     * @param materialId
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月24日 上午11:49:27	 
     */
    public List<MaterialNutrition> findByMaterialId(String materialId)
    {
        MaterialNutritionExample example = new MaterialNutritionExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(materialId))
        {
            criteria.andMaterialIdEqualTo(materialId);
        }
        criteria.andStatEqualTo(DataStatus.ENABLED);
        return mapper.selectByExample(example);
    }
    
    public void deleteMN(MaterialNutrition param ){
    	mapper.updateByPrimaryKeySelective(param);
    }

}
