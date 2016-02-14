package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;

public interface IParamConfigService {

	public List<ParamConfigDto> findForList(ParamConfigDto paramConfigDto);
	
	public ParamConfigDto findById(String id);
	
	public ParamConfigDto findByTypeAndPid(String pid,String pType);
	
	public void insertPam(ParamConfigDto paramConfigDto);
	
	public void updatePam(ParamConfigDto paramConfigDto);
	
	public List<ParamConfigDto> findByList(ParamConfigDto paramConfigDto);
	
	public int findCountForList(ParamConfigDto paramConfigDto);
	
    public List<ParamConfigDto> findForList(ParamConfigDto paramConfigDto,LimitPageDto limitPageDto );
    
    public void deleteNutr(String pid);
}
