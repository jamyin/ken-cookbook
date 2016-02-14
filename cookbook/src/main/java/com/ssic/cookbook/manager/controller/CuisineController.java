/**
 * 
 */
package com.ssic.cookbook.manager.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.service.IProductCuisineService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;

/**     
 * <p>Title: cuisineController </p>
 * <p>Description: 成品菜-烹饪controller</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博   
 * @date 2015年12月22日 上午9:06:30  
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月22日 上午9:06:30</p>
 * <p>修改备注：</p>
 */
@Controller
@RequestMapping("/cuisineController")
public class CuisineController extends BaseController
{
    @Autowired
    private IProductCuisineService cuisineService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IProductService productService;

    /**
     * 跳转到新增成品菜-烹饪页面
     */
    @RequestMapping("/list")
    public ModelAndView list(Page page) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        //查询条件放入查询dto对象中
        String name = pd.getString("name");
        ProductCuisineDto ProductCuisineDto = new ProductCuisineDto();
        ProductCuisineDto.setName(name);
        //查询数量
        int count = cuisineService.findCount(ProductCuisineDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductCuisineDto> cuisineList =
            cuisineService.findListByPage(ProductCuisineDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductCuisineDto dto : cuisineList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(cuisineList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("cuisineList", cuisineList);
        mv.setViewName("cookbook/cuisine/cuisine_list");
        return mv;
    }

    /**
     * 跳转到新增烹饪页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductCuisineDto ProductCuisineDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductCuisineDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/cuisine/cuisine_add");
        mv.addObject("productCuisineDto", ProductCuisineDto);
        return mv;
    }

    /**
     * 跳转到编辑烹饪页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductCuisineDto ProductCuisineDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductCuisineDto cuisineDto = cuisineService.findById(ProductCuisineDto.getId());
        mv.addObject("cuisineDto", cuisineDto);
        mv.setViewName("cookbook/cuisine/cuisine_edit");
        return mv;
    }

    /**
     * 保存成品菜烹饪
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductCuisineDto ProductCuisineDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductCuisineDto.getName()))
        {
            ProductCuisineDto cuisineDto = cuisineService.findByName(ProductCuisineDto.getName());
            if (cuisineDto != null && !cuisineDto.getId().equals(ProductCuisineDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("烹饪:[" + ProductCuisineDto.getName() + "]已经存在，请勿编辑重复烹饪");
                j.setObj(ProductCuisineDto);
                return j;
            }
            else
            {
                //添加菜品烹饪
                cuisineService.edit(ProductCuisineDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑烹饪成功");
        j.setObj(ProductCuisineDto);
        return j;
    }

    /**
     * 保存成品菜烹饪
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductCuisineDto ProductCuisineDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductCuisineDto.getName()))
        {
            ProductCuisineDto cuisineDto = cuisineService.findByName(ProductCuisineDto.getName());
            if (cuisineDto != null)
            {
                j.setSuccess(false);
                j.setMsg("烹饪:[" + ProductCuisineDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(ProductCuisineDto);
                return j;
            }
            else
            {
                //添加菜品烹饪
                cuisineService.add(ProductCuisineDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增烹饪成功");
        j.setObj(ProductCuisineDto);
        return j;
    }

    /**
     * 
     * delete：删除烹饪
     * @param id
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年10月22日 上午9:55:02
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(String id)
    {
        Json j = new Json();
        {
            
            ProductDto productDto = new ProductDto();
            productDto.setProductCuisineId(id);
            List<ProductDto> listProduct =  productService.findAll(productDto);
            if (!CollectionUtils.isEmpty(listProduct))
            {
                String name = "";
                int i = 0;
                for (ProductDto p : listProduct)
                {
                    if (i > 0)
                    {
                        name += ",";
                    }
                    name += p.getName();
                    i++;
                }
                j.setMsg("该烹饪被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductCuisineDto cuisineDto = cuisineService.findById(id);
            if (cuisineDto != null)
            {
                cuisineDto.setStat(CookbookFields.DisEnable);
                cuisineService.delete(cuisineDto);
                j.setMsg("删除烹饪成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除烹饪失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新烹饪下拉框
    @RequestMapping("/reloadCuisine")
    @ResponseBody
    public List<ProductCuisineDto> reloadCuisine()
    {
        ProductCuisineDto ProductCuisineDto = new ProductCuisineDto();
        List<ProductCuisineDto> cuisineList = cuisineService.findListByPage(ProductCuisineDto, null);
        return cuisineList;
    }
}
