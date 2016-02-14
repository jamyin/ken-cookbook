package com.ssic.cookbook.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dao.FixingsResultDao;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.service.IFixingsResultService;

@Service
public class FixingsResultServiceImpl implements IFixingsResultService {

	
	private FixingsResultDao fixingsResultDao;
	
	public List<FixingsResultDto> findFixingsResultAllByPage(Page page) {
		//获取
		return null;
	}

	
	public Integer findFixingsResultCount() {
		return fixingsResultDao.findFixingsResultCount();
	}

}

