package com.ssic.cookbook.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssic.cookbook.manager.dto.FixingsResultDto;


public interface IndependentFixingsExMapper
{   
    List<FixingsResultDto> findAllIntelligentProduct(@Param("FixingsResultDto") FixingsResultDto fixingsResultDto);

    List<FixingsResultDto> findAllIntelligentProductType();

    List<FixingsResultDto> findAllIntelligentProductName();

    int findCount();

    void deleteIntelligent(@Param("FixingsResultDto") FixingsResultDto fixingsResultDto);
}

