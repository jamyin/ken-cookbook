package com.ssic.cookbook.manager.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.ParamConfigMapper;
import com.ssic.cookbook.manager.pojo.ParamConfig;
import com.ssic.cookbook.manager.pojo.ParamConfigExample;
import com.ssic.cookbook.manager.pojo.ParamConfigExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;

@Repository
public class ParamConfigDao {

	@Autowired
	private ParamConfigMapper mapper;
	
	
	public List<ParamConfig> findForList(ParamConfig param){
		ParamConfigExample example = new ParamConfigExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getParamType())){
			criteria.andParamTypeEqualTo(param.getParamType());
		}
		if(!StringUtils.isEmpty(param.getParamId())){
			criteria.andParamIdEqualTo(param.getParamId());
		}
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getParamName())){
			criteria.andParamNameEqualTo(param.getParamName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example);
	}
	
	public List<ParamConfig> findForList(ParamConfig param,LimitPageDto limitPageDto){
		ParamConfigExample example = new ParamConfigExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getParamType())){
			criteria.andParamTypeEqualTo(param.getParamType());
		}
		if(!StringUtils.isEmpty(param.getParamId())){
			criteria.andParamIdEqualTo(param.getParamId());
		}
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getParamName())){
			criteria.andParamNameEqualTo(param.getParamName());
		}
		 if(limitPageDto!=null && !StringUtils.isEmpty(limitPageDto.getStar()) &&  !StringUtils.isEmpty(limitPageDto.getEnd()) ){
	    	 example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
	                 + limitPageDto.getEnd());
	    }
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example);
	}
	
	public int findCountForList(ParamConfig param){
		ParamConfigExample example = new ParamConfigExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getParamType())){
			criteria.andParamTypeEqualTo(param.getParamType());
		}
		if(!StringUtils.isEmpty(param.getParamId())){
			criteria.andParamIdEqualTo(param.getParamId());
		}
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getParamName())){
			criteria.andParamNameEqualTo(param.getParamName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.countByExample(example);
	}
	
	
	public List<ParamConfig> findForList(ParamConfig param,List<String> ids){
		ParamConfigExample example = new ParamConfigExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getParamType())){
			criteria.andParamTypeEqualTo(param.getParamType());
		}
		if(!StringUtils.isEmpty(param.getParamId())){
			criteria.andParamIdEqualTo(param.getParamId());
		}
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(ids!=null && ids.size()!=0){
			criteria.andIdIn(ids);
		}
		if(!StringUtils.isEmpty(param.getParamName())){
			criteria.andParamNameEqualTo(param.getParamName());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example);
	}
	
	public void updateById(ParamConfig param){
		param.setStat(CookbookFields.Enable);
		 mapper.updateByPrimaryKeySelective(param);
	}
	
	public ParamConfig findById(String id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public ParamConfig findByTypeAndPid(String pid,String pType){
		ParamConfigExample example = new ParamConfigExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(pType)){
			criteria.andParamTypeEqualTo(pType);
		}
		if(!StringUtils.isEmpty(pid)){
			criteria.andParamIdEqualTo(pid);
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example).get(0);
	}
	
	public void insertPam(ParamConfig paramConfig){
		paramConfig.setStat(CookbookFields.Enable);
		mapper.insert(paramConfig);
	}
	
	public void deleteNutr(String pid){
		mapper.deleteByPrimaryKey(pid);
	}
	
}
