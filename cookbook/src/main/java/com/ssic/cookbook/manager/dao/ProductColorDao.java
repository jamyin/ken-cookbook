/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.ProductColorMapper;
import com.ssic.cookbook.manager.pojo.ProductCategory;
import com.ssic.cookbook.manager.pojo.ProductCategoryExample;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductColorExample;
import com.ssic.cookbook.manager.pojo.ProductColorExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;

/**		
 * <p>Title: ProductColorDao </p>
 * <p>Description: 成品菜所属颜色Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:00:27	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:00:27</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductColorDao extends MyBatisBaseDao<ProductColor>
{
    @Autowired
    @Getter
    private ProductColorMapper mapper;

    private static final Logger log = Logger.getLogger(ProductColorDao.class);

    public List<ProductColor> findForList(ProductColor param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductColor为空");
        }
        ProductColorExample example = new ProductColorExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //颜色名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
        //如果有分页对象;
        if (limitPageDto != null && !StringUtils.isEmpty(limitPageDto.getStar())
            && !StringUtils.isEmpty(limitPageDto.getEnd()))
        {
            example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
                + limitPageDto.getEnd());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
    }

    public ProductColor findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(ProductColor param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(ProductColor param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productColor
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:50:39	 
     */
    public void update(ProductColor productColor)
    {
        if (productColor != null && !StringUtils.isEmpty(productColor.getId()))
        {
            productColor.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productColor);
        }
    }

    /**     
     * findByName：通过颜色名称查找颜色对象
     * @param productColor
     * @return
     * @exception	
     * @author Administrator
     * @date 2015年12月22日 上午9:45:00	 
     */
    public List<ProductColor> findByName(ProductColor productColor)
    {
        ProductColorExample example = new ProductColorExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        if (!StringUtils.isEmpty(productColor.getName()))
        {
            criteria.andNameEqualTo(productColor.getName());
        }
        return mapper.selectByExample(example);
    }
    
    /**     
     * findCount：查找数量
     * @param category
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:02:29  
     */
    public int findCount(ProductColor param)
    {
        if (param == null)
        {
            log.error("参数ProductColor为空");
        }
        ProductColorExample example = new ProductColorExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //颜色名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
       
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.countByExample(example);
    }
}
