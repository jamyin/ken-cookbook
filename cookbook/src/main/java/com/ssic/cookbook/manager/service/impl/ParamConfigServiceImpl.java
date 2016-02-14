package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dao.ParamConfigDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.pojo.ParamConfig;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.util.BeanUtils;

@Service
public class ParamConfigServiceImpl implements IParamConfigService{

	@Autowired
	private ParamConfigDao paramConfigDao;
	
	public List<ParamConfigDto> findForList(ParamConfigDto paramConfigDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		List<ParamConfig> list = new ArrayList<ParamConfig>();
		list= paramConfigDao.findForList(paramConfig);	
		List<ParamConfigDto> listdto = BeanUtils.createBeanListByTarget(list, ParamConfigDto.class);
		return listdto;
	}
	
	
	

	public ParamConfigDto findById(String id) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig =  paramConfigDao.findById(id);
		ParamConfigDto paramConfigDto = new ParamConfigDto();
		BeanUtils.copyProperties(paramConfig, paramConfigDto);
		return paramConfigDto;
	}

	public ParamConfigDto findByTypeAndPid(String pid, String pType) {
		// TODO Auto-generated method stub
		ParamConfig paramco = paramConfigDao.findByTypeAndPid(pid, pType);
		ParamConfigDto paramConfigDto = new ParamConfigDto();
		BeanUtils.copyProperties(paramco, paramConfigDto);
		return paramConfigDto;
	}

	public void insertPam(ParamConfigDto paramConfigDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		paramConfigDao.insertPam(paramConfig);
	}

	public void updatePam(ParamConfigDto paramConfigDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		paramConfigDao.updateById(paramConfig);
	}




	public List<ParamConfigDto> findByList(ParamConfigDto paramConfigDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		List<ParamConfig> list = new ArrayList<ParamConfig>();
		list = paramConfigDao.findForList(paramConfig, paramConfigDto.getIds());
		List<ParamConfigDto> listdto = BeanUtils.createBeanListByTarget(list, ParamConfigDto.class);
		return listdto;
	}




	public int findCountForList(ParamConfigDto paramConfigDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		return paramConfigDao.findCountForList(paramConfig);
	}




	public List<ParamConfigDto> findForList(ParamConfigDto paramConfigDto,
			LimitPageDto limitPageDto) {
		// TODO Auto-generated method stub
		ParamConfig paramConfig = new ParamConfig();
		BeanUtils.copyProperties(paramConfigDto, paramConfig);
		List<ParamConfig> list = new ArrayList<ParamConfig>();
		list= paramConfigDao.findForList(paramConfig,limitPageDto);	
		List<ParamConfigDto> listdto = BeanUtils.createBeanListByTarget(list, ParamConfigDto.class);
		return listdto;
	}




	public void deleteNutr(String pid) {
		// TODO Auto-generated method stub
		paramConfigDao.deleteNutr(pid);
	}


}
