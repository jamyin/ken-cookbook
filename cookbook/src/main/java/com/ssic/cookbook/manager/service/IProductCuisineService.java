/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;

/**		
 * <p>Title: IProductCuisineService </p>
 * <p>Description: 成品菜烹饪方式Service层</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:02:43	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:02:43</p>
 * <p>修改备注：</p>
 */
public interface IProductCuisineService
{
    /**     
     * findListByPage：查找所有的成品菜烹饪方式
     * @param productCuisineDto
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductCuisineDto> findListByPage(ProductCuisineDto productCuisineDto,LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜烹饪方式id查找对应的成品菜烹饪方式
     * @param productCuisineId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductCuisineDto findById(String productCuisineId);
    
    /**     
     * add：一句话描述方法功能
     * @param productCuisineDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductCuisineDto productCuisineDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productCuisineDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductCuisineDto productCuisineDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productCuisineDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductCuisineDto productCuisineDto);
    
    /**     
     * findByName：通过烹饪名称查找成品菜-烹饪
     * @param name
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34   
     */
    public ProductCuisineDto findByName(String name);
    
    /**     
     * findCount：查找成品菜-烹饪数量
     * @param productCuisineDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductCuisineDto productCuisineDto);
}
