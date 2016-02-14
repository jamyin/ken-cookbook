package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.ColorDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsColorExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsColorMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsColor;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsColorExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜颜色关系表
 * @author YIn
 * @time:2016年1月6日 下午5:19:57
 * @ClassName: ColorDao
 * @Description: TODO
 * @
 */
@Repository
public class ColorDao extends MyBatisBaseDao<IntelligentFixingsColor>
{
	@Autowired
    @Getter
    private IntelligentFixingsColorMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsColorExMapper mappers;
    /**
     * 
     * @author YIn
     * @time:2016年1月6日 下午5:20:12
     * @param c
     * @return
     */
	public boolean add(ColorDto c) {
		boolean flag = false;
		IntelligentFixingsColor record = BeanUtils.createBeanByTarget(c, IntelligentFixingsColor.class);
			record.setId(UUID.randomUUID()+"");
			record.setCreateTime(new Date());
			record.setStat(1);
			int i = mapper.insertSelective(record);
			if(i == 1){
					flag = true;
				}else{
					flag = false;	
				}
			return flag;
	}
	
	/**
	 * @author YIn
	 * @time:2016年1月8日 下午2:24:09
	 * @param id
	 * @return
	 */
	public List<IntelligentFixingsColor> findColor(String id) {
		IntelligentFixingsColorExample example = new IntelligentFixingsColorExample();
		IntelligentFixingsColorExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsColor> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月2日 上午9:00:07
	 */
	public boolean addList(List<ColorDto> color) {
		
		return mappers.addList(color);
	}
}
