/**
 * 
 */
package com.ssic.cookbook.manager.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ssic.cookbook.manager.dao.ProductDao;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.MaterialNutritionDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductMaterialDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.service.IMaterialNutritionService;
import com.ssic.cookbook.manager.service.IMaterialService;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.cookbook.manager.service.IProductCategoryService;
import com.ssic.cookbook.manager.service.IProductColorService;
import com.ssic.cookbook.manager.service.IProductCuisineService;
import com.ssic.cookbook.manager.service.IProductMaterialService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.IProductShapeService;
import com.ssic.cookbook.manager.service.IProductStyleService;
import com.ssic.cookbook.manager.service.IProductTasteService;
import com.ssic.cookbook.manager.util.Json;
import com.ssic.cookbook.manager.util.ListUtils;
import com.ssic.cookbook.manager.util.PageFunction;
import com.ssic.util.constants.DataStatus;

/**		
 * <p>Title: ProductController </p>
 * <p>Description: 成品菜controller</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:04:51	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:04:51</p>
 * <p>修改备注：</p>
 */
@Controller
@RequestMapping("/productController")
public class ProductController extends BaseController
{

    String menuUrl = "materialController/excel.do"; //菜单地址(权限用)
    @Autowired
    private IProductService productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private IProductCategoryService categoryService;
    @Autowired
    private IProductShapeService shapeService;
    @Autowired
    private IProductStyleService styleService;
    @Autowired
    private IProductColorService colorService;
    @Autowired
    private IProductCuisineService cuisineService;
    @Autowired
    private IProductTasteService tasteService;
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private PageFunction pageFunction;
    @Autowired
    private IMaterialNutritionService materialNutritionService;
    @Autowired
    private IProductMaterialService productMaterialService;
    @Autowired
    private INutritionService nutritionService;

    @RequestMapping("/findProduct")
    public ModelAndView findProduct(Page page) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        //查询条件放入查询dto对象中
        String name = pd.getString("productName");
        String demandNumber = pd.getString("demandNumber");
        String singleWeight = pd.getString("singleWeight");
        String categoryId = pd.getString("categoryId");
        String styleId = pd.getString("styleId");
        String tasteId = pd.getString("tasteId");
        String shapeId = pd.getString("shapeId");
        String colorId = pd.getString("colorId");
        String cuisineId = pd.getString("cuisineId");
        ProductDto productDto = new ProductDto();
        productDto.setProductColorId(colorId);
        productDto.setProductCuisineId(cuisineId);
        productDto.setProductShapeId(shapeId);
        productDto.setProductStyleId(styleId);
        productDto.setProductCategoryId(categoryId);
        productDto.setProductTasteId(tasteId);
        if (!StringUtils.isEmpty(demandNumber))
        {
            boolean isNumber = IsIntNumber(demandNumber);
            //是正整数且长度小于7位
            if (isNumber && demandNumber.length() <= 7)
            {
                productDto.setDemandNumber(Integer.valueOf(demandNumber));
            }
        }
        if (!StringUtils.isEmpty(singleWeight))
        {
            boolean isNumber = IsIntNumber(singleWeight);
            //是正整数且长度小于7位
            if (isNumber && singleWeight.length() <= 7)
            {
                productDto.setSingleWeight(Integer.valueOf(singleWeight));
            }
        }
        productDto.setName(name);

