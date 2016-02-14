/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.MaterialNutritionDao;
import com.ssic.cookbook.manager.dto.MaterialNutritionDto;
import com.ssic.cookbook.manager.pojo.MaterialNutrition;
import com.ssic.cookbook.manager.service.IMaterialNutritionService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

/**		
 * <p>Title: MaterialNutritionServiceImpl </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月22日 下午5:23:43	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月22日 下午5:23:43</p>
 * <p>修改备注：</p>
 */
@Service
public class MaterialNutritionServiceImpl implements IMaterialNutritionService
{

    @Autowired
    private MaterialNutritionDao dao;

    public List<MaterialNutritionDto> findForList(MaterialNutritionDto materialNutritionDto)
    {
        MaterialNutrition materialNutrition = new MaterialNutrition();
        BeanUtils.copyProperties(materialNutritionDto, materialNutrition);
        List<MaterialNutrition> list = dao.findForList(materialNutrition);
        List<MaterialNutritionDto> listdto =
            BeanUtils.createBeanListByTarget(list, MaterialNutritionDto.class);
        return listdto;
    }

    public MaterialNutritionDto findById(String id)
    {
        MaterialNutrition materialNutrition = dao.findById(id);
        MaterialNutritionDto materialNutritionDto = new MaterialNutritionDto();
        BeanUtils.copyProperties(materialNutrition, materialNutritionDto);
        return materialNutritionDto;
    }

    public void updateBN(MaterialNutritionDto materialNutritionDto)
    {
        MaterialNutrition param = new MaterialNutrition();
        BeanUtils.copyProperties(materialNutritionDto, param);
        dao.updateById(param);
    }

    public void insertBN(MaterialNutritionDto materialNutritionDto)
    {
        MaterialNutrition param = new MaterialNutrition();
        BeanUtils.copyProperties(materialNutritionDto, param);
        dao.insertBN(param);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IMaterialNutritionService#findByMaterialId(java.lang.String)   
    */
    public List<MaterialNutritionDto> findByMaterialId(String materialId)
    {
        List<MaterialNutrition> list = dao.findByMaterialId(materialId);
        List<MaterialNutritionDto> listdto =
            BeanUtils.createBeanListByTarget(list, MaterialNutritionDto.class);
        return listdto;
    }

	public void deleteMN(String mid) {
		// TODO Auto-generated method stub
		MaterialNutrition materialNutrition = new MaterialNutrition();
		materialNutrition.setMaterialId(mid);
		materialNutrition.setStat(CookbookFields.DisEnable);
		dao.deleteMN(materialNutrition);
	}

}
