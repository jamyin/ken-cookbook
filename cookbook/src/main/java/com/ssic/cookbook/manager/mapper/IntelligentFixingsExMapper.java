package com.ssic.cookbook.manager.mapper;

import java.util.List;

import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;

public interface IntelligentFixingsExMapper {

	List<IntelligentFixingsDto> findIntelligentFixings(IntelligentFixingsDto dto);

	int findCount(IntelligentFixingsDto dto); 
}