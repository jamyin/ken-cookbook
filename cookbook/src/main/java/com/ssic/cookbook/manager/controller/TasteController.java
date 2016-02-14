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
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductTasteService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.PageFunction;

/**     
 * <p>Title: TasteController </p>
 * <p>Description: 成品菜-口味controller</p>
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
@RequestMapping("/tasteController")
public class TasteController extends BaseController
{
    @Autowired
    private IProductTasteService tasteService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IProductService productService;

    /**
     * 跳转到新增成品菜-口味页面
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
        ProductTasteDto ProductTasteDto = new ProductTasteDto();
        ProductTasteDto.setName(name);
        //查询数量
        int count = tasteService.findCount(ProductTasteDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductTasteDto> tasteList = tasteService.findListByPage(ProductTasteDto, limitPageDto);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        for (ProductTasteDto dto : tasteList)
        {
            dto.setCreateTimeString(formatter.format(dto.getCreateTime()));
        }
        //设置page信息
        pageFunction.setLimitPage(tasteList.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("tasteList", tasteList);
        mv.setViewName("cookbook/taste/taste_list");
        return mv;
    }

    /**
     * 跳转到新增口味页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductTasteDto ProductTasteDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductTasteDto.setId(UUID.randomUUID().toString());
        mv.setViewName("cookbook/taste/taste_add");
        mv.addObject("productTasteDto", ProductTasteDto);
        return mv;
    }

    /**
     * 跳转到编辑口味页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductTasteDto ProductTasteDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        ProductTasteDto tasteDto = tasteService.findById(ProductTasteDto.getId());
        mv.addObject("tasteDto", tasteDto);
        mv.setViewName("cookbook/taste/taste_edit");
        return mv;
    }

    /**
     * 保存成品菜口味
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(ProductTasteDto ProductTasteDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductTasteDto.getName()))
        {
            ProductTasteDto tasteDto = tasteService.findByName(ProductTasteDto.getName());
            if (tasteDto != null && !tasteDto.getId().equals(ProductTasteDto.getId()))
            {
                j.setSuccess(false);
                j.setMsg("口味:[" + ProductTasteDto.getName() + "]已经存在，请勿编辑重复口味");
                j.setObj(ProductTasteDto);
                return j;
            }
            else
            {
                //编辑菜品口味
                tasteService.edit(ProductTasteDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("编辑口味成功");
        j.setObj(ProductTasteDto);
        return j;
    }

    /**
     * 保存成品菜口味
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(ProductTasteDto ProductTasteDto)
    {
        Json j = new Json();
        if (!StringUtils.isEmpty(ProductTasteDto.getName()))
        {
            ProductTasteDto tasteDto = tasteService.findByName(ProductTasteDto.getName());
            if (tasteDto != null)
            {
                j.setSuccess(false);
                j.setMsg("口味:[" + ProductTasteDto.getName() + "]已经存在，请勿重复添加");
                j.setObj(ProductTasteDto);
                return j;
            }
            else
            {
                //添加菜品口味
                tasteService.add(ProductTasteDto);
            }
        }
        j.setSuccess(true);
        j.setMsg("新增口味成功");
        j.setObj(ProductTasteDto);
        return j;
    }

    /**
     * 
     * delete：删除口味
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
            productDto.setProductTasteId(id);
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
                j.setMsg("该口味被成品菜:[" + name + "]使用中,不能删除.");
                j.setSuccess(false);
                return j;
            }
            ProductTasteDto categoryDto = tasteService.findById(id);
            if (categoryDto != null)
            {
                categoryDto.setStat(CookbookFields.DisEnable);
                tasteService.delete(categoryDto);
                j.setMsg("删除口味成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除口味失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    //刷新口味下拉框
    @RequestMapping("/reloadTaste")
    @ResponseBody
    public List<ProductTasteDto> reloadTaste()
    {
        ProductTasteDto ProductTasteDto = new ProductTasteDto();
        List<ProductTasteDto> tasteList = tasteService.findListByPage(ProductTasteDto, null);
        return tasteList;
    }
}
