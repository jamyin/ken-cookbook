package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.NutritionsDto;

public interface INutritionService {

	public List<NutritionDto> findForList(NutritionDto nutritionDto);
	
	public NutritionDto findById(String id);
	
	public void updateNS(NutritionDto nutritionDto);
	
	public void insertNS(NutritionDto nutritionDto);
	
	public void deleteNutr(String nutrid);
	
	/**
	 * 查询所有营养
	 * @author YIn
	 * @time:2015年12月30日 下午2:53:57
	 * @param nutritionDto
	 * @param limitPageDto
	 * @return
	 */
	public List<NutritionDto> findListByPage(NutritionDto nutritionDto,LimitPageDto limitPageDto);
	
	//更新营养信息并且更新营养参数
	
	public void updateNSAndPro(NutritionDto nutritionDto);
	
}
