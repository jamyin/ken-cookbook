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

import com.ssic.cookbook.manager.dao.ProductColorDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductCategory;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.service.IProductColorService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductColorServiceImpl </p>
 * <p>Description: 成品菜所属颜色Service实现层</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午1:33:43	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午1:33:43</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductColorServiceImpl implements IProductColorService
{
    @Autowired
    private ProductColorDao colorDao;

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findListByPage(com.ssic.cookbook.manager.dto.ProductDto)   
    */
   
    public List<ProductColorDto> findListByPage(ProductColorDto productColorDto,LimitPageDto limitPageDto)
    {
        ProductColor productColor = new ProductColor();
        BeanUtils.copyProperties(productColorDto, productColor);
        List<ProductColorDto> listDto = new ArrayList<ProductColorDto>();
        List<ProductColor> list = colorDao.findForList(productColor,limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductColorDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductService#findById(java.lang.String)   
    */

    public ProductColorDto findById(String productId)
    {
        ProductColorDto dto = new ProductColorDto();
        ProductColor productColor = colorDao.findById(productId);
        if (productColor != null)
        {
            dto = BeanUtils.createBeanByTarget(productColor, ProductColorDto.class);
        }
        return dto;
    }
    
    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */
   
     public void add(ProductColorDto productColorDto)
     {
         ProductColor productColor = new ProductColor();
         BeanUtils.copyProperties(productColorDto, productColor);
         productColor.setStat(DataStatus.ENABLED);
         productColor.setCreateTime(new Date());
         productColor.setLastUpdateTime(new Date());
         colorDao.insert(productColor);

     }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductColorService#edit(com.ssic.cookbook.manager.dto.ProductColorDto)   
     */
   
    public void edit(ProductColorDto productColorDto)
    {
        ProductColor productColor = new ProductColor();
        BeanUtils.copyProperties(productColorDto, productColor);
        colorDao.update(productColor);
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductColorService#delete(com.ssic.cookbook.manager.dto.ProductColorDto)   
     */
 
    public void delete(ProductColorDto productColorDto)
    {
        ProductColor productColor = new ProductColor();
        BeanUtils.copyProperties(productColorDto, productColor);
        colorDao.update(productColor);
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductColorService#findByName(java.lang.String)   
     */
    public ProductColorDto findByName(String name)
    {
        ProductColor productColor = new ProductColor();
        productColor.setName(name);
        List<ProductColor> list = colorDao.findByName(productColor);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductColorDto dto = new ProductColorDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductColorService#findCount(com.ssic.cookbook.manager.dto.ProductColorDto)   
     */
   
    public int findCount(ProductColorDto productColorDto)
    {
        ProductColor productColor = new ProductColor();
        BeanUtils.copyProperties(productColorDto, productColor);
        return colorDao.findCount(productColor);
    }
}
