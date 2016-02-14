package com.ssic.cookbook.manager.util;

public class CookbookFields {

	
	public static int Enable = 1;    //stat 为 1
	
	public static int DisEnable = 0;   //stat 为 0
	
    public static String Is_Sensitive_Material = "is_sensitive_material";  //是否敏感食材 1:是 0:否
	
	public static String Material_Type = "material_type";  //原料类型:1原料,2:半成品
	
	public static String Material_Nutrition = "material_nutrition";   //原料营养
	
	public static String Nutrition_Unit = "Nutrition_Unit";  //营养单位
	
	public static String Material_Compare = "material_compare";  //原料比较符
	
	public static String Return_Success = "200";   //返回成功
	
	public static String Return_Fail = "500";   //返回失败
	
	public static int upload_size_max = 500;  //上传最大条数
}