        //查询成品菜数量
        int count = productService.findCount(productDto);
        //获取分页的开始结束
        LimitPageDto limitPageDto = pageFunction.getLimitPage(page, count);
        List<ProductDto> listdto = productService.findListByPage(productDto, limitPageDto);
        //设置page信息
        pageFunction.setLimitPage(listdto.size(), count, limitPageDto.getTotalPage(), page);
        //类别集合
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategoryDto> listCategory = categoryService.findListByPage(productCategoryDto, null);
        //菜系集合
        ProductStyleDto productStyleDto = new ProductStyleDto();
        List<ProductStyleDto> listStyle = styleService.findListByPage(productStyleDto, null);
        //口味集合
        ProductTasteDto productTasteDto = new ProductTasteDto();
        List<ProductTasteDto> listTaste = tasteService.findListByPage(productTasteDto, null);
        //颜色集合
        ProductColorDto productColorDto = new ProductColorDto();
        List<ProductColorDto> listColor = colorService.findListByPage(productColorDto, null);
        //形状集合
        ProductShapeDto productShapeDto = new ProductShapeDto();
        List<ProductShapeDto> listShape = shapeService.findListByPage(productShapeDto, null);
        //烹饪集合
        ProductCuisineDto productCuisineDto = new ProductCuisineDto();
        List<ProductCuisineDto> listCuisine = cuisineService.findListByPage(productCuisineDto, null);
        //组装mv对象并返回到前台页面
        mv.setViewName("cookbook/product/product_list");
        mv.addObject("listProduct", listdto);
        mv.addObject("listCategory", listCategory);
        mv.addObject("listStyle", listStyle);
        mv.addObject("listTaste", listTaste);
        mv.addObject("listColor", listColor);
        mv.addObject("listShape", listShape);
        mv.addObject("listCuisine", listCuisine);
        mv.addObject("pd", pd);
        mv.addObject(Const.SESSION_QX, this.getHC()); //按钮权限
        return mv;
    }

    /**
     * 新增成品菜页面
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Json add(ProductDto productDto) throws Exception
    {
        Json j = new Json();

        if (!StringUtils.isEmpty(productDto.getId()))
        {
            //查找6种不同类型的成分是否已经存在;
            List<ProductDto> list = productService.findDistinctComponents(productDto, null);
            if (!CollectionUtils.isEmpty(list))
            {
                j.setSuccess(false);
                j.setMsg("该成品菜配料组合已经存在，请勿重复添加.");
                j.setObj(productDto);
                return j;
            }
            ProductDto p = productService.findByName(productDto.getName());
            if (!StringUtils.isEmpty(p.getId()))
            {
                if (p.getStat() == 1 && !StringUtils.isEmpty(p.getName()))
                {
                    j.setSuccess(false);
                    j.setMsg("该成品菜名称已经存在,不能重名.");
                    j.setObj(productDto);
                    return j;
                }
            }
            productService.add(productDto);//判断新增权限
        }
        j.setSuccess(true);
        j.setMsg("新增成品菜成功");
        j.setObj(productDto);
        return j;
    }

    /**
     * 跳转到新增成品菜选择页面
     */
    @RequestMapping(value = "/addChoosePage")
    public ModelAndView addChoosePage(ProductDto productDto) throws Exception
    {

        ModelAndView mv = this.getModelAndView();
        mv.setViewName("cookbook/product/product_choose_add");
        mv.addObject("productDto", productDto);
        return mv;
    }

    /**
     * 跳转到新增成品菜选择页面
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView addPage(ProductDto productDto) throws Exception
    {

        ModelAndView mv = this.getModelAndView();
        productDto.setId(UUID.randomUUID().toString());
        //类别集合
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategoryDto> listCategory = categoryService.findListByPage(productCategoryDto, null);
        //菜系集合
        ProductStyleDto productStyleDto = new ProductStyleDto();
        List<ProductStyleDto> listStyle = styleService.findListByPage(productStyleDto, null);
        //口味集合
        ProductTasteDto productTasteDto = new ProductTasteDto();
        List<ProductTasteDto> listTaste = tasteService.findListByPage(productTasteDto, null);
        //颜色集合
        ProductColorDto productColorDto = new ProductColorDto();
        List<ProductColorDto> listColor = colorService.findListByPage(productColorDto, null);
        //形状集合
        ProductShapeDto productShapeDto = new ProductShapeDto();
        List<ProductShapeDto> listShape = shapeService.findListByPage(productShapeDto, null);
        //烹饪集合
        ProductCuisineDto productCuisineDto = new ProductCuisineDto();
        List<ProductCuisineDto> listCuisine = cuisineService.findListByPage(productCuisineDto, null);

        if (!CollectionUtils.isEmpty(productDto.getMaterialIdList()))
        {
            List<String> list = new ArrayList<String>();
            for (String materialId : productDto.getMaterialIdList())
            {
                MaterialDto dto = materialService.findById(materialId);
                list.add(dto.getName());
            }
            productDto.setMaterialNameList(list);
        }
        mv.setViewName("cookbook/product/product_add");
        mv.addObject("listCategory", listCategory);
        mv.addObject("listStyle", listStyle);
        mv.addObject("listTaste", listTaste);
        mv.addObject("listColor", listColor);
        mv.addObject("listShape", listShape);
        mv.addObject("listCuisine", listCuisine);
        mv.addObject("productDto", productDto);
        return mv;
    }

    /**
     * 跳转到编辑成品菜页面
     */
    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(ProductDto productDto) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        //类别集合
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        List<ProductCategoryDto> listCategory = categoryService.findListByPage(productCategoryDto, null);
        //菜系集合
        ProductStyleDto productStyleDto = new ProductStyleDto();
        List<ProductStyleDto> listStyle = styleService.findListByPage(productStyleDto, null);
        //口味集合
        ProductTasteDto productTasteDto = new ProductTasteDto();
        List<ProductTasteDto> listTaste = tasteService.findListByPage(productTasteDto, null);
        //颜色集合
        ProductColorDto productColorDto = new ProductColorDto();
        List<ProductColorDto> listColor = colorService.findListByPage(productColorDto, null);
        //形状集合
        ProductShapeDto productShapeDto = new ProductShapeDto();
        List<ProductShapeDto> listShape = shapeService.findListByPage(productShapeDto, null);
        //烹饪集合
        ProductCuisineDto productCuisineDto = new ProductCuisineDto();
        List<ProductCuisineDto> listCuisine = cuisineService.findListByPage(productCuisineDto, null);
        ProductMaterialDto productMaterialDto = new ProductMaterialDto();
        //查找当前成品菜下的所有原料
        productMaterialDto.setProductId(productDto.getId());
        List<ProductMaterialDto> materialList =
            productMaterialService.findListByPage(productMaterialDto, null);
        ProductDto product_Dto = productService.findById(productDto.getId());
        List<String> materialIdList = new ArrayList<String>();
        List<String> materialNameList = new ArrayList<String>();
        List<String> nutritionNameList = new ArrayList<String>();
        String idList = "";
        String nameList = "";
        List<String> nutritionLists = new ArrayList<String>();
        int i = 0;
        for (ProductMaterialDto materDto : materialList)
        {
            if (i > 0)
            {
                idList += ",";
                nameList += ",";
            }
            MaterialDto materialDto = materialService.findById(materDto.getMaterialId());
            if (materialDto != null && materialDto.getStat() == 1)
            {
                idList += materialDto.getId();
                nameList += materialDto.getName();
                materialIdList.add(materialDto.getId());
                materialNameList.add(materialDto.getName());
                List<MaterialNutritionDto> nutritionList =
                    materialNutritionService.findByMaterialId(materDto.getMaterialId());
                for (MaterialNutritionDto materNutriDto : nutritionList)
                {
                    List<String> list = new ArrayList<String>();
                    if (!StringUtils.isEmpty(materNutriDto.getNutritionId()))
                    {
                        list.add(materNutriDto.getNutritionId());
                        NutritionDto nuDto = nutritionService.findById(materNutriDto.getNutritionId());
                        nutritionNameList.add(nuDto.getName());
                        nutritionLists.add(nuDto.getName());

                    }
                }
            }
            i++;

        }
        product_Dto.setMaterialIdList(materialIdList);
        product_Dto.setMaterialNameList(materialNameList);
        List<String> nutrition_NameList = ListUtils.getNewList(nutritionLists);

        if (!StringUtils.isEmpty(productDto.getPageInfo()) && productDto.getPageInfo().equals("chooseEdit"))
        {//0表示:添加成品菜页面，选择已存在成品菜,然后进入编辑成品菜页面进入添加原料界面
            mv.addObject("pageInfo", 0);
        }
        else
        {//1表示:编辑页面进入添加原料界面
            mv.addObject("pageInfo", 1);
        }
        mv.addObject("productDto", product_Dto);
        mv.addObject("listCategory", listCategory);
        mv.addObject("listStyle", listStyle);
        mv.addObject("listTaste", listTaste);
        mv.addObject("listColor", listColor);
        mv.addObject("listShape", listShape);
        mv.addObject("listCuisine", listCuisine);
        mv.addObject("materialIdList", idList);
        mv.addObject("materialNameList", nameList);
        mv.addObject("nutritionList", nutrition_NameList);
        mv.setViewName("cookbook/product/product_edit");
        return mv;
    }

    /**
     * 跳转到上传图片页面
     */
    @RequestMapping(value = "/uploadPage")
    public ModelAndView uploadPage(String id) throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        ProductDto product_Dto = productService.findById(id);
        mv.addObject("productDto", product_Dto);
        mv.setViewName("cookbook/product/product_uploadPage");
        return mv;
    }

    @RequestMapping(value = "/upload")
    public ModelAndView upload(@RequestParam(value = "imgUrl") MultipartFile file,
        @RequestParam("productId") String productId) throws Exception
    {
        ProductDto productDto = productService.findById(productId);
        ModelAndView mv = this.getModelAndView();
        String filePath = Const.IMAGEPATH; //图片上传路径
        String fileName = FileUpload.fileUp(file, filePath, UUID.randomUUID().toString()); //执行上传
        productDto.setImgUrl(filePath + fileName);
        productService.editForUpload(productDto);
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 编辑成品菜
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Json edit(ProductDto productDto) throws Exception
    {
        Json j = new Json();
        ProductDto product_Dto = productService.findByName(productDto.getName());
        //重名判断
        if (!StringUtils.isEmpty(product_Dto.getId()))
        {
            //查找6种不同类型的成分是否已经存在;
            List<ProductDto> list = productService.findDistinctComponents(productDto, null);
            if (!CollectionUtils.isEmpty(list))
            {
                for (ProductDto dto : list)
                {
                    if (!dto.getId().equals(productDto.getId()))
                    {
                        j.setSuccess(false);
                        j.setMsg("该成品菜配料组合已经存在，请勿重复添加.");
                        j.setObj(productDto);
                        return j;
                    }
                }

            }

            if (product_Dto.getStat() == 1 && !StringUtils.isEmpty(product_Dto.getId())
                && !StringUtils.isEmpty(product_Dto.getName()))
            {
                if (!productDto.getId().equals(product_Dto.getId()))
                {
                    j.setSuccess(false);
                    j.setMsg("成品菜名称重复，不能重名.");
                    j.setObj(productDto);
                    return j;
                }
            }
        }

        productService.edit(productDto);//判断新增权限
        j.setSuccess(true);
        j.setMsg("编辑成品菜成功");
        j.setObj(productDto);
        return j;
    }

    /**
     * 跳转到编辑成品菜页面
     */
    @RequestMapping(value = "/hasProduct")
    @ResponseBody
    public Json hasProduct(String productName) throws Exception
    {
        Json json = new Json();
        ModelAndView mv = this.getModelAndView();
        ProductDto productDto = new ProductDto();
        productDto.setName(productName);
        List<ProductDto> listdto = productService.findListByPage(productDto, null);
        if (!CollectionUtils.isEmpty(listdto))
        {
            json.setObj(listdto);
            json.setSuccess(true);
        }
        else
        {
            json.setSuccess(false);
        }
        return json;
    }

    /**
     * 跳转到成品菜-新增原料选择页面
     */
    @RequestMapping(value = "/materialAddPage")
    public ModelAndView materialAddPage(String productId, String pageInfo, String materianName)
        throws Exception
    {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setName(materianName);
        List<MaterialDto> listMaterialDto = materialService.findListByPage(materialDto);
        ModelAndView mv = this.getModelAndView();
        if (!StringUtils.isEmpty(pageInfo))
        {
            if (pageInfo.equals("edit") || pageInfo.equals("chooseEdit") || pageInfo.equals("1"))
            {//编辑成品菜页面进入添加原料界面 或者 添加成品菜页面，选择已存在成品菜,然后进入编辑成品菜页面进入添加原料界面
                mv.addObject("pageInfo", 1);
            }
            else if (pageInfo.equals("add") || pageInfo.equals("0"))
            {//添加成品菜界面页面进入添加原料界面
                mv.addObject("pageInfo", 0);
            }
        }
        mv.setViewName("cookbook/product/product_material_add");
        mv.addObject("listMaterial", listMaterialDto);
        mv.addObject("productId", productId);
        return mv;
    }

    /**
     * 跳转到成品菜-新增原料选择页面
     */
    @RequestMapping(value = "/materialAdd")
    @ResponseBody
    public Json materialAdd(ProductDto productDto, String materialId) throws Exception
    {
        Json j = new Json();
        MaterialDto matDto = materialService.findById(materialId);

        //原料名称集合
        List<String> materialNameList = new ArrayList<String>();
        //原料id集合
        List<String> materialIdList = new ArrayList<String>();
        //营养集合
        List<String> nutritionNameList = new ArrayList<String>();
        //原料克重集合
        List<Integer> materialWeightList = new ArrayList<Integer>();

        //集合中加入新添加的原料 id
        if (matDto != null)
        {
            materialNameList.add(matDto.getName());
            materialIdList.add(matDto.getId());
        }

        if (!StringUtils.isEmpty(productDto.getMaterialWeight()))
        {
            materialWeightList.add(productDto.getMaterialWeight());
        }

        if (!CollectionUtils.isEmpty(productDto.getMaterialWeightList()))
        {
            for (Integer mater_weight : productDto.getMaterialWeightList())
            {
                materialWeightList.add(mater_weight);
            }
        }

        Double singleCost = 0.0;
        Double materialWeight = 0.0;
        //集合中加入之前存在的原料id
        if (!CollectionUtils.isEmpty(productDto.getMaterialIdList()))
        {
            for (String id : productDto.getMaterialIdList())
            {
                if (id.equals(materialId))
                {
                    j.setSuccess(false);
                    j.setMsg("已添加原料:" + matDto.getName());
                    j.setObj(productDto);
                    return j;
                }
                MaterialDto dto = materialService.findById(id);
                if (dto != null)
                {
                    materialNameList.add(dto.getName());
                    materialIdList.add(id);
                }
            }
        }

        for (String material_id : materialIdList)
        {
            MaterialDto dto = materialService.findById(material_id);
            if (materialId.equals(material_id))
            {
                if (!StringUtils.isEmpty(productDto.getMaterialWeight()))
                {
                    Double weight = Double.valueOf(productDto.getMaterialWeight()) / 1000;
                    singleCost += dto.getCost().doubleValue() / 100 * weight;
                }
                else
                {
                    singleCost += dto.getCost().doubleValue() / 100;
                }
            }

            List<MaterialNutritionDto> nutritionList = materialNutritionService.findByMaterialId(material_id);
            for (MaterialNutritionDto materNutriDto : nutritionList)
            {
                NutritionDto nuDto = nutritionService.findById(materNutriDto.getNutritionId());
                nutritionNameList.add(nuDto.getName());
            }
        }
        if (!StringUtils.isEmpty(productDto.getSingleCost()))
        {
            singleCost += Double.valueOf(productDto.getSingleCost());
        }

        productDto.setMaterialWeightList(materialWeightList);
        productDto.setSingleCost(Math.round(singleCost * 100) / 100.0);
        productDto.setMaterialNameList(materialNameList);
        productDto.setMaterialIdList(materialIdList);
        List<String> nutrition_NameList = ListUtils.getNewList(nutritionNameList);
        productDto.setNutritionNameList(nutrition_NameList);
        j.setSuccess(true);
        j.setMsg("新增成功");
        j.setObj(productDto);
        return j;
    }

    /**
     * 
     * delete：删除成品菜
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
            ProductDto productDto = productService.findById(id);
            if (productDto != null)
            {
                //删除成品菜,需要先删除 成品菜-原料关系表
                ProductMaterialDto productMaterialDto = new ProductMaterialDto();
                productMaterialDto.setProductId(id);
                List<ProductMaterialDto> list =
                    productMaterialService.findListByPage(productMaterialDto, null);
                if (!CollectionUtils.isEmpty(list))
                {//执行批量删除:成品菜-原料 操作
                    for (ProductMaterialDto pro_mat_Dto : list)
                    { //删除
                        productMaterialService.delete(pro_mat_Dto);
                    }
                }
                productDto.setStat(DataStatus.DISABLED);
                productService.delete(productDto);
                j.setMsg("删除成品菜成功！");
                j.setSuccess(true);
            }
            else
            {
                j.setMsg("删除成品菜失败,请联系管理员!");
                j.setSuccess(false);
            }
        }
        return j;
    }

    /**
     * 计算原料总成本
     */
    @RequestMapping(value = "/totalCost")
    @ResponseBody
    public Json totalCost(Double singleCost, Double demandNumber) throws Exception
    {
        Json json = new Json();

        if (demandNumber != null)
        {
            json.setSuccess(true);

            json.setObj(Math.round(singleCost * demandNumber * 100) / 100.0);
        }
        else
        {
            json.setSuccess(false);
            json.setObj(Math.round(singleCost * demandNumber * 100) / 100.0);
            json.setMsg("需求数量不能为空!");
        }
        return json;
    }

    //展示该成品菜下的原料
    @RequestMapping("/findMaterialByProductId")
    @ResponseBody
    public List<MaterialDto> findNuByMid(String productId)
    {
        List<MaterialDto> list = new ArrayList<MaterialDto>();
        if (!StringUtils.isEmpty(productId))
        {
            ProductMaterialDto productMaterialDto = new ProductMaterialDto();
            productMaterialDto.setProductId(productId);
            List<ProductMaterialDto> proMatlist =
                productMaterialService.findListByPage(productMaterialDto, null);
            if (!CollectionUtils.isEmpty(proMatlist))
            {
                for (ProductMaterialDto dto : proMatlist)
                {
                    if (!StringUtils.isEmpty(dto.getMaterialId()))
                    {
                        MaterialDto dtos = materialService.findById(dto.getMaterialId());
                        if (dtos != null && dtos.getStat() == DataStatus.ENABLED)
                        {
                            list.add(materialService.findById(dto.getMaterialId()));
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 打开上传EXCEL页面
     */
    @RequestMapping(value = "/productUploadExcel")
    public ModelAndView productUploadExcel() throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("cookbook/product/product_upload_excel");
        return mv;
    }

    /**
     * 从EXCEL导入成品菜到数据库
     */
    @RequestMapping(value = "/importExcel")
    // required=false表示不传的话，会给参数赋值为null，required=true就是必须要有  
    public ModelAndView importExcel(@RequestParam(value = "excel", required = false) MultipartFile file)
        throws Exception
    {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        String result = "";
        if (null != file && !file.isEmpty())
        {
            String filePath = PathUtil.getClassResources() + Const.FILEPATHFILE; //文件上传路径
            String fileName = FileUpload.fileUp(file, filePath, UUID.randomUUID().toString()); //执行上传
            List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0); //执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
            /*存入数据库操作======================================*/
            System.out.println("----------------数据集合长度:--------------------" + listPd.size());
            if (!CollectionUtils.isEmpty(listPd))
            {
                result = importExcel(listPd);
            }
            else
            {
                result = "导入数据不存在";
            }
            /*存入数据库操作======================================*/
            mv.addObject("msg", "error");
            mv.addObject("uploadmsg", result);
        }
        mv.setViewName("cookbook/material/upload_result");
        return mv;
    }

    /**
     * var0 :名称
     * var1 :类别
     * var2 :菜系
     * var3 :口味
     * var4 :形状
     * var5 :烹饪
     * var6 :颜色
     * var7 :原料
     * var8 :单份定价
     * var9 :单份克重
     * var10 :需求数
     * var11 :备注
     */
    public String importExcel(List<PageData> listPd)
    {
        String result = "";
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < listPd.size(); i++)
        { //单份定价  单份克重 需求数 的长度都必须小于7,否则返回

            if (listPd.get(i).getString("var8").length() > 7 || listPd.get(i).getString("var9").length() > 7
                || listPd.get(i).getString("var10").length() > 7)
            {
                result +=
                    "[" + listPd.get(i).getString("var0").trim() + "] 的(单份定价)或(单份克重)或(需求数)的长度大于于7,导入失败。";
                continue;
            }
            Product p = new Product();
            p.setId(UUID.randomUUID().toString());
            ProductDto productDto = new ProductDto();
            productDto.setName(listPd.get(i).getString("var0").trim());
            List<ProductDto> listDto = productService.findListByPage(productDto, null);

            if (!CollectionUtils.isEmpty(listDto))
            {
                //成品菜名称如果重复，则返回;
                result += "  [" + listPd.get(i).getString("var0").trim() + "]名称已经存在,导入失败。";
                continue;
            }
            p.setName(listPd.get(i).getString("var0").trim());
            //类别
            if (!StringUtils.isEmpty(listPd.get(i).getString("var1")))
            {
                ProductCategoryDto category =
                    categoryService.findByName(listPd.get(i).getString("var1").trim());
                if (category == null)
                { //如果类别为空,需要重新创建一个类别
                    ProductCategoryDto productCategoryDto = new ProductCategoryDto();
                    productCategoryDto.setId(UUID.randomUUID().toString());
                    productCategoryDto.setName(listPd.get(i).getString("var1").trim());
                    categoryService.add(productCategoryDto);
                    p.setProductCategoryId(productCategoryDto.getId());
                }
                else
                {
                    p.setProductCategoryId(category.getId());
                }
            }
            else
            {
                return "类别不能为空";
            }
            //菜系
            if (!StringUtils.isEmpty(listPd.get(i).getString("var2")))
            {
                ProductStyleDto style = styleService.findByName(listPd.get(i).getString("var2").trim());
                if (style == null)
                { //如果菜系为空,需要重新创建一个菜系
                    ProductStyleDto productStyleDto = new ProductStyleDto();
                    productStyleDto.setId(UUID.randomUUID().toString());
                    productStyleDto.setName(listPd.get(i).getString("var2").trim());
                    styleService.add(productStyleDto);
                    p.setProductStyleId(productStyleDto.getId());
                }
                else
                {
                    p.setProductStyleId(style.getId());
                }
            }
            else
            {
                return "菜系不能为空";
            }
            //口味
            if (!StringUtils.isEmpty(listPd.get(i).getString("var3")))
            {
                ProductTasteDto taste = tasteService.findByName(listPd.get(i).getString("var3").trim());
                if (taste == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductTasteDto productTasteDto = new ProductTasteDto();
                    productTasteDto.setId(UUID.randomUUID().toString());
                    productTasteDto.setName(listPd.get(i).getString("var3").trim());
                    tasteService.add(productTasteDto);
                    p.setProductTasteId(productTasteDto.getId());
                }
                else
                {
                    p.setProductTasteId(taste.getId());
                }
            }
            else
            {
                return "口味不能为空";
            }
            //形状
            if (!StringUtils.isEmpty(listPd.get(i).getString("var4")))
            {
                ProductShapeDto shape = shapeService.findByName(listPd.get(i).getString("var4").trim());
                if (shape == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductShapeDto productShapeDto = new ProductShapeDto();
                    productShapeDto.setId(UUID.randomUUID().toString());
                    productShapeDto.setName(listPd.get(i).getString("var4").trim());
                    shapeService.add(productShapeDto);
                    p.setProductShapeId(productShapeDto.getId());
                }
                else
                {
                    p.setProductShapeId(shape.getId());
                }
            }
            else
            {
                return "形状不能为空";
            }
            //烹饪
            if (!StringUtils.isEmpty(listPd.get(i).getString("var5")))
            {
                ProductCuisineDto cuisine = cuisineService.findByName(listPd.get(i).getString("var5").trim());
                if (cuisine == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductCuisineDto productCuisineDto = new ProductCuisineDto();
                    productCuisineDto.setId(UUID.randomUUID().toString());
                    productCuisineDto.setName(listPd.get(i).getString("var5").trim());
                    cuisineService.add(productCuisineDto);
                    p.setProductCuisineId(productCuisineDto.getId());
                }
                else
                {
                    p.setProductCuisineId(cuisine.getId());
                }
            }
            else
            {
                return "烹饪方式不能为空";
            }
            //颜色
            if (!StringUtils.isEmpty(listPd.get(i).getString("var6")))
            {
                ProductColorDto color = colorService.findByName(listPd.get(i).getString("var6").trim());
                if (color == null)
                { //如果口味为空,需要重新创建一个口味
                    ProductColorDto productColorDto = new ProductColorDto();
                    productColorDto.setId(UUID.randomUUID().toString());
                    productColorDto.setName(listPd.get(i).getString("var6").trim());
                    colorService.add(productColorDto);
                    p.setProductColorId(productColorDto.getId());
                }
                else
                {
                    p.setProductColorId(color.getId());
                }
            }
            else
            {
                return "颜色不能为空";
            }
            //原料
            if (!StringUtils.isEmpty(listPd.get(i).getString("var7")))
            {
                if (listPd.get(i).getString("var7").contains(","))
                {
                    String[] strarray = listPd.get(i).getString("var7").trim().split(",");
                    String k = null;
                    Long singleCost = 0L;
                    for (int j = 0; j < strarray.length; j++)
                    {
                        k = (strarray[j]).toString();
                        //如果原料不存在 则导入失败
                        boolean isExist = productService.materialIsExist(k.trim());
                        if (isExist == true)
                        {
                            productService.saveProductMaterial(p, k);
                            MaterialDto material = materialService.findByName(k);
                            if (material != null && material.getStat() == 1)
                            {
                                singleCost += material.getCost();
                            }
                            p.setSingleCost(singleCost);
                        }
                        else
                        {
                            result +=
                                "  [" + listPd.get(i).getString("var0").trim() + "]的原料:(" + k.trim()
                                    + ")不存在,无法导入。";
                            continue;
                        }
                    }
                }
                else
                {
                    //如果原料不存在 则导入失败
                    boolean isExist = productService.materialIsExist(listPd.get(i).getString("var7").trim());
                    if (isExist == true)
                    {
                        //添加原料
                        productService.saveProductMaterial(p, listPd.get(i).getString("var7").trim());
                    }
                    else
                    {
                        result +=
                            "  [" + listPd.get(i).getString("var0").trim() + "]的原料:("
                                + listPd.get(i).getString("var7").trim() + ")不存在,无法导入。";
                        continue;
                    }
                }
            }
            //单份定价
            if (!StringUtils.isEmpty(listPd.get(i).getString("var8")))
            {
                p.setEachPricing(Double.valueOf(listPd.get(i).getString("var8").trim()).longValue());
            }
            //单份克重
            if (!StringUtils.isEmpty(listPd.get(i).getString("var9")))
            {
                p.setSingleWeight(Integer.valueOf(listPd.get(i).getString("var9").trim()));
            }
            //需求数
            if (!StringUtils.isEmpty(listPd.get(i).getString("var10")))
            {
                p.setDemandNumber(Integer.valueOf(listPd.get(i).getString("var10").trim()));
            }
            //备注

            if (!StringUtils.isEmpty(listPd.get(i).getString("var11")))
            {
                p.setRemark(listPd.get(i).getString("var11").trim());
            }
            //总成本
            if (!StringUtils.isEmpty(p.getSingleCost()) && !StringUtils.isEmpty(p.getDemandNumber()))
            {
                BigDecimal b1 = new BigDecimal(Double.valueOf(p.getSingleCost()));
                BigDecimal b2 = new BigDecimal(Double.valueOf(p.getDemandNumber()));
                //放入总成本
                p.setTotalCost(b1.multiply(b2).longValue());
            }
            list.add(p);
        }
        if (!CollectionUtils.isEmpty(list))
        {
            for (Product p : list)
            {
                if (p.getSingleCost() == null || p.getTotalCost() == null)
                {
                    continue;
                }
                ProductDto pDto = new ProductDto();
                BeanUtils.copyProperties(p, pDto);
                LimitPageDto limitPageDto = new LimitPageDto();
                //查找6种不同类型的成分是否已经存在;
                List<ProductDto> list2 = productService.findDistinctComponents(pDto, limitPageDto);
                if (!CollectionUtils.isEmpty(list2))
                {//如果存在重复的配菜,则直接退出for循环
                    result += " [" + p.getName().trim() + "]的配菜组合已经存在,无法导入。";
                    continue;
                }
                p.setStat(DataStatus.ENABLED);
                p.setCreateTime(new Date());
                p.setLastUpdateTime(new Date());
                productDao.insert(p);

            }

        }
        if (StringUtils.isEmpty(result))
        {
            result = "导入成品菜成功!";
        }
        return result;
    }

    /**
     * 
     * @author pengcheng.yang
     * @desc 导出成品菜信息到EXCEL
     * @return  ModelAndView
     */
    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel()
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

                titles.add("名称"); //1
                titles.add("类别"); //2
                titles.add("菜系"); //3
                titles.add("口味"); //4
                titles.add("形状"); //5
                titles.add("烹饪"); //6
                titles.add("颜色"); //7
                titles.add("原料"); //8
                titles.add("单份成本(元)"); //9
                titles.add("单份定价(元)"); //10
                titles.add("单份克重(克)"); //11
                titles.add("需求数(份)"); //12
                titles.add("备注"); //13
                titles.add("总成本(元)"); //14
                dataMap.put("titles", titles);

                List<PageData> expList = productService.expExcelFindAll(pd);
                List<PageData> varList = new ArrayList<PageData>();
                for (int i = 0; i < expList.size(); i++)
                {
                    PageData vpd = new PageData();
                    vpd.put("var1", expList.get(i).getString("pname")); //1
                    vpd.put("var2", expList.get(i).getString("cname")); //2
                    vpd.put("var3", expList.get(i).getString("sname")); //3
                    vpd.put("var4", expList.get(i).getString("tname")); //4
                    vpd.put("var5", expList.get(i).getString("psname")); //5
                    vpd.put("var6", expList.get(i).getString("pcname")); //6
                    vpd.put("var7", expList.get(i).getString("coname")); //7
                    vpd.put("var8", expList.get(i).getString("mname")); //8
                    vpd.put("var9",
                        Double.valueOf(expList.get(i).getString("single_cost")).doubleValue() / 100); //9
                    vpd.put("var10", expList.get(i).getString("each_pricing")); //10
                    vpd.put("var11", expList.get(i).getString("single_weight")); //11
                    vpd.put("var12", expList.get(i).getString("demand_number")); //12
                    vpd.put("var13", expList.get(i).getString("remark")); //13
                    vpd.put("var14",
                        Double.valueOf(expList.get(i).getString("total_cost")).doubleValue() / 100); //14
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

    /**
     * 下载模版
     */
    @RequestMapping(value = "/downExcel")
    public void downExcel(HttpServletResponse response) throws Exception
    {

        FileDownload.fileDownload(response,
            PathUtil.getClasspath() + Const.FILEPATHFILE + "products.xls",
            "Products.xls");
    }

    public static boolean IsIntNumber(String str)
    {
        String regex = "^\\+?[1-9][0-9]*$";
        return match(regex, str);
    }

    /**
    * @param regex
    * 正则表达式字符串
    * @param str
    * 要匹配的字符串
    * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
    */
    private static boolean match(String regex, String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
