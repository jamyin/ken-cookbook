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

import com.ssic.cookbook.manager.dao.ProductCuisineDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.ProductCategory;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductCuisine;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.service.IProductCuisineService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductCuisineServiceImpl </p>
 * <p>Description:  成品菜烹饪方式Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:03:44	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:03:44</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductCuisineServiceImpl implements IProductCuisineService
{
    @Autowired
    private ProductCuisineDao cuisineDao;

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findListByPage(com.ssic.cookbook.manager.dto.ProductDto)   
    */
   
    public List<ProductCuisineDto> findListByPage(ProductCuisineDto productCuisineDto,
        LimitPageDto limitPageDto)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        BeanUtils.copyProperties(productCuisineDto, productCuisine);
        List<ProductCuisineDto> listDto = new ArrayList<ProductCuisineDto>();
        List<ProductCuisine> list = cuisineDao.findForList(productCuisine, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductCuisineDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findById(java.lang.String)   
    */
    public ProductCuisineDto findById(String productCusineId)
    {
        ProductCuisineDto dto = new ProductCuisineDto();
        ProductCuisine productCuisine = cuisineDao.findById(productCusineId);
        if (productCuisine != null)
        {
            dto = BeanUtils.createBeanByTarget(productCuisine, ProductCuisineDto.class);
        }
        return dto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */

    public void add(ProductCuisineDto productCuisineDto)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        BeanUtils.copyProperties(productCuisineDto, productCuisine);
        productCuisine.setStat(DataStatus.ENABLED);
        productCuisine.setCreateTime(new Date());
        productCuisine.setLastUpdateTime(new Date());
        cuisineDao.insert(productCuisine);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCuisineService#edit(com.ssic.cookbook.manager.dto.ProductCuisineDto)   
    */
  
    public void edit(ProductCuisineDto productCuisineDto)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        BeanUtils.copyProperties(productCuisineDto, productCuisine);
        cuisineDao.update(productCuisine);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCuisineService#delete(com.ssic.cookbook.manager.dto.ProductCuisineDto)   
    */
    
    public void delete(ProductCuisineDto productCuisineDto)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        BeanUtils.copyProperties(productCuisineDto, productCuisine);
        cuisineDao.update(productCuisine);
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductCuisineService#findByName(java.lang.String)   
    */
    public ProductCuisineDto findByName(String name)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        productCuisine.setName(name);
        List<ProductCuisine> list = cuisineDao.findByName(productCuisine);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductCuisineDto dto = new ProductCuisineDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductCuisineService#findCount(com.ssic.cookbook.manager.dto.ProductCuisineDto)   
     */
   
    public int findCount(ProductCuisineDto productCuisineDto)
    {
        ProductCuisine productCuisine = new ProductCuisine();
        BeanUtils.copyProperties(productCuisineDto, productCuisine);
        return cuisineDao.findCount(productCuisine);
    }
}
