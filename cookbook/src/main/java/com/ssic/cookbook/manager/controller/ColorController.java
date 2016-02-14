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
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.service.IProductColorService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;

/**     
 * <p>Title: colorController </p>
 * <p>Description: 成品菜-颜色controller</p>
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
@RequestMapping("/colorController")
public class ColorController extends BaseController
{
    @Autowired
    private IProductColorService colorService;
    @Autowired
    private IProductService productService;
    @Autowired
    private PageFunction pageFunction;

    /**
     * 跳转到新增成品菜-颜色页面
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
        ProductColorDto ProductColorDto = new ProductColorDto();
        ProductColorDto.setName(name);
        //查询数量
        int count = colorService.findCount(ProductColorDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductColorDto> colorList = colorService.findListByPage(ProductColorDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductColorDto dto : colorList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(colorList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("colorList", colorList);
        mv.setViewName("cookbook/color/color_list");
        return mv;
    }

    /**
     * 跳转到新增颜色页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductColorDto ProductColorDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductColorDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/color/color_add");
        mv.addObject("productColorDto", ProductColorDto);
        return mv;
    }

    /**
     * 跳转到编辑颜色页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductColorDto ProductColorDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductColorDto colorDto = colorService.findById(ProductColorDto.getId());
        mv.addObject("colorDto", colorDto);
        mv.setViewName("cookbook/color/color_edit");
        return mv;
    }

    /**
     * 保存成品菜颜色
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductColorDto ProductColorDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductColorDto.getName()))
        {
            ProductColorDto colorDto = colorService.findByName(ProductColorDto.getName());
            if (colorDto != null && !colorDto.getId().equals(ProductColorDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("颜色:[" + ProductColorDto.getName() + "]已经存在，请勿编辑重复颜色");
                j.setObj(ProductColorDto);
                return j;
            }
            else
            {
                //添加菜品颜色
                colorService.edit(ProductColorDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑颜色成功");
        j.setObj(ProductColorDto);
        return j;
    }

    /**
     * 保存成品菜颜色
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductColorDto ProductColorDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductColorDto.getName()))
        {
            ProductColorDto colorDto = colorService.findByName(ProductColorDto.getName());
            if (colorDto != null)
            {
                j.setSuccess(false);
                j.setMsg("颜色:[" + ProductColorDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(ProductColorDto);
                return j;
            }
            else
            {
                //添加菜品颜色
                colorService.add(ProductColorDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增颜色成功");
        j.setObj(ProductColorDto);
        return j;
    }

    /**
     * 
     * delete：删除颜色
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
            productDto.setProductColorId(id);
            List<ProductDto> listProduct =  productService.findAll(productDto);
           // List<ProductDto> listProduct = productService.findListByPage(productDto, null);
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
                j.setMsg("该颜色被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductColorDto colorDto = colorService.findById(id);
            if (colorDto != null)
            {
                colorDto.setStat(CookbookFields.DisEnable);
                colorService.delete(colorDto);
                j.setMsg("删除颜色成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除颜色失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新颜色下拉框
    @RequestMapping("/reloadColor")
    @ResponseBody
    public List<ProductColorDto> reloadColor()
    {
        ProductColorDto ProductColorDto = new ProductColorDto();
        List<ProductColorDto> colorList = colorService.findListByPage(ProductColorDto, null);
        return colorList;
    }
}
