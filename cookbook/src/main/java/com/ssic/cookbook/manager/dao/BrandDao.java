package com.ssic.cookbook.manager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.BrandMapper;
import com.ssic.cookbook.manager.pojo.Brand;
import com.ssic.cookbook.manager.pojo.BrandExample;
import com.ssic.cookbook.manager.pojo.BrandExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;

@Repository
public class BrandDao {

	@Autowired
	private BrandMapper mapper;
	
	public List<Brand> findForList(Brand param){
		BrandExample example = new BrandExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getName())){
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example);
	}
	
	public List<Brand> findForList(Brand param,LimitPageDto limitPageDto){
		BrandExample example = new BrandExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getName())){
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		 if(limitPageDto!=null && !StringUtils.isEmpty(limitPageDto.getStar()) &&  !StringUtils.isEmpty(limitPageDto.getEnd()) ){
	    	 example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
	                 + limitPageDto.getEnd());
	    }
		return mapper.selectByExample(example);
	}
	
	
	public int findForCount(Brand param){
		BrandExample example = new BrandExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getName())){
			criteria.andNameEqualTo(param.getName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.countByExample(example);
	}
	
	
	public void deleteBS(Brand param){
		param.setStat(CookbookFields.DisEnable);
		mapper.updateByPrimaryKeySelective(param);
	}
	
	
	public Brand findById(String id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public void updateById(Brand param){
		param.setStat(CookbookFields.Enable);
		mapper.updateByPrimaryKeySelective(param);
	}
	
	public void insertBrand(Brand param){
		param.setStat(CookbookFields.Enable);
		mapper.insert(param);
	}
	
	
	
}
