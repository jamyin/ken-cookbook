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
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.service.IProductCategoryService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;
import com.ssic.util.BeanUtils;

/**		
 * <p>Title: CategoryController </p>
 * <p>Description: 成品菜-类别controller</p>
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
@RequestMapping("/categoryController")
public class CategoryController extends BaseController
{
    @Autowired
    private IProductCategoryService categoryService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IProductService productService;

    /**
     * 跳转到新增成品菜-类别页面
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
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setName(name);
        //查询数量
        int count = categoryService.findCount(productCategoryDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductCategoryDto> categoryList =
            categoryService.findListByPage(productCategoryDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductCategoryDto dto : categoryList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(categoryList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("categoryList", categoryList);
        mv.setViewName("cookbook/category/category_list");
        return mv;
    }

    /**
     * 跳转到新增类别页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductCategoryDto productCategoryDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        productCategoryDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/category/category_add");
        mv.addObject("productCategoryDto", productCategoryDto);
        return mv;
    }

    /**
     * 跳转到编辑类别页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductCategoryDto productCategoryDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductCategoryDto categoryDto = categoryService.findById(productCategoryDto.getId());
        mv.addObject("categoryDto", categoryDto);
        mv.setViewName("cookbook/category/category_edit");
        return mv;
    }

    /**
     * 保存成品菜类别
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductCategoryDto productCategoryDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(productCategoryDto.getName()))
        {
            ProductCategoryDto categoryDto = categoryService.findByName(productCategoryDto.getName());
            if (categoryDto != null && !categoryDto.getId().equals(productCategoryDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("类别:[" + productCategoryDto.getName() + "]已经存在，请勿编辑重复类别");
                j.setObj(productCategoryDto);
                return j;
            }
            else
            {
                //添加菜品类别
                categoryService.edit(productCategoryDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑类别成功");
        j.setObj(productCategoryDto);
        return j;
    }

    /**
     * 保存成品菜类别
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductCategoryDto productCategoryDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(productCategoryDto.getName()))
        {
            ProductCategoryDto categoryDto = categoryService.findByName(productCategoryDto.getName());
            if (categoryDto != null)
            {
                j.setSuccess(false);
                j.setMsg("类别:[" + productCategoryDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(productCategoryDto);
                return j;
            }
            else
            {
                //添加菜品类别
                categoryService.add(productCategoryDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增类别成功");
        j.setObj(productCategoryDto);
        return j;
    }

    /**
     * 
     * delete：删除类别
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
            productDto.setProductCategoryId(id);
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
                j.setMsg("该类别被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductCategoryDto categoryDto = categoryService.findById(id);
            if (categoryDto != null)
            {
                categoryDto.setStat(CookbookFields.DisEnable);
                categoryService.delete(categoryDto);
                j.setMsg("删除类别成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除类别失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新类别下拉框
    @RequestMapping("/reloadCategory")
    @ResponseBody
    public List<ProductCategoryDto> reloadCategory()
    {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategoryDto> categoryList = categoryService.findListByPage(productCategoryDto, null);
        return categoryList;
    }
}
