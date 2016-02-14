package com.ssic.cookbook.manager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.MaterialBigCategoryMapper;
import com.ssic.cookbook.manager.pojo.MaterialBigCategory;
import com.ssic.cookbook.manager.pojo.MaterialBigCategoryExample;
import com.ssic.cookbook.manager.pojo.MaterialBigCategoryExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;

@Repository
public class MaterialBigCategoryDao {

	@Autowired
	private MaterialBigCategoryMapper mapper;

	public List<MaterialBigCategory> findForList(MaterialBigCategory param) {
		MaterialBigCategoryExample example = new MaterialBigCategoryExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(param.getId())) {
			criteria.andIdEqualTo(param.getId());
		}
		if (!StringUtils.isEmpty(param.getName())) {
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
	
		return mapper.selectByExample(example);
	}

	public List<MaterialBigCategory> findForList(MaterialBigCategory param,LimitPageDto limitPageDto){
		MaterialBigCategoryExample example = new MaterialBigCategoryExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(param.getId())) {
			criteria.andIdEqualTo(param.getId());
		}
		if (!StringUtils.isEmpty(param.getName())) {
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
	    if(limitPageDto!=null && !StringUtils.isEmpty(limitPageDto.getStar()) &&  !StringUtils.isEmpty(limitPageDto.getEnd()) ){
	    	 example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
	                 + limitPageDto.getEnd());
	    }
		return mapper.selectByExample(example);
	}
	
	public int findForCount(MaterialBigCategory param){
		MaterialBigCategoryExample example = new MaterialBigCategoryExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(param.getId())) {
			criteria.andIdEqualTo(param.getId());
		}
		if (!StringUtils.isEmpty(param.getName())) {
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.countByExample(example);
	}
	
	
	public MaterialBigCategory findById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	public void updateById(MaterialBigCategory param){
		param.setStat(CookbookFields.Enable);
		mapper.updateByPrimaryKeySelective(param);
	}
	
	public void insertMB(MaterialBigCategory param){
		param.setStat(CookbookFields.Enable);
		mapper.insert(param);
	}

	public void deleteMB(MaterialBigCategory param){
		param.setStat(CookbookFields.DisEnable);
		mapper.updateByPrimaryKeySelective(param);
	}
	
}
