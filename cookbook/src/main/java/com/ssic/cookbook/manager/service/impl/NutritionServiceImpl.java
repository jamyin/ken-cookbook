package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssic.cookbook.manager.dao.NutritionDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.pojo.Nutrition;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

@Service
public class NutritionServiceImpl implements INutritionService{

	@Autowired
	private NutritionDao dao;
	@Autowired
	private IParamConfigService paramConfigService;
	
	
	public List<NutritionDto> findForList(NutritionDto nutritionDto) {
		// TODO Auto-generated method stub
		Nutrition nutrition = new Nutrition();
		BeanUtils.copyProperties(nutritionDto, nutrition);
		List<Nutrition> list = dao.findForList(nutrition);
		List<NutritionDto> listdto = BeanUtils.createBeanListByTarget(list, NutritionDto.class);
		return listdto;
	}


	public NutritionDto findById(String id) {
		// TODO Auto-generated method stub
		Nutrition nutr = dao.findById(id);
		NutritionDto nutritionDto = new NutritionDto();
		BeanUtils.copyProperties(nutr, nutritionDto);
		return nutritionDto;
	}


	public void updateNS(NutritionDto nutritionDto) {
		// TODO Auto-generated method stub
		Nutrition nut = new Nutrition();
		BeanUtils.copyProperties(nutritionDto, nut);
		dao.updateById(nut);
	}


	public void insertNS(NutritionDto nutritionDto) {
		// TODO Auto-generated method stub
		Nutrition nut = new Nutrition();
		BeanUtils.copyProperties(nutritionDto, nut);
		dao.insertNut(nut);
	}


	public void deleteNutr(String nutrid) {
		// TODO Auto-generated method stub
		Nutrition nutrition = new Nutrition();
		nutrition.setId(nutrid);
		nutrition.setStat(CookbookFields.DisEnable);
		dao.deleteNutr(nutrition);
	}

	/**
	 * @author YIn
	 * @time:2015年12月30日 下午2:57:01
	 */
	public List<NutritionDto> findListByPage(NutritionDto nutritionDto,
			LimitPageDto limitPageDto) {
		Nutrition nutrition = new Nutrition();
        BeanUtils.copyProperties(nutritionDto, nutrition);
        List<NutritionDto> listDto = new ArrayList<NutritionDto>();
        List<Nutrition> list = dao.findListByPage(nutrition, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, NutritionDto.class);
            return listDto;
        }
        return listDto;
	}

    //更新营养信息
	@Transactional
	public void updateNSAndPro(NutritionDto nutritionDto) {
		// TODO Auto-generated method stub
		String parid = nutritionDto.getParamId();
		String nustrName = nutritionDto.getName();
		String nustrUnit = nutritionDto.getUnit();
		//更新参数表
		ParamConfigDto paramConfigDto = new ParamConfigDto();
		paramConfigDto.setId(parid);
		paramConfigDto.setParamName(nustrName);
		paramConfigDto.setRemark(nustrUnit);
		paramConfigDto.setLastUpdateTime(new Date());
		paramConfigService.updatePam(paramConfigDto);
		//更新营养表
	    NutritionDto nutritionDto2 = new NutritionDto();
	    nutritionDto2.setParamId(parid);
	    nutritionDto2.setName(nustrName);
	    nutritionDto2.setUnit(nustrUnit);
	    dao.updateNutrByPid(nutritionDto2);
		
	}
	
	

}
