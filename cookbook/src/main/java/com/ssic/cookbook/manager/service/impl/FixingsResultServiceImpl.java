package com.ssic.cookbook.manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dao.FixingsResultDao;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.pojo.FixingsResult;
import com.ssic.cookbook.manager.service.IFixingsResultService;
import com.ssic.util.BeanUtils;

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

	/**
	 * @author YIn
	 * @time:2016年3月21日 下午3:19:05
	 */
	@Override
	public Integer updateFixingsResult(FixingsResultDto dto) {
		FixingsResult fixingsResult = BeanUtils.createBeanByTarget(dto, FixingsResult.class);
		return fixingsResultDao.updateFixingsResult(fixingsResult);
	}

	/**
	 * @author YIn
	 * @time:2016年3月21日 下午3:28:00
	 */
	@Override
	public FixingsResult findFixingsResultById(FixingsResultDto dto) {
		FixingsResult fixingsResult = BeanUtils.createBeanByTarget(dto, FixingsResult.class);
		return fixingsResultDao.findFixingsResultById(fixingsResult);
	}

}

