package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dto.FixingsResultDto;

public interface IFixingsResultService {

	/**
	 * findFixingsResultAllByPage：获取配菜结果列表
	 * @param page 分页参数
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月19日 下午1:58:34
	 */
	public List<FixingsResultDto> findFixingsResultAllByPage(Page page);
	
	
	/**
	 * findFixingsResultCount：获取配菜结果总数
	 * @param page
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月19日 下午1:59:40
	 */
	public Integer findFixingsResultCount();
	
	
}

