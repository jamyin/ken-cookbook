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
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductShapeService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;

/**     
 * <p>Title: ShapeController </p>
 * <p>Description: 成品菜-形状controller</p>
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
@RequestMapping("/shapeController")
public class ShapeController extends BaseController
{
    @Autowired
    private IProductShapeService shapeService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IProductService productService;

    /**
     * 跳转到新增成品菜-形状页面
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
        ProductShapeDto ProductShapeDto = new ProductShapeDto();
        ProductShapeDto.setName(name);
        //查询数量
        int count = shapeService.findCount(ProductShapeDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductShapeDto> shapeList = shapeService.findListByPage(ProductShapeDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductShapeDto dto : shapeList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(shapeList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("shapeList", shapeList);
        mv.setViewName("cookbook/shape/shape_list");
        return mv;
    }

    /**
     * 跳转到新增形状页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductShapeDto ProductShapeDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductShapeDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/shape/shape_add");
        mv.addObject("productShapeDto", ProductShapeDto);
        return mv;
    }

    /**
     * 跳转到编辑形状页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductShapeDto ProductShapeDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductShapeDto shapeDto = shapeService.findById(ProductShapeDto.getId());
        mv.addObject("shapeDto", shapeDto);
        mv.setViewName("cookbook/shape/shape_edit");
        return mv;
    }

    /**
     * 保存成品菜形状
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductShapeDto ProductShapeDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductShapeDto.getName()))
        {
            ProductShapeDto shapeDto = shapeService.findByName(ProductShapeDto.getName());
            if (shapeDto != null && !shapeDto.getId().equals(ProductShapeDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("形状:[" + ProductShapeDto.getName() + "]已经存在，请勿编辑重复形状");
                j.setObj(ProductShapeDto);
                return j;
            }
            else
            {
                //添加菜品形状
                shapeService.edit(ProductShapeDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑形状成功");
        j.setObj(ProductShapeDto);
        return j;
    }

    /**
     * 保存成品菜形状
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductShapeDto ProductShapeDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductShapeDto.getName()))
        {
            ProductShapeDto shapeDto = shapeService.findByName(ProductShapeDto.getName());
            if (shapeDto != null)
            {
                j.setSuccess(false);
                j.setMsg("形状:[" + ProductShapeDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(ProductShapeDto);
                return j;
            }
            else
            {
                //添加菜品形状
                shapeService.add(ProductShapeDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增形状成功");
        j.setObj(ProductShapeDto);
        return j;
    }

    /**
     * 
     * delete：删除形状
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
            productDto.setProductShapeId(id);
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
                j.setMsg("该形状被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductShapeDto ShapeDto = shapeService.findById(id);
            if (ShapeDto != null)
            {
                ShapeDto.setStat(CookbookFields.DisEnable);
                shapeService.delete(ShapeDto);
                j.setMsg("删除形状成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除形状失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新形状下拉框
    @RequestMapping("/reloadShape")
    @ResponseBody
    public List<ProductShapeDto> reloadShape()
    {
        ProductShapeDto ProductShapeDto = new ProductShapeDto();
        List<ProductShapeDto> shapeList = shapeService.findListByPage(ProductShapeDto, null);
        return shapeList;
    }
}
