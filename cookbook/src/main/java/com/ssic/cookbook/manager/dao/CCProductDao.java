/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssic.cookbook.manager.dto.CategoryClassProductDto;
import com.ssic.cookbook.manager.mapper.CategoryClassProductMapper;
import com.ssic.cookbook.manager.pojo.CategoryClassProduct;
import com.ssic.cookbook.manager.pojo.CategoryClassProductExample;
import com.ssic.cookbook.manager.pojo.CategoryClassProductExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

/**
 * @author wk.s
 * <br> the dao descripted as CategoryClassProductDao
 *
 */
@Repository
public class CCProductDao {

	@Autowired
	private CategoryClassProductMapper cCProductMapper;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(CategoryClassProductDto dto){
		
		boolean flag = false;
		try {
			CategoryClassProduct record = BeanUtils.createBeanByTarget(dto, CategoryClassProduct.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = cCProductMapper.insertSelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("新增CategoryClassProduct数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * <b>更新</b>
	 * <br>以id为条件，其它fields为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(CategoryClassProductDto dto){
		
		boolean flag = false;
		try {
			CategoryClassProduct record = BeanUtils.createBeanByTarget(dto, CategoryClassProduct.class);
			record.setLastUpdateTime(new Date());
			int i = cCProductMapper.updateByPrimaryKeySelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("更新CategoryClassProduct数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(CategoryClassProductDto dto){
		
		boolean flag = false;
		try {
			CategoryClassProduct record = new CategoryClassProduct();
			record.setStat(CookbookFields.DisEnable);
			record.setLastUpdateTime(new Date());
			CategoryClassProductExample example = new CategoryClassProductExample();
			Criteria criteria = example.createCriteria();
			CategoryClassProduct param = BeanUtils.createBeanByTarget(dto, CategoryClassProduct.class);
			assemble(param, criteria);
			cCProductMapper.updateByExample(record, example);
			flag = true;
		} catch (Exception e) {
			log.error("逻辑删除CategoryClassProduct数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 组装条件
	 */
	public void assemble(CategoryClassProduct param, Criteria criteria){
		
		String id = param.getId();
		if(StringUtils.isNotBlank(id)) criteria.andIdEqualTo(id);
		
		String categoryClassId = param.getCategoryClassId();
		if(StringUtils.isNotBlank(categoryClassId)) criteria.andCategoryClassIdEqualTo(categoryClassId);
		
		Date createTime = param.getCreateTime();
		if(createTime != null) criteria.andCreateTimeEqualTo(createTime);
		
		Date lastUpdateTime = param.getLastUpdateTime();
		if(lastUpdateTime != null) criteria.andLastUpdateTimeEqualTo(lastUpdateTime);
		
		String productId = param.getProductId();
		if(StringUtils.isNotBlank(productId)) criteria.andProductIdEqualTo(productId);
		
		Integer stat = param.getStat();
		if(stat != null) criteria.andStatEqualTo(stat);
	}
}
