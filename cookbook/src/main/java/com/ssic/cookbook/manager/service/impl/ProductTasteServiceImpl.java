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

import com.ssic.cookbook.manager.dao.ProductTasteDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductStyle;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.service.IProductTasteService;
import com.ssic.util.BeanUtils;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductTasteServiceImpl </p>
 * <p>Description: 成品菜口味Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午4:07:25	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午4:07:25</p>
 * <p>修改备注：</p>
 */
@Service
public class ProductTasteServiceImpl implements IProductTasteService
{

    @Autowired
    private ProductTasteDao tasteDao;

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductTasteService#findListByPage(com.ssic.cookbook.manager.dto.ProductTasteDto)   
     */
   /* @Cacheable(value = "default", key = "'cookbook.manager.dto.ProductTasteDtoList'")*/
    public List<ProductTasteDto> findListByPage(ProductTasteDto productTasteDto, LimitPageDto limitPageDto)
    {
        ProductTaste productTaste = new ProductTaste();
        BeanUtils.copyProperties(productTasteDto, productTaste);
        List<ProductTasteDto> listDto = new ArrayList<ProductTasteDto>();
        List<ProductTaste> list = tasteDao.findForList(productTaste, limitPageDto);
        if (!CollectionUtils.isEmpty(list))
        {
            listDto = BeanUtils.createBeanListByTarget(list, ProductTasteDto.class);
            return listDto;
        }
        return listDto;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductTasteService#findById(java.lang.String)   
    */
    public ProductTasteDto findById(String productTasteId)
    {
        ProductTasteDto dto = new ProductTasteDto();
        ProductTaste productTaste = tasteDao.findById(productTasteId);
        if (productTaste != null)
        {
            dto = BeanUtils.createBeanByTarget(productTaste, ProductTasteDto.class);
        }
        return dto;
    }

    /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IProductService#add(com.ssic.cookbook.manager.dto.ProductDto)   
     */
/*    @CacheEvict(value = "default", key = "'cookbook.manager.dto.ProductTasteDtoList'", beforeInvocation = true)*/
    public void add(ProductTasteDto productTasteDto)
    {
        ProductTaste productTaste = new ProductTaste();
        BeanUtils.copyProperties(productTasteDto, productTaste);
        productTaste.setStat(DataStatus.ENABLED);
        productTaste.setCreateTime(new Date());
        productTaste.setLastUpdateTime(new Date());
        tasteDao.insert(productTaste);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductTasteService#edit(com.ssic.cookbook.manager.dto.ProductTasteDto)   
    */
   
    public void edit(ProductTasteDto productTasteDto)
    {
        ProductTaste productTaste = new ProductTaste();
        BeanUtils.copyProperties(productTasteDto, productTaste);
        tasteDao.update(productTaste);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductTasteService#delete(com.ssic.cookbook.manager.dto.ProductTasteDto)   
    */
 
    public void delete(ProductTasteDto productTasteDto)
    {
        ProductTaste productTaste = new ProductTaste();
        BeanUtils.copyProperties(productTasteDto, productTaste);
        tasteDao.update(productTaste);

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductTasteService#findByName(java.lang.String)   
    */
    public ProductTasteDto findByName(String name)
    {
        ProductTaste productTaste = new ProductTaste();
        productTaste.setName(name);
        List<ProductTaste> list = tasteDao.findByName(productTaste);
        if (!CollectionUtils.isEmpty(list))
        {
            ProductTasteDto dto = new ProductTasteDto();
            BeanUtils.copyProperties(list.get(0), dto);
            return dto;
        }
        return null;
    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IProductTasteService#findCount(com.ssic.cookbook.manager.dto.ProductTasteDto)   
    */
 
    public int findCount(ProductTasteDto productTasteDto)
    {
        ProductTaste productTaste = new ProductTaste();
        BeanUtils.copyProperties(productTasteDto, productTaste);
        return tasteDao.findCount(productTaste);
    }
}
