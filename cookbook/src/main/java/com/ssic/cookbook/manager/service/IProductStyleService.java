/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;

/**		
 * <p>Title: IProductStyleService </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:56:16	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:56:16</p>
 * <p>修改备注：</p>
 */
public interface IProductStyleService
{
    /**     
     * findListByPage：查找所有的成品菜菜系
     * @param ProductStyleDto
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductStyleDto> findListByPage(ProductStyleDto ProductStyleDto,LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜菜系id查找对应的成品菜菜系
     * @param ProductStyleId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductStyleDto findById(String productStyleId);
    
    /**     
     * add：一句话描述方法功能
     * @param productStyleDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductStyleDto productStyleDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productStyleDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductStyleDto productStyleDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productStyleDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductStyleDto productStyleDto);
    
    /**     
     * findByName：通过菜系名称查找成品菜-菜系
     * @param name
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34   
     */
    public ProductStyleDto findByName(String name);
    
    /**     
     * findCount：查找成品菜-菜系数量
     * @param productStyleDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductStyleDto productStyleDto);

}
