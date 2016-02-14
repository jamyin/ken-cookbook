package com.ssic.cookbook.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.dto.NutritionDto;

public interface NutritionExMapper {
	 List<IntelligentFixingsDto> findSmart(IntelligentFixingsDto dto);
	 
	 void updateNutrByPid (@Param("nutritionDto") NutritionDto nutritionDto);
	 
}