/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.ProductStyleDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.ProductShape;
import com.ssic.cookbook.manager.pojo.ProductStyle;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.service.IProductStyleService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductStyleServiceImpl </p>
 * <p>Description: 成品菜菜系Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:56:41	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:56:41</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductStyleServiceImpl implements IProductStyleService
{
    @Autowired
    private ProductStyleDao styleDao;

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductStyleService#findListByPage(com.ssic.cookbook.manager.dto.ProductStyleDto)   
     */
  
    public List<ProductStyleDto> findListByPage(ProductStyleDto productStyleDto, LimitPageDto limitPageDto)
    {
        ProductStyle productStyle = new ProductStyle();
        BeanUtils.copyProperties(productStyleDto, productStyle);
        List<ProductStyleDto> listDto = new ArrayList<ProductStyleDto>();
        List<ProductStyle> list = styleDao.findForList(productStyle, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductStyleDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductStyleService#findById(java.lang.String)   
    */
    public ProductStyleDto findById(String productStyleId)
    {
        ProductStyleDto dto = new ProductStyleDto();
        ProductStyle productStyle = styleDao.findById(productStyleId);
        if (productStyle != null)
        {
            dto = BeanUtils.createBeanByTarget(productStyle, ProductStyleDto.class);
        }
        return dto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */
   
    public void add(ProductStyleDto productStyleDto)
    {
        ProductStyle productStyle = new ProductStyle();
        BeanUtils.copyProperties(productStyleDto, productStyle);
        productStyle.setStat(DataStatus.ENABLED);
        productStyle.setCreateTime(new Date());
        productStyle.setLastUpdateTime(new Date());
        styleDao.insert(productStyle);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductStyleService#edit(com.ssic.cookbook.manager.dto.ProductStyleDto)   
    */
  
    public void edit(ProductStyleDto productStyleDto)
    {
        ProductStyle productStyle = new ProductStyle();
        BeanUtils.copyProperties(productStyleDto, productStyle);
        styleDao.update(productStyle);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductStyleService#delete(com.ssic.cookbook.manager.dto.ProductStyleDto)   
    */
  
    public void delete(ProductStyleDto productStyleDto)
    {
        ProductStyle productStyle = new ProductStyle();
        BeanUtils.copyProperties(productStyleDto, productStyle);
        styleDao.update(productStyle);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductStyleService#findByName(java.lang.String)   
    */
    public ProductStyleDto findByName(String name)
    {
        ProductStyle productStyle = new ProductStyle();
        productStyle.setName(name);
        List<ProductStyle> list = styleDao.findByName(productStyle);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductStyleDto dto = new ProductStyleDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductStyleService#findCount(com.ssic.cookbook.manager.dto.ProductStyleDto)   
     */
  
    public int findCount(ProductStyleDto productStyleDto)
    {
        ProductStyle productStyle = new ProductStyle();
        BeanUtils.copyProperties(productStyleDto, productStyle);
        return styleDao.findCount(productStyle);
    }
}
