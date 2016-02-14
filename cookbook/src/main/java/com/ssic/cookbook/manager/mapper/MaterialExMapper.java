package com.ssic.cookbook.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.NutritionDto;

public interface MaterialExMapper {

	List<MaterialDto> findAll (@Param("materialDto") MaterialDto materialDto);
	
	int findCount(@Param("materialDto") MaterialDto materialDto);
	
	List<NutritionDto> findNuByMid(@Param("mid") String mid);
	
	void deleteMaNuByMid(@Param("mid") String mid);
		
}
