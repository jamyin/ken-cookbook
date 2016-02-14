package com.ssic.cookbook.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssic.cookbook.manager.dao.MaterialBigCategoryDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;
import com.ssic.cookbook.manager.pojo.MaterialBigCategory;
import com.ssic.cookbook.manager.service.IBrandService;
import com.ssic.cookbook.manager.service.IMaterialBigCategoryService;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.util.BeanUtils;

@Service
public class MaterialBigCategoryServiceImpl implements IMaterialBigCategoryService{

	@Autowired
	private MaterialBigCategoryDao materialBigCategoryDao;
	
	
	public List<MaterialBigCategoryDto> findForList(
			MaterialBigCategoryDto materialBigCategoryDto) {
		// TODO Auto-generated method stub
		MaterialBigCategory param = new MaterialBigCategory();
		BeanUtils.copyProperties(materialBigCategoryDto, param);
		List<MaterialBigCategory> list = materialBigCategoryDao.findForList(param);
		List<MaterialBigCategoryDto> listdto = BeanUtils.createBeanListByTarget(list, MaterialBigCategoryDto.class);
		return listdto;
	}

	public MaterialBigCategoryDto findById(String id) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = materialBigCategoryDao.findById(id);
		MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
		BeanUtils.copyProperties(materialBigCategory, materialBigCategoryDto);
		return materialBigCategoryDto;
	}

	public void updateMBC(MaterialBigCategoryDto materialBigCategoryDto) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = new MaterialBigCategory();
		BeanUtils.copyProperties(materialBigCategoryDto, materialBigCategory);
		materialBigCategoryDao.updateById(materialBigCategory);
	}

	public void insertMBC(MaterialBigCategoryDto materialBigCategoryDto) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = new MaterialBigCategory();
		BeanUtils.copyProperties(materialBigCategoryDto, materialBigCategory);
		materialBigCategoryDao.insertMB(materialBigCategory);
	}

	public void delteMBC(String bigId) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = new MaterialBigCategory();
		materialBigCategory.setId(bigId);
		materialBigCategoryDao.deleteMB(materialBigCategory);
	}

	public int findForCount(MaterialBigCategoryDto materialBigCategoryDto) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = new MaterialBigCategory();
		BeanUtils.copyProperties(materialBigCategoryDto, materialBigCategory);
		return materialBigCategoryDao.findForCount(materialBigCategory);
	}

	public List<MaterialBigCategoryDto> findForList(
			MaterialBigCategoryDto materialBigCategoryDto,
			LimitPageDto limitPageDto) {
		// TODO Auto-generated method stub
		MaterialBigCategory materialBigCategory = new MaterialBigCategory();
		BeanUtils.copyProperties(materialBigCategoryDto, materialBigCategory);
		List<MaterialBigCategory> list= materialBigCategoryDao.findForList(materialBigCategory, limitPageDto);
		List<MaterialBigCategoryDto> listdto = BeanUtils.createBeanListByTarget(list, MaterialBigCategoryDto.class);
		return listdto;
	}

}
