package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.BrandDto;
import com.ssic.cookbook.manager.dto.LimitPageDto;

public interface IBrandService {

	public List<BrandDto> findForList(BrandDto brandDto);
	
	public BrandDto findById(String id);
	
	public void updateBS(BrandDto brandDto);
	
	public void insertBS(BrandDto brandDto);
	
	public void deleteBS(String id);
	
	public int findForCount(BrandDto brandDto);
	
	public List<BrandDto> findForList(BrandDto brandDto,LimitPageDto limitPageDto);
}
