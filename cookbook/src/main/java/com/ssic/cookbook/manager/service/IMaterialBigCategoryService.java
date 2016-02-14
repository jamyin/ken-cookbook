package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;

public interface IMaterialBigCategoryService {

	public List<MaterialBigCategoryDto> findForList(MaterialBigCategoryDto materialBigCategoryDto);
	
	public MaterialBigCategoryDto findById(String id);
	
	public void updateMBC(MaterialBigCategoryDto materialBigCategoryDto);
	
	public void insertMBC(MaterialBigCategoryDto materialBigCategoryDto);
	
	public void delteMBC(String bigId);
	
	public int findForCount(MaterialBigCategoryDto materialBigCategoryDto);
	
	public List<MaterialBigCategoryDto> findForList(MaterialBigCategoryDto materialBigCategoryDto,LimitPageDto limitPageDto);

}
