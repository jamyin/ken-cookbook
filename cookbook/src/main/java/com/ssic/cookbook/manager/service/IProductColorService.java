/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;

/**		
 * <p>Title: IProductColorService </p>
 * <p>Description: 成品菜所属颜色Service层</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:32:28	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:32:28</p>
 * <p>修改备注：</p>
 */
public interface IProductColorService
{
    /**     
    * findListByPage：查找所有的成品菜颜色
    * @param productColorDto
    * @param limitPageDto
    * @return
    * @exception   
    * @author 刘博
    * @date 2015年12月16日 下午3:09:22   
    */
    public List<ProductColorDto> findListByPage(ProductColorDto productColorDto, LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜颜色id查找对应的成品菜颜色
     * @param productColorId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductColorDto findById(String productColorId);
    
    /**     
     * add：一句话描述方法功能
     * @param productColorDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductColorDto productColorDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productColorDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductColorDto productColorDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productColorDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductColorDto productColorDto);
    
    /**     
     * findByName：通过颜色名称查找成品菜颜色
     * @param name
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34   
     */
    public ProductColorDto findByName(String name);
    
    /**     
     * findCount：查找成品菜-颜色数量
     * @param productColorDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductColorDto productColorDto);
}
