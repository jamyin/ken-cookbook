package com.ssic.cookbook.manager.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssic.cookbook.admin.controller.base.BaseController;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.Const;
import com.ssic.cookbook.admin.util.FileDownload;
import com.ssic.cookbook.admin.util.FileUpload;
import com.ssic.cookbook.admin.util.Jurisdiction;
import com.ssic.cookbook.admin.util.ObjectExcelRead;
import com.ssic.cookbook.admin.util.ObjectExcelView;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.admin.util.PathUtil;
import com.ssic.cookbook.manager.dto.BrandDto;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.MaterialNutritionDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.dto.ProductMaterialDto;
import com.ssic.cookbook.manager.dto.ReturnResultDto;
import com.ssic.cookbook.manager.service.IBrandService;
import com.ssic.cookbook.manager.service.IMaterialBigCategoryService;
import com.ssic.cookbook.manager.service.IMaterialNutritionService;
import com.ssic.cookbook.manager.service.IMaterialService;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.cookbook.manager.service.IProductMaterialService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.cookbook.manager.util.PageFunction;
import com.ssic.util.UUIDGenerator;

@Controller
@RequestMapping("/materialController")
public class MaterialController extends BaseController
{

    String menuUrl = "materialController/excel.do"; //菜单地址(权限用)
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private IParamConfigService paramConfigService;
    @Autowired
    private IMaterialBigCategoryService materialBigCategoryService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private INutritionService nutritionService;
    @Autowired
    private IProductMaterialService productMaterialService;
    @Autowired
    private IMaterialNutritionService materialNutritionService;

    @RequestMapping("/findMaterial")
    public ModelAndView findMaterial(Page page) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        MaterialDto materialDto = new MaterialDto();
        //List<MaterialDto> listdto = materialService.findListByPage(materialDto);

