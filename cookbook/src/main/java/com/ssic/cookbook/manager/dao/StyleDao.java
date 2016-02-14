package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.StyleDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsStyleExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsStyleMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsStyle;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsStyleExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜菜系关系表
 * @author YIn
 * @time:2016年1月6日 下午5:19:57
 * @ClassName: StyleDao
 * @Description: TODO
 * @
 */
@Repository
public class StyleDao extends MyBatisBaseDao<IntelligentFixingsStyle>
{
	@Autowired
    @Getter
    private IntelligentFixingsStyleMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsStyleExMapper mappers;
    /**
     * 
     * @author YIn
     * @time:2016年1月6日 下午5:20:12
     * @param c
     * @return
     */
	public boolean add(StyleDto c) {
		boolean flag = false;
		IntelligentFixingsStyle record = BeanUtils.createBeanByTarget(c, IntelligentFixingsStyle.class);
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
	 * @time:2016年1月8日 下午2:22:11
	 * @param id
	 * @return
	 */
	public List<IntelligentFixingsStyle> findStyle(String id) {
		IntelligentFixingsStyleExample example = new IntelligentFixingsStyleExample();
		IntelligentFixingsStyleExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsStyle> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月1日 下午8:46:04
	 */
	public boolean addList(List<StyleDto> style) {
		return mappers.addList(style);
	}
}
