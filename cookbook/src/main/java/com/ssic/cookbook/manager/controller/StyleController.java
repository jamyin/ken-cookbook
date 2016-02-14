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
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductStyleService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;

/**     
 * <p>Title: CategoryController </p>
 * <p>Description: 成品菜-菜系controller</p>
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
@RequestMapping("/styleController")
public class StyleController extends BaseController
{
    @Autowired
    private IProductStyleService styleService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IProductService productService;

    /**
     * 跳转到新增成品菜-菜系页面
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
        ProductStyleDto ProductStyleDto = new ProductStyleDto();
        ProductStyleDto.setName(name);
        //查询数量
        int count = styleService.findCount(ProductStyleDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductStyleDto> styleList = styleService.findListByPage(ProductStyleDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductStyleDto dto : styleList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(styleList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("styleList", styleList);
        mv.setViewName("cookbook/style/style_list");
        return mv;
    }

    /**
     * 跳转到新增菜系页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductStyleDto ProductStyleDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductStyleDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/style/style_add");
        mv.addObject("productStyleDto", ProductStyleDto);
        return mv;
    }

    /**
     * 跳转到编辑菜系页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductStyleDto ProductStyleDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductStyleDto styleDto = styleService.findById(ProductStyleDto.getId());
        mv.addObject("styleDto", styleDto);
        mv.setViewName("cookbook/style/style_edit");
        return mv;
    }

    /**
     * 保存成品菜菜系
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductStyleDto ProductStyleDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductStyleDto.getName()))
        {
            ProductStyleDto styleDto = styleService.findByName(ProductStyleDto.getName());
            if (styleDto != null && !styleDto.getId().equals(ProductStyleDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("菜系:[" + ProductStyleDto.getName() + "]已经存在，请勿编辑重复菜系");
                j.setObj(ProductStyleDto);
                return j;
            }
            else
            {
                //添加菜品菜系
                styleService.edit(ProductStyleDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑菜系成功");
        j.setObj(ProductStyleDto);
        return j;
    }

    /**
     * 保存成品菜菜系
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductStyleDto ProductStyleDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductStyleDto.getName()))
        {
            ProductStyleDto styleDto = styleService.findByName(ProductStyleDto.getName());
            if (styleDto != null)
            {
                j.setSuccess(false);
                j.setMsg("菜系:[" + ProductStyleDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(ProductStyleDto);
                return j;
            }
            else
            {
                //添加菜品菜系
                styleService.add(ProductStyleDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增菜系成功");
        j.setObj(ProductStyleDto);
        return j;
    }

    /**
     * 
     * delete：删除菜系
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
            productDto.setProductStyleId(id);
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
                j.setMsg("该菜系被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductStyleDto categoryDto = styleService.findById(id);
            if (categoryDto != null)
            {
                categoryDto.setStat(CookbookFields.DisEnable);
                styleService.delete(categoryDto);
                j.setMsg("删除菜系成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除菜系失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新菜系下拉框
    @RequestMapping("/reloadStyle")
    @ResponseBody
    public List<ProductStyleDto> reloadCategory()
    {
        ProductStyleDto ProductStyleDto = new ProductStyleDto();
        List<ProductStyleDto> styleList = styleService.findListByPage(ProductStyleDto, null);
        return styleList;
    }
}