        String name = pd.getString("name");
        if (!StringUtils.isEmpty(name))
        {
            materialDto.setName(name);
        }
        String isSensitiveMaterial = pd.getString("isSensitiveMaterial");
        if (!StringUtils.isEmpty(isSensitiveMaterial))
        {
            materialDto.setIsSensitiveMaterial(Integer.valueOf(isSensitiveMaterial));
        }
        String type = pd.getString("type");
        if (!StringUtils.isEmpty(type))
        {
            materialDto.setType(Integer.valueOf(type));
        }
        String bigCategoryId = pd.getString("bigCategoryId");
        if (!StringUtils.isEmpty(bigCategoryId))
        {
            materialDto.setBigCategoryId(bigCategoryId);
        }
        String brandId = pd.getString("brandId");
        if (!StringUtils.isEmpty(brandId))
        {
            materialDto.setBrandId(brandId);
        }
        int count = materialService.findCount(materialDto);

        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        materialDto.setLimitStar(limitPageDto.getStar());
        materialDto.setLimitEnd(limitPageDto.getEnd());
        List<MaterialDto> list2 = materialService.findAll(materialDto);
        if (!CollectionUtils.isEmpty(list2))
        {
            for (MaterialDto materdto : list2)
            {
                long mcost2 = materdto.getCost();
                materdto.setStrcost(trans_string(mcost2));
            }
        }
        page.setPd(pd);
        //设置page信息
        pageFunction.setLimitPage(list2.size(), count, limitPageDto.getTotalPage(), page);
        //查询参数   是否敏感食材 1:是 0:否   原料类型:1原料,2:半成品   
        ParamConfigDto p1 = new ParamConfigDto();
        p1.setParamType(CookbookFields.Is_Sensitive_Material);
        List<ParamConfigDto> listSen = paramConfigService.findForList(p1);
        ParamConfigDto p2 = new ParamConfigDto();
        p2.setParamType(CookbookFields.Material_Type);
        List<ParamConfigDto> listMType = paramConfigService.findForList(p2);
        List<MaterialBigCategoryDto> listMB =
            materialBigCategoryService.findForList(new MaterialBigCategoryDto());
        List<BrandDto> listbrand = brandService.findForList(new BrandDto());
        mv.addObject("listSen", listSen); //敏感词
        mv.addObject("listMType", listMType); //类型
        mv.addObject("listMB", listMB); //大类
        mv.addObject("listbrand", listbrand); //品牌
        mv.setViewName("cookbook/material/material_list");
        mv.addObject("listMaterial", list2);
        mv.addObject("pd", pd);
        mv.addObject(Const.SESSION_QX, this.getHC()); //按钮权限
        return mv;
    }

    @RequestMapping("/addMaterial")
    public ModelAndView addMaterial()
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //查询参数   是否敏感食材 1:是 0:否   原料类型:1原料,2:半成品   
        ParamConfigDto p1 = new ParamConfigDto();
        p1.setParamType(CookbookFields.Is_Sensitive_Material);
        List<ParamConfigDto> listSen = paramConfigService.findForList(p1);
        ParamConfigDto p2 = new ParamConfigDto();
        p2.setParamType(CookbookFields.Material_Type);
        List<ParamConfigDto> listMType = paramConfigService.findForList(p2);
        List<MaterialBigCategoryDto> listMB =
            materialBigCategoryService.findForList(new MaterialBigCategoryDto());
        List<BrandDto> listbrand = brandService.findForList(new BrandDto());
        //获取营养
        ParamConfigDto p3 = new ParamConfigDto();
        p3.setParamType(CookbookFields.Material_Nutrition);
        List<ParamConfigDto> listnutr = paramConfigService.findForList(p3);
        mv.addObject("listSen", listSen); //敏感词
        mv.addObject("listMType", listMType); //类型
        mv.addObject("listMB", listMB); //大类
        mv.addObject("listbrand", listbrand); //品牌
        mv.addObject("listnutr", listnutr); //营养
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_addMaterial");
        return mv;
    }

    @RequestMapping("/saveMaterial")
    public ModelAndView saveMaterial(PrintWriter out)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String mname = pd.getString("name");
        String mcost = pd.getString("cost");
        //成本含量乘以100 展示的时候除以100
        Long lmcost = trans_long(mcost);
        String mimageurl = pd.getString("imgUrl");
        String mtype = pd.getString("type");
        String bigCategoryId = pd.getString("bigCategoryId");
        String brandId = pd.getString("brandId");
        String isSensitiveMaterial = pd.getString("isSensitiveMaterial");
        String mremark = pd.getString("remark");
        String snIds = pd.getString("snIds");
        String[] snIds2 = snIds.split(",");

        //获取营养的信息
        ArrayList<NutritionDto> arrayList = new ArrayList<NutritionDto>();
        String nutrSize = pd.getString("nutrSize"); //营养条数
        int nSize = Integer.parseInt(nutrSize);
        for (int i = 0; i < snIds2.length; i++)
        {
        	String sn_index = snIds2[i].substring(snIds2[i].length()-1, snIds2[i].length());
            String nutrPid = pd.getString("nutrPid_" + sn_index);
            String ncontent = pd.getString("ncontent_" + sn_index);
            NutritionDto nutritionDto = new NutritionDto();
            nutritionDto.setParamId(nutrPid);
            nutritionDto.setContent(trans_long(ncontent));
            arrayList.add(nutritionDto);
        }

        //新增原料 插入原料，营养，品牌，大类，营养原料表
        MaterialDto materialDto = new MaterialDto();
        materialDto.setName(mname);
        materialDto.setCost(lmcost);
        materialDto.setId(UUIDGenerator.getUUID());
        materialDto.setImgUrl(mimageurl);
        materialDto.setType(Integer.valueOf(mtype));
        materialDto.setBigCategoryId(bigCategoryId);
        materialDto.setBrandId(brandId);
        materialDto.setIsSensitiveMaterial(Integer.valueOf(isSensitiveMaterial));
        materialDto.setListNu(arrayList);
        materialDto.setRemark(mremark);
        ReturnResultDto returnResultDto = materialService.insertAllMater(materialDto);

        if ("200".equals(returnResultDto.getErrorCode()))
        {
            mv.addObject("msg", "success");
        }
        else
        {
            mv.addObject("msg", "failed");
        }
        mv.setViewName("save_result");
        return mv;
    }

    //删除原料
    @RequestMapping("/delMaterial")
    @ResponseBody
    public void delMaterial(PrintWriter out)
    {
        PageData pd = new PageData();
        pd = this.getPageData();
        String mid = pd.getString("mid");
        MaterialDto materialDto = materialService.findById(mid);
        if (materialDto != null && materialDto.getStat() == 1)
        {
            if (!StringUtils.isEmpty(materialDto.getBigCategoryId()))
            {
                //删除原料前需要先删除跟原料的关系:原料所属原料大类
                materialBigCategoryService.delteMBC(materialDto.getBigCategoryId());
            }
            //该原料下的所有营养集合
            List<MaterialNutritionDto> listNutrition = materialNutritionService.findByMaterialId(mid);
            if (!CollectionUtils.isEmpty(listNutrition))
            {
                //删除原料-营养的关系
                for (MaterialNutritionDto materialNutritionDto : listNutrition)
                {
                    nutritionService.deleteNutr(materialNutritionDto.getNutritionId());
                }
            }
        }

        materialService.delM(mid);
        out.write("success");
        out.close();
    }

    //查询该原料是否已经在成品菜中
    @RequestMapping("/findProductByMater")
    @ResponseBody
    public int findProductByMater(String mid)
    {
        ProductMaterialDto productMaterialDto = new ProductMaterialDto();
        productMaterialDto.setMaterialId(mid);
        List<ProductMaterialDto> list = productMaterialService.findListByPage(productMaterialDto, null);
        return list.size() == 0 ? 1 : 0;
    }

    //编辑原料
    @RequestMapping("/editMPage")
    public ModelAndView editMPage(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String mid = pd.getString("mid");
        //原料
        MaterialDto materialDto = materialService.findById(mid);
        //营养
        List<NutritionDto> listnutr = new ArrayList<NutritionDto>();
        listnutr = materialService.findNuByMid(mid);
        StringBuffer stringBuffer = new StringBuffer();
        if (!CollectionUtils.isEmpty(listnutr))
        {
            for (int i = 0; i < listnutr.size(); i++)
            {
                NutritionDto nutr = listnutr.get(i);
                String nutrid = nutr.getId();
                String nutrpname = nutr.getName();
                String str_nutrcontent = trans_string(nutr.getContent());
                String nutrunit = nutr.getUnit();
                stringBuffer.append("<div id='" + i
                    + "' class=\"select_wrap\" style=\"width: 214px; margin-left:0;\">");
                stringBuffer.append("<input type=\"hidden\" id='nutrPid_" + i + "' name='nutrPid_" + i
                    + "' value='" + nutrid + "'  />");
                stringBuffer.append("<input  type=\"text\" id='nutrPName_" + i
                    + "' class=\"kel\" name=\"nutrname\" readonly=\"readonly\"  value='" + nutrpname
                    + "' style=\"width: 75px;margin-bottom:0;\"  />");
                stringBuffer.append("<input type=\"text\" class=\"kel\" name='ncontent_" + i
                    + "' id='ncontent_" + i + "' value='" + str_nutrcontent
                    + "'  style=\"width: 36px;margin-bottom:0;margin-left: 6px;\"> / ");
                stringBuffer.append("<span class=\"kel_label\"><input type=\"text\" class=\"kel\" id=\"unit\" name=\"unit\" readonly=\"readonly\"  value='"
                    + nutrunit + "' style=\"width: 31px;margin-bottom:0;\"></span>");
                stringBuffer.append("<i class=\"icon icon-minus input_minus\"    onClick=\"iconlick(" + i
                    + ")\"></i>");
                stringBuffer.append("</div>");

            }
        }
        mv.addObject("nutInfo", stringBuffer.toString());

        //查询参数   是否敏感食材 1:是 0:否   原料类型:1原料,2:半成品   
        ParamConfigDto p1 = new ParamConfigDto();
        p1.setParamType(CookbookFields.Is_Sensitive_Material);
        List<ParamConfigDto> listSen = paramConfigService.findForList(p1);
        ParamConfigDto p2 = new ParamConfigDto();
        p2.setParamType(CookbookFields.Material_Type);
        List<ParamConfigDto> listMType = paramConfigService.findForList(p2);
        List<MaterialBigCategoryDto> listMB =
            materialBigCategoryService.findForList(new MaterialBigCategoryDto());
        List<BrandDto> listbrand = brandService.findForList(new BrandDto());
        
        //cost加小数点
        Long cost =  materialDto.getCost();
        materialDto.setStrcost(trans_string(cost));
        
        mv.addObject("listSen", listSen); //敏感词
        mv.addObject("listMType", listMType); //类型
        mv.addObject("listMB", listMB); //大类
        mv.addObject("listbrand", listbrand); //品牌
        mv.addObject("pd", pd);
        mv.addObject("materialDto", materialDto);
        mv.addObject("listnutr", listnutr);
        mv.setViewName("cookbook/material/material_editMaterial");
        return mv;
    }

    //更新原料
    @RequestMapping("/updateMaterial")
    public ModelAndView updateMaterial(PrintWriter out)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String materialId = pd.getString("material_id"); //原料ID
        String bigCategoryId = pd.getString("bigCategoryId"); //大类ID
        String remark = pd.getString("remark"); //备注
        String type = pd.getString("type"); //原料类型
        String cost = pd.getString("cost"); //原料成本
        String brandId = pd.getString("brandId"); //品牌ID
        String imgUrl = pd.getString("imgUrl"); //图片地址
        String isSensitiveMaterial = pd.getString("isSensitiveMaterial"); //敏感词ID
        String name = pd.getString("name"); //原料名称
        
        String snIds = pd.getString("snIds");
        String[] snIds2 = snIds.split(",");
        
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(materialId);
        materialDto.setBigCategoryId(bigCategoryId);
        materialDto.setRemark(remark);
        materialDto.setType(Integer.valueOf(type));
        materialDto.setCost(trans_long(cost));
        materialDto.setBrandId(brandId);
        materialDto.setImgUrl(imgUrl);
        materialDto.setIsSensitiveMaterial(Integer.valueOf(isSensitiveMaterial));
        materialDto.setName(name);

        String nutrSize = pd.getString("nutrSize"); //营养条数
        int nutrSize2 = Integer.valueOf(nutrSize);
        //查询出全部的营养
        List<NutritionDto> listnu = new ArrayList<NutritionDto>();
        if (nutrSize2 > 0)
        {
            for (int i = 0; i < snIds2.length; i++)
            {
            	String sn_index = snIds2[i].substring(snIds2[i].length()-1, snIds2[i].length());
                String pid = pd.getString("nutrPid_" + sn_index);
                String content = pd.getString("ncontent_" + sn_index);
                if (!StringUtils.isEmpty(pid))
                {
                    ParamConfigDto pcDto =
                        paramConfigService.findByTypeAndPid(pid, CookbookFields.Material_Nutrition);
                    NutritionDto nutritionDto = new NutritionDto();
                    nutritionDto.setName(pcDto.getParamName());
                    nutritionDto.setParamId(pid);
                    nutritionDto.setId(UUIDGenerator.getUUID());
                    nutritionDto.setLastUpdateTime(new Date());
                    nutritionDto.setName(pcDto.getParamName());
                    nutritionDto.setParamType(CookbookFields.Material_Nutrition);
                    nutritionDto.setUnit(pcDto.getRemark());
                    nutritionDto.setContent(trans_long(content));
                    listnu.add(nutritionDto);
                }
            }
        }
        materialDto.setListNu(listnu);
        materialService.updateMaterAndNutr(materialDto);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //查看这个原料名称是否存在
    @RequestMapping("/hasMNAme")
    @ResponseBody
    public List<MaterialDto> hasMNAme(String name)
    {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setName(name);
        List<MaterialDto> list = materialService.findListBy(materialDto);
        return list;
    }

    //新增品牌页面
    @RequestMapping("/addBrandPage")
    public ModelAndView addBrandPage(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_addBrandPage");
        return mv;
    }

    //展示品牌
    @RequestMapping("/addBrand")
    public ModelAndView addBrand(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String brandName = pd.getString("name");
        BrandDto brandDto = new BrandDto();
        if (!StringUtils.isEmpty(brandName))
        {
            brandDto.setName(brandName);
        }
        int count = brandService.findForCount(brandDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<BrandDto> listbrand = brandService.findForList(brandDto, limitPageDto);
        page.setPd(pd);
        //设置page信息
        pageFunction.setLimitPage(listbrand.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("listbrand", listbrand);
        mv.setViewName("cookbook/material/material_brand");
        return mv;
    }

    //修改品牌页面
    @RequestMapping("/editBrandPage")
    public ModelAndView editBrandPage(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String brandId = pd.getString("brandId");
        BrandDto brandDto = brandService.findById(brandId);
        mv.addObject("pd", pd);
        mv.addObject("brandId", brandId);
        mv.addObject("brandDto", brandDto);
        mv.setViewName("cookbook/material/material_editBrandPage");
        return mv;
    }

    //修改大类页面
    @RequestMapping("/editBigCaterPage")
    public ModelAndView editBigCaterPage(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigId = pd.getString("bigId");
        MaterialBigCategoryDto materialBigCategoryDto = materialBigCategoryService.findById(bigId);
        mv.addObject("bigId", bigId);
        mv.addObject("materialBigCategoryDto", materialBigCategoryDto);
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_editBigCaterPage");
        return mv;
    }

    //保存大类页面
    @RequestMapping("/updateBigCater")
    public ModelAndView updateBigCater(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigId = pd.getString("bigId");
        String name = pd.getString("name");
        MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
        materialBigCategoryDto.setId(bigId);
        materialBigCategoryDto.setName(name);
        materialBigCategoryDto.setLastUpdateTime(new Date());
        materialBigCategoryService.updateMBC(materialBigCategoryDto);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //修改品牌
    @RequestMapping("/updateBrand")
    public ModelAndView updateBrand(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String brandId = pd.getString("brandId");
        String name = pd.getString("name");
        if (!StringUtils.isEmpty(brandId))
        {
            BrandDto brandDto = new BrandDto();
            brandDto.setId(brandId);
            brandDto.setName(name);
            brandDto.setLastUpdateTime(new Date());
            brandService.updateBS(brandDto);
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //保存品牌
    @RequestMapping("/saveBrand")
    public ModelAndView saveBrand(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String brandName = pd.getString("name");
        BrandDto brandDto = new BrandDto();
        if (!StringUtils.isEmpty(brandName))
        {
            brandDto.setName(brandName);
            brandDto.setId(UUIDGenerator.getUUID());
            brandDto.setCreateTime(new Date());
            brandService.insertBS(brandDto);
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //删除大类
    @RequestMapping("/delBigCate")
    @ResponseBody
    public void delBigCate(PrintWriter out)
    {
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigId = pd.getString("bigId");
        materialBigCategoryService.delteMBC(bigId);
        out.write("success");
        out.close();
    }

    //删除品牌
    @RequestMapping("/deleteBrand")
    @ResponseBody
    public void deleteBrand(PrintWriter out)
    {
        PageData pd = new PageData();
        pd = this.getPageData();
        String brandId = pd.getString("brandId");
        brandService.deleteBS(brandId);
        out.write("success");
        out.close();
    }

    //刷新品牌下拉框
    @RequestMapping("/freshBrand")
    @ResponseBody
    public List<BrandDto> freshBrand()
    {
        List<BrandDto> list = brandService.findForList(new BrandDto());
        return list;
    }

    //展示大类
    @RequestMapping("/addBigCategory")
    public ModelAndView addBigCategory(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigname = pd.getString("name");
        MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
        if (!StringUtils.isEmpty(bigname))
        {
            materialBigCategoryDto.setName(bigname);
        }
        int count = materialBigCategoryService.findForCount(materialBigCategoryDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<MaterialBigCategoryDto> listbig =
            materialBigCategoryService.findForList(materialBigCategoryDto, limitPageDto);
        page.setPd(pd);
        //设置page信息
        pageFunction.setLimitPage(listbig.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("listbig", listbig);
        mv.setViewName("cookbook/material/material_bigCate");
        return mv;
    }

    //刷新大类下拉框
    @RequestMapping("/freshBig")
    @ResponseBody
    public List<MaterialBigCategoryDto> freshBig()
    {
        List<MaterialBigCategoryDto> list =
            materialBigCategoryService.findForList(new MaterialBigCategoryDto());
        return list;
    }

    //新增大类页面
    @RequestMapping("/addBigPage")
    public ModelAndView addBigPage()
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_addBigPage");
        return mv;

    }

    //保存大类
    @RequestMapping("/saveBig")
    public ModelAndView saveBig()
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigName = pd.getString("name");
        MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
        if (!StringUtils.isEmpty(bigName))
        {
            materialBigCategoryDto.setName(bigName);
            materialBigCategoryDto.setId(UUIDGenerator.getUUID());
            materialBigCategoryDto.setCreateTime(new Date());
            materialBigCategoryService.insertMBC(materialBigCategoryDto);
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //查询营养的单位
    @RequestMapping("/findNutrById")
    @ResponseBody
    public ParamConfigDto findNutrById(String paramId)
    {
        ParamConfigDto pdto = paramConfigService.findByTypeAndPid(paramId, CookbookFields.Material_Nutrition);
        return pdto;
    }

    //新增营养界面
    @RequestMapping("/addNutrsPage")
    public ModelAndView addNutrsPage(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_addNutrsPage");
        return mv;
    }

    //修改营养界面
    @RequestMapping("/goEditNustr")
    public ModelAndView goEditNustr(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String nustrid = pd.getString("nustrid");
        ParamConfigDto pcDto = new ParamConfigDto();
        if (!StringUtils.isEmpty(nustrid))
        {
            pcDto = paramConfigService.findByTypeAndPid(nustrid, CookbookFields.Material_Nutrition);
        }
        mv.addObject("pcDto", pcDto);
        mv.addObject("pd", pd);
        mv.setViewName("cookbook/material/material_editNutrsPage");
        return mv;
    }

    //展示该原料下的营养
    @RequestMapping("/findNuByMid")
    @ResponseBody
    public List<NutritionDto> findNuByMid(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        List<NutritionDto> list = new ArrayList<NutritionDto>();
        String mid = pd.getString("mid");
        if (!StringUtils.isEmpty(mid))
        {
            list = materialService.findNuByMid(mid);
        }

        if (!CollectionUtils.isEmpty(list))
        {
            for (NutritionDto nutr1 : list)
            {
                long nucon = nutr1.getContent();
                String nustr = trans_string(nucon);
                nutr1.setContentstr(nustr);
            }
        }

        return list;
    }

    /**
     * 打开上传EXCEL页面
     */
    @RequestMapping("/goUploadExcel")
    public ModelAndView goUploadExcel() throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("cookbook/material/material_uploadexcel");
        return mv;
    }

    //读取excel
    @RequestMapping("/readExcel")
    public ModelAndView readExcel(@RequestParam(value = "excel", required = false) MultipartFile file)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        if (null != file && !file.isEmpty())
        {
            String filePath = PathUtil.getClassResources() + Const.FILEPATHFILE; //文件上传路径
            String fileName = FileUpload.fileUp(file, filePath, "materialexcel"); //执行上传
            List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0); //执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            //开始读取原料信息
            String uploadMessage = "";
            if (!CollectionUtils.isEmpty(listPd))
            {
                List<MaterialDto> listmdto = new ArrayList<MaterialDto>();
                for (PageData paged : listPd)
                {
                    MaterialDto materialDto = new MaterialDto();
                    String name = paged.getString("var0");
                    String bigCategoryName = paged.getString("var1");
                    String brandName = paged.getString("var2");
                    String typeName = paged.getString("var3");
                    String isSensitiveName = paged.getString("var4");
                    String coststr = paged.getString("var5");
                    String remark = paged.getString("var6");
                    String nutrlie = paged.getString("var7");

                    //开始校验字段
                    //非空校验
                    if (StringUtils.isEmpty(name))
                    {
                        uploadMessage = "原料名称不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(bigCategoryName))
                    {
                        uploadMessage = "原料大类不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(brandName))
                    {
                        uploadMessage = "原料品牌不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(typeName))
                    {
                        uploadMessage = "原料类型不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(isSensitiveName))
                    {
                        uploadMessage = "原料是否敏感不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(coststr))
                    {
                        uploadMessage = "原料成本不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    if (StringUtils.isEmpty(nutrlie))
                    {
                        uploadMessage = "原料营养不能为空";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    //名称重复校验
                    MaterialDto materialDto2 = new MaterialDto();
                    materialDto2.setName(name);
                    List<MaterialDto> listm2 = materialService.findListBy(materialDto2);
                    if (!CollectionUtils.isEmpty(listm2))
                    {
                        uploadMessage = "原料名称: " + name + " 重复";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    //原料类型必须是：原料 半成品
                    if (!"原料".equals(typeName) && !"半成品".equals(typeName))
                    {
                        uploadMessage = "原料类型必须是：原料或者半成品";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    //是否敏感食材必须是： 是 否
                    if (!"是".equals(isSensitiveName) && !"否".equals(isSensitiveName))
                    {
                        uploadMessage = "是否敏感食材必须是：是或者否";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    //原料成本必须是数字
                    if (!coststr.matches("[0-9]+"))
                    {
                        uploadMessage = "原料成本: " + coststr + " 必须是数字";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    //原料营养校验  钙,22,毫克%锌,33,毫克
                    String[] nustr2 = nutrlie.split("%");
                    if (nustr2 == null || nustr2.length == 0)
                    {
                        uploadMessage = "原料营养: " + nutrlie + " 没有用%分割";
                        mv.addObject("msg", "error");
                        mv.addObject("uploadmsg", uploadMessage);
                        mv.setViewName("cookbook/material/upload_result");
                        return mv;
                    }

                    for (int i = 0; i < nustr2.length; i++)
                    {
                        String nustrinfo2 = nustr2[i];
                        String[] nustrinfo3 = nustrinfo2.split(",");
                        if (nustrinfo3 == null || nustrinfo3.length == 0)
                        {
                            uploadMessage = "原料营养: " + nutrlie + " 没有用,分割";
                            mv.addObject("msg", "error");
                            mv.addObject("uploadmsg", uploadMessage);
                            mv.setViewName("cookbook/material/upload_result");
                            return mv;
                        }
                        else if (nustrinfo3.length != 3)
                        {
                            uploadMessage = "原料营养: " + nutrlie + " 营养个数不够";
                            mv.addObject("msg", "error");
                            mv.addObject("uploadmsg", uploadMessage);
                            mv.setViewName("cookbook/material/upload_result");
                            return mv;
                        }
                        else if (!nustrinfo3[1].matches("[0-9]+"))
                        {
                            uploadMessage = "原料营养: " + nutrlie + " 营养含量必须为数字";
                            mv.addObject("msg", "error");
                            mv.addObject("uploadmsg", uploadMessage);
                            mv.setViewName("cookbook/material/upload_result");
                            return mv;
                        }
                        else
                        {
                            //验证参数表中的营养名称和单位是否一致
                            ParamConfigDto paramConfigDto = new ParamConfigDto();
                            paramConfigDto.setParamName(nustrinfo3[0]);
                            paramConfigDto.setParamType(CookbookFields.Material_Nutrition);
                            List<ParamConfigDto> listpdto = paramConfigService.findForList(paramConfigDto);
                            if (!CollectionUtils.isEmpty(listpdto))
                            {
                                String premark = listpdto.get(0).getRemark();
                                if (!premark.equals(nustrinfo3[2]))
                                {
                                    uploadMessage = "原料: " + nustrinfo3[0] + " 的单位必须为: " + premark;
                                    mv.addObject("msg", "error");
                                    mv.addObject("uploadmsg", uploadMessage);
                                    mv.setViewName("cookbook/material/upload_result");
                                    return mv;
                                }
                            }
                        }
                    }
                    MaterialDto materialDto3 = new MaterialDto();
                    //营养校验完成
                    List<NutritionDto> listnutr2 = new ArrayList<NutritionDto>();
                    for (int j = 0; j < nustr2.length; j++)
                    {
                        String nustrinfo3 = nustr2[j];
                        String[] nustrs3 = nustrinfo3.split(",");
                        // for(int k=0;k<nustrs3.length;k++){
                        String nustr3name = nustrs3[0];
                        String nustr3content = nustrs3[1];
                        String nustr3unit = nustrs3[2];
                        NutritionDto nutritionDto = new NutritionDto();
                        nutritionDto.setName(nustr3name);
                        nutritionDto.setContent(trans_long(nustr3content));
                        nutritionDto.setUnit(nustr3unit);
                        listnutr2.add(nutritionDto);

                        // }
                    }
                    //全部校验完成
                    materialDto3.setListNu(listnutr2);
                    materialDto3.setName(name);
                    materialDto3.setBigCategoryName(bigCategoryName);
                    materialDto3.setBrandName(brandName);
                    materialDto3.setTypeName(typeName);
                    materialDto3.setIsSensitiveName(isSensitiveName);
                    materialDto3.setCost(trans_long(coststr));
                    materialDto3.setRemark(remark);
                    listmdto.add(materialDto3);

                }
                //开始插入数据
                ReturnResultDto returnResultDto = materialService.insertExecl(listmdto);
                uploadMessage = returnResultDto.getMsg();
            }

            mv.addObject("msg", "success");
            mv.addObject("uploadmsg", uploadMessage);
        }
        mv.setViewName("cookbook/material/upload_result");
        return mv;
    }

    /**
     * 下载模版
     */
    @RequestMapping(value = "/downExcel")
    public void downExcel(HttpServletResponse response) throws Exception
    {

        FileDownload.fileDownload(response,
            PathUtil.getClasspath() + Const.FILEPATHFILE + "Material.xls",
            "Material.xls");

    }

    //营养展示界面
    @RequestMapping("/findNutrsList")
    public ModelAndView findNutrsList(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String bigname = pd.getString("name");
        ParamConfigDto paramConfigDto = new ParamConfigDto();
        if (!StringUtils.isEmpty(bigname))
        {
            paramConfigDto.setParamName(bigname);
        }
        String ids = pd.getString("ids");
        if (!StringUtils.isEmpty(ids))
        {
            String[] nutrids = ids.split(",");
            List<String> listnut = Arrays.asList(nutrids);
            paramConfigDto.setIds(listnut);
        }
        paramConfigDto.setParamType(CookbookFields.Material_Nutrition);
        int count = paramConfigService.findCountForList(paramConfigDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        page.setPd(pd);
        List<ParamConfigDto> listnutr = paramConfigService.findForList(paramConfigDto, limitPageDto);
        //设置page信息
        pageFunction.setLimitPage(listnutr.size(), count, limitPageDto.getTotalPage(), page);
        mv.addObject("listnutr", listnutr);
        mv.setViewName("cookbook/material/material_nutrsList");
        return mv;
    }

    //查询营养更具ids
    @RequestMapping("/findNutrByList")
    @ResponseBody
    public List<ParamConfigDto> findNutrByList(String ids)
    {
        List<ParamConfigDto> list = new ArrayList<ParamConfigDto>();
        if (!StringUtils.isEmpty(ids))
        {
            ParamConfigDto paramConfigDto = new ParamConfigDto();
            if (!StringUtils.isEmpty(ids))
            {
                String[] nutrids = ids.split(",");
                List<String> listnut = Arrays.asList(nutrids);
                paramConfigDto.setIds(listnut);
            }
            list = paramConfigService.findByList(paramConfigDto);
        }
        return list;
    }

    //保存营养
    @RequestMapping("/saveNutr")
    public ModelAndView saveNutr(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String nutrName = pd.getString("nutrName");
        String unit = pd.getString("unit");
        ParamConfigDto paramConfigDto = new ParamConfigDto();
        paramConfigDto.setParamName(nutrName);
        paramConfigDto.setRemark(unit);
        String uuid = UUIDGenerator.getUUID();
        paramConfigDto.setId(uuid);
        paramConfigDto.setName("原料营养");
        paramConfigDto.setParamId(uuid);
        paramConfigDto.setParamType(CookbookFields.Material_Nutrition);
        paramConfigDto.setCreateTime(new Date());
        paramConfigService.insertPam(paramConfigDto);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //编辑营养
    @RequestMapping("/editNutr")
    public ModelAndView editNutr(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String nutrName = pd.getString("nutrName");
        String unit = pd.getString("unit");
        String nustrid = pd.getString("nustrid");
        //修改营养参数表修改营养表
        NutritionDto nutritionDto = new NutritionDto();
        nutritionDto.setName(nutrName);
        nutritionDto.setUnit(unit);
        nutritionDto.setParamId(nustrid);
        nutritionService.updateNSAndPro(nutritionDto);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    //删除营养
    @RequestMapping("/deleteNutr")
    public void deleteNutr(PrintWriter out)
    {
        PageData pd = new PageData();
        pd = this.getPageData();
        String nustrid = pd.getString("nustrid");
        //删除营养参数
        paramConfigService.deleteNutr(nustrid);
        out.write("success");
        out.close();
    }

    /* ===============================权限================================== */
    public Map<String, String> getHC()
    {
        Subject currentUser = SecurityUtils.getSubject(); //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }

    /* ===============================权限================================== */

    /**
     * @author pengcheng.yang
     * @desc 导出原料信息到EXCEL
     * @return  ModelAndView
     */
    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel(Page page)
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try
        {
            if (Jurisdiction.buttonJurisdiction(menuUrl, "cha"))
            {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                List<String> titles = new ArrayList<String>();

                titles.add("原料名称"); //1
                titles.add("原料类型"); //2
                titles.add("原料成本"); //3
                titles.add("所属大类"); //4
                titles.add("所属品牌"); //5
                titles.add("是否敏感食材"); //6
                titles.add("备注"); //7
                dataMap.put("titles", titles);

                List<PageData> expList = materialService.expExcelFindAll(pd);
                List<PageData> varList = new ArrayList<PageData>();
                for (int i = 0; i < expList.size(); i++)
                {
                    PageData vpd = new PageData();
                    vpd.put("var1", expList.get(i).getString("mname")); //1
                    if ("1".equals(expList.get(i).getString("type")))
                    {
                        vpd.put("var2", "原料"); // 原料
                    }
                    if ("2".equals(expList.get(i).getString("type")))
                    {
                        vpd.put("var2", "半成品"); // 半成品
                    }
                    vpd.put("var3",  trans_string(Long.parseLong(expList.get(i).getString("cost")))  ); //3
                    vpd.put("var4", expList.get(i).getString("cname")); //4
                    vpd.put("var5", expList.get(i).getString("bname")); //5
                    if ("1".equals(expList.get(i).getString("is_sensitive_material")))
                    { //6
                        vpd.put("var6", "是"); // 原料
                    }
                    if ("0".equals(expList.get(i).getString("is_sensitive_material")))
                    {
                        vpd.put("var6", "否"); // 半成品
                    }
                    vpd.put("var7", expList.get(i).getString("remark")); //7
                    varList.add(vpd);
                }
                dataMap.put("varList", varList);
                ObjectExcelView erv = new ObjectExcelView(); //执行excel操作
                mv = new ModelAndView(erv, dataMap);
            }
        }
        catch (Exception e)
        {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    //string 转化为 long 乘以100
    public Long trans_long(String num)
    {
        Double dnum = Double.valueOf(num);
        Long lnum = (long) (dnum * 100);
        return lnum;
    }

    //long转化为String 除以100
    public String trans_string(long num)
    {
        Double dla2 = Double.valueOf(num) / 100;
        String sla = dla2.toString();
        return sla;
    }

}
