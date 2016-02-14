package com.ssic.cookbook.manager.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.mapper.MaterialExMapper;
import com.ssic.cookbook.manager.mapper.MaterialMapper;
import com.ssic.cookbook.manager.pojo.Material;
import com.ssic.cookbook.manager.pojo.MaterialExample;
import com.ssic.cookbook.manager.pojo.MaterialExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.constants.DataStatus;

@Repository
public class MaterialDao
{

    @Autowired
    private MaterialMapper mapper;
    @Autowired
    private MaterialExMapper exmapper;

    public List<Material> findByPage(Material param)
    {
        MaterialExample example = new MaterialExample();
        Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        if (!StringUtils.isEmpty(param.getType()))
        {
            criteria.andTypeEqualTo(param.getType());
        }
        if (!StringUtils.isEmpty(param.getBigCategoryId()))
        {
            criteria.andBigCategoryIdEqualTo(param.getBigCategoryId());
        }
        if (!StringUtils.isEmpty(param.getBrandId()))
        {
            criteria.andBrandIdEqualTo(param.getBrandId());
        }
        if (!StringUtils.isEmpty(param.getIsSensitiveMaterial()))
        {
            criteria.andIsSensitiveMaterialEqualTo(param.getIsSensitiveMaterial());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
    }

    public List<Material> findBy(Material param)
    {
        MaterialExample example = new MaterialExample();
        Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameEqualTo(param.getName());
        }
        if (!StringUtils.isEmpty(param.getType()))
        {
            criteria.andTypeEqualTo(param.getType());
        }
        if (!StringUtils.isEmpty(param.getBigCategoryId()))
        {
            criteria.andBigCategoryIdEqualTo(param.getBigCategoryId());
        }
        if (!StringUtils.isEmpty(param.getBrandId()))
        {
            criteria.andBrandIdEqualTo(param.getBrandId());
        }
        if (!StringUtils.isEmpty(param.getIsSensitiveMaterial()))
        {
            criteria.andIsSensitiveMaterialEqualTo(param.getIsSensitiveMaterial());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
    }
    
    
    public Material findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public List<MaterialDto> findAll(MaterialDto param)
    {
        return exmapper.findAll(param);
    }

    public int findCount(MaterialDto param)
    {
        return exmapper.findCount(param);
    }

    public void updateM(Material param)
    {
        param.setStat(CookbookFields.Enable);
        mapper.updateByPrimaryKeySelective(param);
    }

    public void delM(Material param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertM(Material param)
    {
        param.setStat(CookbookFields.Enable);
        mapper.insert(param);
    }

    public List<NutritionDto> findNuByMid(String mid)
    {
        return exmapper.findNuByMid(mid);
    }

    /**     
     * findByName：一句话描述方法功能
     * @param name
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月28日 下午4:59:52	 
     */
    public Material findByName(String name)
    {
        MaterialExample example = new MaterialExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name))
        {
            criteria.andNameEqualTo(name);
        }
        List<Material> list = mapper.selectByExample(example);
        return list.get(0);

    }

    public void deleteMaNuByMid(String mid)
    {
        exmapper.deleteMaNuByMid(mid);
    }

    /**     
     * findListByName：一句话描述方法功能
     * @param material
     * @return
     * @exception	
     * @author 刘博
     * @date 2016年1月15日 上午10:08:06	 
     */
    public List<Material> findListByName(Material param)
    {
        MaterialExample example = new MaterialExample();
        Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameEqualTo(param.getName());
        }
        criteria.andStatEqualTo(DataStatus.ENABLED);
        return mapper.selectByExample(example);
    }

}
