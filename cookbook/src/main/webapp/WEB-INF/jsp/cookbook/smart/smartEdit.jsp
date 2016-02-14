<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/inputiconplus.js"></script><!-- 加减号 -->
<script type="text/javascript">
	$(top.hangge());
</script>
</head>
<body>
	<!-- 类别-->
	<div id="hiddType" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" style="vertical-align: top; width: 150px;" name="categoryId">
				<c:forEach items="${listCategory}" var="category" varStatus="vs">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select> 
			<input type="text" name="categoryCount" placeholder="数量" title="数量" style="width: 44px; margin-bottom:0" />份 
			<a class="btn onMinusType"><i class="icon-minus"></i></a>
		</div>
	</div>
	
	<!-- 菜系-->
	<div id="hiddStyle" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" name="styleId" style-name="${style.name}" data-placeholder="请选择菜系" style="vertical-align: top; width: 150px;">
					<!-- <option value="">请选择</option> -->
					<c:forEach items="${listStyle}" var="style">
						<option value="${style.id}">${style.name}</option>
					</c:forEach>
			</select> 
			<a class="btn onMinusStyle"><i class="icon-minus"></i></a>
		</div>
	</div>
	
	<!-- 形状-->
	<div id="hiddShape" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" name="shapeId" shape-name="${shape.name}"
					  data-placeholder="请选择形状" style="vertical-align: top; width: 150px;">
					<!--  <option value="">请选择</option> -->
					 <c:forEach items="${listShape}" var="shape">
						<option value="${shape.id}">${shape.name}</option>
					 </c:forEach>
			</select>
			<a class="btn onMinusShape"><i class="icon-minus"></i></a>
		</div>
	</div>
	
	<!-- 口味-->
	<div id="hiddTaste" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" name="tasteId" taste-name="${taste.name}"
					 data-placeholder="请选择口味" style="vertical-align: top; width: 150px;">
					<!-- <option value="">请选择</option> -->
					<c:forEach items="${listTaste}" var="taste">
						<option value="${taste.id}">${taste.name}</option>
					</c:forEach>
			</select> 
			<a class="btn onMinusTaste"><i class="icon-minus"></i></a>
		</div>
	</div>
	
	<!-- 烹饪-->
	<div id="hiddCuisine" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" name="cuisineId" taste-name="${cuisine.name}"
					 data-placeholder="请选择烹饪"
					style="vertical-align: top; width: 150px;">
					<!-- <option value="">请选择</option> -->
					<c:forEach items="${listCuisine}" var="cuisine">
						<option value="${cuisine.id}">${cuisine.name}</option>
					</c:forEach>
			</select> 
			<a class="btn onMinusCuisine"><i class="icon-minus"></i></a>
		</div>
	</div>
	
	<!-- 颜色-->
	<div id="hiddColor" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" name="colorId" taste-name="${color.name}"
					data-placeholder="请选择颜色"
					style="vertical-align: top; width: 150px;">
					<!-- <option value="">请选择</option> -->
					<c:forEach items="${listColor}" var="color">
						<option value="${color.id}">${color.name}</option>
					</c:forEach>
			</select> 
			<a class="btn onMinusColor"><i class="icon-minus"></i></a>
		</div>
	</div>

	<!-- 营养-->
	<div id="hiddNutrition" style="display: none;">
		<div class="controls">
			<select class="input-xlarge" style="vertical-align: top; width: 150px;" name="nutritionId">
				<c:forEach items="${listNutrition}" var="nutrition" varStatus="vs">
					<option value="${nutrition.id}">${nutrition.paramName}</option>
				</c:forEach>
			</select> 
			<select class="input-xlarge" style="vertical-align: top; width: 50px;" name="nutritionOperator">
				<option value="&lt;" selected="">&lt;</option>
				<option value="&gt;">&gt;</option>
				<option value="=">=</option>
			</select> 
			<input type="text" name="nutritionContent" placeholder="含量" title="含量" style="width: 44px; margin-bottom:0" />
			<a class="btn onMinusNutrition"><i class="icon-minus"></i></a>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span12">
						<form class=".form-inline" id="smartForm">
							<!-- <legend class="">智能配菜</legend> -->
							<!-- <input type="hidden" name="fixingsName" id="fixingsName"/> -->
							<input type="hidden" name="productStyleName" id="productStyleName"/>
							<input type="hidden" name="productShapeName" id="productShapeName"/>
							<input type="hidden" name="productTasteName" id="productTasteName"/>
							<input type="hidden" name="productCuisineName" id="productCuisineName"/>
							<input type="hidden" name="productColorName" id="productColorName"/>
							
							<input type="hidden" name="categoryList" id="categoryList"/>
							<input type="hidden" name="nutritionList" id="nutritionList"/>
							<input type="hidden" name="smartId" id="smartId"/>
							<input type="hidden" name="id" value="${intelligentFixingsDto.id}"/>
							<input type="hidden" name="fixingsName" value="${intelligentFixingsDto.fixingsName}"/>
							<br>
							套餐名称:<input type="text" name="setMealName" id="categoryList" value="${intelligentFixingsDto.setMealName}"/>
							
							<fieldset>
								<div class="control-group" id="categoryGroup">类别 
									<!-- Select Basic -->
									<c:forEach items="${intelligentFixingsDto.category}" var="categoryCheck"  varStatus="categoryCheckStatus">
									<div class="controls">
										<select class="input-xlarge"
											style="vertical-align: top; width: 150px;" name="categoryId">
											<c:forEach items="${listCategory}" var="category" varStatus="vs">
											<c:choose>  
											   <c:when test="${category.id == categoryCheck.categoryId}">  
											   		<option value="${category.id}"  selected = "selected">${category.name}</option>
											   </c:when>  
											   <c:otherwise>   
											   		<option value="${category.id}">${category.name}</option>
											   </c:otherwise>  
											</c:choose> 
											</c:forEach>
										</select>
										<input type="text" name="categoryCount" placeholder="数量" title="数量"
											style="width: 44px; margin-bottom:0" value="${categoryCheck.categoryCount}"/><span>份</span>
											<!--添加按钮-减 -->
											<c:if test="${categoryCheckStatus.index != 0}">
												<a class="btn onMinusType"><i class="icon-minus"></i></a>
											 </c:if>
											 <!--添加按钮-加 -->
											<c:if test="${categoryCheckStatus.index == 0}">
												<a class="btn onAddType" id="onAddType"><i class="icon-plus"></i></a>
											</c:if>
									</div>
									</c:forEach>
								</div>
							</fieldset>
							<hr>
							<fieldset>
								<div class="control-group">
									<label class="control-label">属性选择</label>
								</div>
								<div class="control-group" id="styleGroup">菜系
								<c:forEach items="${intelligentFixingsDto.style}" var="styleCheck" varStatus="styleCheckStatus">
									<div class="controls">
										 <select class="input-xlarge" name="styleId" style-name="${style.name}"  
											data-placeholder="请选择菜系"
											style="vertical-align: top; width: 150px;">
											<!-- <option value="">请选择</option> -->
											<c:forEach items="${listStyle}" var="style">
												<c:choose>  
												   <c:when test="${style.id == styleCheck.styleId}">  
												   		<option value="${style.id}"  selected = "selected">${style.name}</option>
												   </c:when>  
												   <c:otherwise>   
												   		<option value="${style.id}">${style.name}</option>
												   </c:otherwise>  
												</c:choose> 
											</c:forEach>
										</select>
										<!--添加菜系按钮-减 -->
										<c:if test="${styleCheckStatus.index != 0}">
											<a class="btn onMinusStyle" id="onMinusStyle"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加菜系按钮-加 -->
										<c:if test="${styleCheckStatus.index == 0}">
											<a class="btn onAddStyle" id="onAddStyle"><i class="icon-plus"></i></a>
										</c:if>
									</div>
									</c:forEach>
								</div>
								<div class="control-group" id="shapeGroup">形状
								<c:forEach items="${intelligentFixingsDto.shape}" var="shapeCheck" varStatus="shapeCheckStatus">
									<div class="controls">
										 <select class="input-xlarge" name="shapeId" shape-name="${shape.name}"
											id="productShapeId" data-placeholder="请选择形状"
											style="vertical-align: top; width: 150px;">
											<!-- <option value="">请选择</option> -->
											<c:forEach items="${listShape}" var="shape">
												<c:choose>  
												   <c:when test="${shape.id == shapeCheck.shapeId}">  
												   		<option value="${shape.id}"  selected = "selected">${shape.name}</option>
												   </c:when>  
												   <c:otherwise>   
												   		<option value="${shape.id}">${shape.name}</option>
												   </c:otherwise>  
												</c:choose>
											</c:forEach>
										</select>
										<!--添加按钮-减 -->
										<c:if test="${shapeCheckStatus.index != 0}">
										<a class="btn onMinusShape" id="onMinusShape"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加按钮-加 -->
										<c:if test="${shapeCheckStatus.index == 0}">
										<a class="btn onAddShape" id="onAddShape"><i class="icon-plus"></i></a>
										</c:if>
									</div>
								</c:forEach>
								</div>
								<div class="control-group" id="tasteGroup">口味
								<c:forEach items="${intelligentFixingsDto.taste}" var="tasteCheck" varStatus="tasteCheckStatus">
									<div class="controls">
										 <select class="input-xlarge" name="tasteId" taste-name="${taste.name}"
											id="productTasteId" data-placeholder="请选择口味"
											style="vertical-align: top; width: 150px;">
											<!-- <option value="">请选择</option> -->
											<c:forEach items="${listTaste}" var="taste">
												<c:choose>  
												   <c:when test="${taste.id == tasteCheck.tasteId}">  
												   		<option value="${taste.id}"  selected = "selected">${taste.name}</option>
												   </c:when>  
												   <c:otherwise>   
												   		<option value="${taste.id}">${taste.name}</option>
												   </c:otherwise>  
												</c:choose>
											</c:forEach>
										</select>
										<!--添加按钮-减 -->
										<c:if test="${tasteCheckStatus.index != 0}">
											<a class="btn onMinusTaste" id="onMinusTaste"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加按钮-加 -->
										<c:if test="${tasteCheckStatus.index == 0}">
											<a class="btn onAddTaste" id="onAddTaste"><i class="icon-plus"></i></a>
										</c:if>
									</div>
								</c:forEach>
								</div>
								<div class="control-group" id="cuisineGroup">烹饪
								<c:forEach items="${intelligentFixingsDto.cuisine}" var="cuisineCheck" varStatus="cuisineCheckStatus">
									<div class="controls">
										 <select class="input-xlarge" name="cuisineId" taste-name="${cuisine.name}"
											id="productCuisineId" data-placeholder="请选择烹饪"
											style="vertical-align: top; width: 150px;">
											<!-- <option value="">请选择</option> -->
											<c:forEach items="${listCuisine}" var="cuisine">
												<c:choose>  
												   <c:when test="${cuisine.id == cuisineCheck.cuisineId}">  
												   		<option value="${cuisine.id}"  selected = "selected">${cuisine.name}</option>
												   </c:when>  
												   <c:otherwise>   
												   		<option value="${cuisine.id}">${cuisine.name}</option>
												   </c:otherwise>  
												</c:choose>
											</c:forEach>
										</select>
										<!--添加按钮-减 -->
										<c:if test="${cuisineCheckStatus.index != 0}">
											<a class="btn onMinusCuisine" id="onMinusCuisine"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加按钮-加 -->
										<c:if test="${cuisineCheckStatus.index == 0}">
											<a class="btn onAddCuisine" id="onAddCuisine"><i class="icon-plus"></i></a>
										</c:if>
									</div>
									</c:forEach>
								</div>
								<div class="control-group" id="colorGroup">颜色
								<c:forEach items="${intelligentFixingsDto.color}" var="colorCheck" varStatus="colorCheckStatus">
									<div class="controls">
										<select class="input-xlarge" name="colorId" taste-name="${color.name}" 
											data-placeholder="请选择颜色"
											style="vertical-align: top; width: 150px;">
											<!-- <option value="">请选择</option> -->
											<c:forEach items="${listColor}" var="color">
												<c:choose>  
												   <c:when test="${color.id == colorCheck.colorId}">  
												   		<option value="${color.id}"  selected = "selected">${color.name}</option>
												   </c:when>  
												   <c:otherwise>   
												   		<option value="${color.id}">${color.name}</option>
												   </c:otherwise>  
												</c:choose>
											</c:forEach>
										</select>
										<!--添加按钮-减 -->
										<c:if test="${colorCheckStatus.index != 0}">
											<a class="btn onMinusColor" id="onMinusColor"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加按钮-加 -->
										<c:if test="${colorCheckStatus.index == 0}">
											<a class="btn onAddColor" id="onAddColor"><i class="icon-plus"></i></a>
										</c:if>
									</div>
								</c:forEach>
								</div>
							</fieldset>
							<hr>
							<div class="control-group">
								<label class="control-label">配菜总成本 <select
									class="input-xlarge" style="vertical-align: top; width: 50px;"
									name="totalCostOperator">
									<c:if test="${intelligentFixingsDto.totalCostOperator == '<'}">
										<option value="&lt;" selected="selected">&lt;</option>
										<option value="&gt;">&gt;</option>
										<option value="=">=</option>
									</c:if>
									<c:if test="${intelligentFixingsDto.totalCostOperator == '>'}">
										<option value="&lt;">&lt;</option>
										<option value="&gt;" selected="selected">&gt;</option>
										<option value="=">=</option>
									</c:if>
									<c:if test="${intelligentFixingsDto.totalCostOperator == '='}">
										<option value="&lt;">&lt;</option>
										<option value="&gt;">&gt;</option>
										<option value="=" selected="selected">=</option>
									</c:if>
								</select> <input type="text" name="totalCost" id="totalCost"
									placeholder="金额" title="配菜总成本" style="width: 120px;" value="${intelligentFixingsDto.totalCostStr}"/>/元
								</label>
							</div>
							<div class="control-group">
								<label class="control-label">配菜总定价 <select
									class="input-xlarge" style="vertical-align: top; width: 50px;"
									name="totalFixedPriceOperator">
									<c:if test="${intelligentFixingsDto.totalFixedPriceOperator == '<'}">
										<option value="&lt;" selected="selected">&lt;</option>
										<option value="&gt;">&gt;</option>
										<option value="=">=</option>
									</c:if>
									<c:if test="${intelligentFixingsDto.totalFixedPriceOperator == '>'}">
										<option value="&lt;">&lt;</option>
										<option value="&gt;" selected="selected">&gt;</option>
										<option value="=">=</option>
									</c:if>
									<c:if test="${intelligentFixingsDto.totalFixedPriceOperator == '='}">
										<option value="&lt;">&lt;</option>
										<option value="&gt;">&gt;</option>
										<option value="=" selected="selected">=</option>
									</c:if>
								</select> <input type="text" name="totalFixedPrice" id="totalFixedPrice"
									placeholder="金额" title="配菜总定价" style="width: 120px;"  value="${intelligentFixingsDto.totalFixedPriceStr}"/>/元
								</label>
							</div>
							<!-- 
							<label style="float:left;padding-left: 20px;"><input type="radio" checked="checked" value="0" id="isSensitiveMaterial" name="isSensitiveMaterial"><span style="width:60px" class="lbl">否</span></label>
							 -->
							<div class="control-group" id="nutritionGroup">营养 
							<c:forEach items="${intelligentFixingsDto.nutrition}" var="nutritionCheck" varStatus="nutritionCheckStatus">
									<div class="controls">
									<select class="input-xlarge" style="vertical-align: top; width: 150px;" name="nutritionId">
											<c:forEach items="${listNutrition}" var="nutrition" varStatus="vs">
											   <c:choose>  
											   <c:when test="${nutrition.id == nutritionCheck.nutritionId}">  
											   		<option value="${nutrition.id}"  selected = "selected">${nutrition.paramName}</option>
											   </c:when>  
											   <c:otherwise>   
											   		<option value="${nutrition.id}">${nutrition.paramName}</option>
											   </c:otherwise>
											   </c:choose> 
											</c:forEach>
									</select> 
									<select class="input-xlarge" style="vertical-align: top; width: 50px;" name="nutritionOperator">
											<c:if test="${nutritionCheck.nutritionOperator == '<'}">
												<option value="&lt;" selected="selected">&lt;</option>
												<option value="&gt;">&gt;</option>
												<option value="=">=</option>
											</c:if>
											<c:if test="${nutritionCheck.nutritionOperator == '>'}">
												<option value="&lt;">&lt;</option>
												<option value="&gt;" selected="selected">&gt;</option>
												<option value="=">=</option>
											</c:if>
											<c:if test="${nutritionCheck.nutritionOperator == '='}">
												<option value="&lt;">&lt;</option>
												<option value="&gt;">&gt;</option>
												<option value="=" selected="selected">=</option>
											</c:if>
									</select> 
									<input type="text" name="nutritionContent" id="nutritionContent" placeholder="含量" title="含量" style="width: 44px; margin-bottom:0" value="${nutritionCheck.nutritionContentStr}"/> 
									<!-- 	<a class="btn onAddNutrition" id="onAddNutrition"><i class="icon-plus"></i></a> -->
										<!--添加按钮-减 -->
										<c:if test="${nutritionCheckStatus.index != 0}">
											<a class="btn onMinusNutrition" id="onMinusNutrition"><i class="icon-minus"></i></a>
										</c:if>
										<!--添加按钮-加 -->
										<c:if test="${nutritionCheckStatus.index == 0}">
											<a class="btn onAddNutrition" id="onAddNutrition"><i class="icon-plus"></i></a>
										</c:if>
										</div>
									</c:forEach>
									</div><br>
								<div class="control-group" id="addNutrition"></div>
							</div>
							<div class="control-group">
								<label class="control-label">敏感食材 &nbsp;&nbsp;
								    <c:if test="${intelligentFixingsDto.isSensitiveIngredients ==0}"> 
									<input type="radio" value="1" id="isSensitiveMaterial" name="isSensitiveIngredients">
										<span style="width: 60px" class="lbl">加入</span> &nbsp;&nbsp; 
									<input type="radio" value="0" id="isNotSensitiveMaterial" name="isSensitiveIngredients" checked="checked">
										<span style="width: 60px" class="lbl">不加入</span>
									</c:if>
								    <c:if test="${intelligentFixingsDto.isSensitiveIngredients ==1}"> 
									<input type="radio" value="1" id="isSensitiveMaterial" name="isSensitiveIngredients" checked="checked>
										<span style="width: 60px" class="lbl">加入</span> &nbsp;&nbsp; 
									<input type="radio" value="0" id="isNotSensitiveMaterial" name="isSensitiveIngredients"">
										<span style="width: 60px" class="lbl">不加入</span>
									</c:if>
								</label>
							</div>
					</div>
					<fieldset>
						<div class="control-group">
							<label class="control-label">日期选择</label>
							<div class="controls">
								<input class="span10 date-picker" name="fixingsStartTimeStr"
									id="fixingsStartTimeStr" value="${intelligentFixingsDto.fixingsStartTimeStr}"
									type="text" data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="开始日期" /> - <input
									class="span10 date-picker" name="fixingsEndTimeStr"
									id="fixingsEndTimeStr" value="${intelligentFixingsDto.fixingsEndTimeStr}" type="text"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="结束日期" />
							</div>
						</div>
					</fieldset>

					<fieldset>
						<div class="control-group">
							<label class="control-label">所需份数</label>
							<!-- Select Basic -->
							<div class="controls">
							<c:if test="${intelligentFixingsDto.lunchCount != null}">
								<input type='checkbox' name='lunchCountSelect' id="lunchCountSelect"  checked="checked"/>
								中饭：<input type="text" name="lunchCount" id="lunchCount"  placeholder="数量" title="数量" style="width: 44px;" value="${intelligentFixingsDto.lunchCount}"/>份
							</c:if>
							<c:if test="${intelligentFixingsDto.lunchCount == null}">
								<input type='checkbox' name='lunchCountSelect' id="lunchCountSelect"/>
								中饭：<input type="text" name="lunchCount" id="lunchCount"  placeholder="数量" title="数量" style="width: 44px;" />份
							</c:if>
									</br>
							<c:if test="${intelligentFixingsDto.dinnerCount != null}">
								<input type='checkbox' name='dinnerCountSelect' id="dinnerCountSelect"   checked="checked"/>
								晚饭：<input type="text" name="dinnerCount" id="dinnerCount" placeholder="数量" title="数量" style="width: 44px;"  value="${intelligentFixingsDto.dinnerCount}"/>份
							</c:if>
							<c:if test="${intelligentFixingsDto.dinnerCount == null}">
								<input type='checkbox' name='dinnerCountSelect' id="dinnerCountSelect"/>
								晚饭：<input type="text" name="dinnerCount" id="dinnerCount" placeholder="数量" title="数量" style="width: 44px;" />份
							</c:if>
							
							</div>
						</div>
					</fieldset>

					<fieldset>
						<!-- <a class="btn btn-small btn-primary" onclick="save()">保存</a>
						&nbsp;&nbsp; <a class="btn btn-small btn-danger"
							onclick="top.Dialog.close();">取消</a> -->
						<hr>
						<div align="center">
					<!-- 	<a class="btn btn-big btn-primary" onclick="toBefore()" id="toBefore">上一步</a> -->
						<a class="btn btn-big btn-danger" onclick="save()">保存</a>
						<a class="btn btn-big btn-danger" onclick="top.Dialog.close();">关闭</a>
						<!-- <a class="btn btn-big btn-primary" onclick="toAfter()" style="display: none;" id="toAfter">下一步</a> -->
						</div>
					</fieldset>

					</form>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12"></div>
			</div>
			<div class="row-fluid">
				<div class="span12"></div>
			</div>
		</div>
	</div>
	</div>

	<!-- 引入 -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>

	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	<!-- 确认窗口 -->
	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--提示框-->

	<script type="text/javascript">
		//正则判断重复
		function mm(a)
		{
		   return /(\x0f[^\x0f]+)\x0f[\s\S]*\1/.test("\x0f"+a.join("\x0f\x0f") +"\x0f");
		}
		//Hash判断重复
		function isRepeat(arr) {
			   var hash = {};
			   for(var i in arr) {
			       if(hash[arr[i]])
			       {
			           return true;
			       }
			       // 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
			       hash[arr[i]] = true;
			    }
			   return false;
			} 
		
		//保存套餐
		<%-- function toAfter(){
			 var smartId = $("#smartId").val();
			 //console.log("smartId="+smartId);
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="保存套餐";
			 diag.URL = '<%=basePath%>smart/toAddSmartName.do?smartId='+smartId;
			 diag.Width = 350;
			 diag.Height = 255;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 //nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		} --%>
		
		$(function() {
			var name = request("menuName");
			//bootbox.alert(decodeURI(name));
			/* $("#fixingsName").val(decodeURI(name)); */
			//下拉框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

			//日期框
			$('.date-picker').datepicker();

			//增加类别
			$("#onAddType").click(function() {
				$("#categoryGroup").append($("#hiddType").html());
				
			});
			
			//减少类别
			$(".control-group").on('click','.onMinusType',function(){ 
				$(this).closest('.controls').remove();
			});

			//增加菜系
			$("#onAddStyle").click(function() {
				$("#styleGroup").append($("#hiddStyle").html());
				
			});
			
			//减少菜系
			$(".control-group").on('click','.onMinusStyle',function(){ 
				$(this).closest('.controls').remove();
			});

			//增加形状
			$("#onAddShape").click(function() {
				$("#shapeGroup").append($("#hiddShape").html());
				
			});
			
			//减少形状
			$(".control-group").on('click','.onMinusShape',function(){ 
				$(this).closest('.controls').remove();
			});

			//增加口味
			$("#onAddTaste").click(function() {
				$("#tasteGroup").append($("#hiddTaste").html());
				
			});
			
			//减少口味
			$(".control-group").on('click','.onMinusTaste',function(){ 
				$(this).closest('.controls').remove();
			});

			//增加烹饪
			$("#onAddCuisine").click(function() {
				$("#cuisineGroup").append($("#hiddCuisine").html());
				
			});
			
			//减少烹饪
			$(".control-group").on('click','.onMinusCuisine',function(){ 
				$(this).closest('.controls').remove();
			});

			//增加颜色
			$("#onAddColor").click(function() {
				$("#colorGroup").append($("#hiddColor").html());
				
			});
			
			//减少颜色
			$(".control-group").on('click','.onMinusColor',function(){ 
				$(this).closest('.controls').remove();
			});
			
			
			//增加营养
			$("#onAddNutrition").click(function() {
				$("#nutritionGroup").append($("#hiddNutrition").html());
			});
			
			//减少营养
			$(".control-group").on('click','.onMinusNutrition',function(){ 
				$(this).closest('.controls').remove();
			});

			$("#myclose").click(function() {
				top.Dialog.close();
			});
			
		    //删除原料数据框:需要清空:原料框,营养框,单份成本框,需求数量框,总成本框
		    $("#deleteMaterial").click(function(){
		    $("#materialNameList").val("");
		    $("#materialIdList").val("");
		    $("#nutrition").val("");
		    $("#singleCost").val("");
		    $("#totalCost").val("");
		    $("#demandNumber").val("");
		    //bootbox.alert("清空原料成功");
		    });
		  
	});
		//保存智能配菜
		function save(){
			var result=true;
			//类别重复校验
			var c = $("select[name='categoryId']");
			var cFlag = false;
			var cArry = c.slice(1, c.length);
			var cArry2 = cArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(cArry2.length-1);i++){
				if(cArry2.eq(i).val() == cArry2.eq(i+1).val()){
					cFlag = true;
					break;
				}
			
			}  
			if(cFlag == true){
				bootbox.alert("配菜种类有重复,请重新选择");
				return ;
			}
			  
			//种类数量不能为空
			var cCount = $("input[name='categoryCount']");
			var cCountFlag = false;
			var cCountArry = cCount.slice(1, cCount.length);
			var positiveInt = new RegExp("^[0-9]*[1-9][0-9]*$");   //正整数
			for(var i=0;i<cCountArry.length;i++){
				//console.log(cCountArry.eq(i).val());
				if(cCountArry.eq(i).val() == null || cCountArry.eq(i).val() == ""){
					cCountFlag = true;
					break;
				}
				if(!positiveInt.test(cCountArry.eq(i).val())){  
					cCountFlag = true;
					break;;  
				    }  
			
			}  
			if(cCountFlag == true){
				bootbox.alert("请输入类别份数,且份数为正整数");
				return ;
			}
			
			//菜系重复校验
			var style = $("select[name='productStyleId']");
			var styleFlag = false;
			var styleArry = style.slice(1, style.length);
			var styleArry2 = styleArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(styleArry2.length-1);i++){
				if(styleArry2.eq(i).val() == styleArry2.eq(i+1).val()){
					styleFlag = true;
					break;
				}
			
			}  
			if(styleFlag == true){
				bootbox.alert("菜系有重复,请重新选择");
				return ;
			}
			
			//形状重复校验
			var shape = $("select[name='productShapeId']");
			var shapeFlag = false;
			var shapeArry = shape.slice(1, shape.length);
			var shapeArry2 = shapeArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(shapeArry2.length-1);i++){
				if(shapeArry2.eq(i).val() == shapeArry2.eq(i+1).val()){
					shapeFlag = true;
					break;
				}
			
			}  
			if(shapeFlag == true){
				bootbox.alert("形状有重复,请重新选择");
				return ;
			}
			
			//口味重复校验
			var taste = $("select[name='productTaste Id']");
			var tasteFlag = false;
			var tasteArry = taste.slice(1, taste.length);
			var tasteArry2 = tasteArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(tasteArry2.length-1);i++){
				if(tasteArry2.eq(i).val() == tasteArry2.eq(i+1).val()){
					tasteFlag = true;
					break;
				}
			
			}  
			if(tasteFlag == true){
				bootbox.alert("口味有重复,请重新选择");
				return ;
			}
			
			//烹饪重复校验
			var cuisine = $("select[name='productCuisineId']");
			var cuisineFlag = false;
			var cuisineArry = cuisine.slice(1, cuisine.length);
			var cuisineArry2 = cuisineArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(cuisineArry2.length-1);i++){
				if(cuisineArry2.eq(i).val() == cuisineArry2.eq(i+1).val()){
					cuisineFlag = true;
					break;
				}
			
			}  
			if(cuisineFlag == true){
				bootbox.alert("烹饪有重复,请重新选择");
				return ;
			}
			
			//颜色重复校验
			var color = $("select[name='productColorId']");
			var colorFlag = false;
			var colorArry = color.slice(1, color.length);
			var colorArry2 = colorArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(colorArry2.length-1);i++){
				if(colorArry2.eq(i).val() == colorArry2.eq(i+1).val()){
					colorFlag = true;
					break;
				}
			
			}  
			if(colorFlag == true){
				bootbox.alert("颜色有重复,请重新选择");
				return ;
			}
			
			//配菜总成本不能为空
		   /*  if($("#totalCost").val()==""){
				$("#totalCost").tips({
					side:3,
		            msg:'请输入配菜总成本',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#totalCost").focus();
				return false;
			  } */
		 	//var positive = new RegExp("^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$");  //正数
		 	var positive = new RegExp("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"); //正数
			if($.trim($("#totalCost").val())==""){
				bootbox.alert("配菜总成本为空");
				return ;
			  } 
			if(!positiveInt.test($.trim($("#totalCost").val()))){  
				bootbox.alert("配菜总成本应为正整数");
				return ;  
			}   
			
		  //配菜总定价不能为空
		    if($.trim($("#totalFixedPrice").val())==""){
		    	bootbox.alert("配菜总定价为空");
				return ;
			  }
		    if(!positiveInt.test($.trim($("#totalFixedPrice").val()))){  
				bootbox.alert("配菜总定价应为正整数");
				return ;  
			}
			  
			//营养重复校验
			var n = $("select[name='nutritionId']");
			var nFlag = false;
			var nArry = n.slice(1, n.length);
			var nArry2 = nArry.sort(function(l,r){return $(l).val()<$(r).val()?-1:1});
			for(var i=0;i<(nArry2.length-1);i++){
				if(nArry2.eq(i).val() == nArry2.eq(i+1).val()){
					nFlag = true;
					break;
				}
			
			}  
			if(nFlag == true){
				bootbox.alert("营养有重复,请重新选择");
				return ;
			}
			
			//营养含量不能为空
			var cContent = $("input[name='nutritionContent']");
			var cContentFlag = false;
			var cContentArry = cContent.slice(1, cContent.length);  
			for(var i=0;i<cContentArry.length;i++){
				if(cContentArry.eq(i).val() == null || cContentArry.eq(i).val() == ""){
					cContentFlag = true;
					break;
				}
				if(!positiveInt.test(cContentArry.eq(i).val())){  
					cContentFlag = true;
					break;;  
				}   
			
			}  
			if(cContentFlag == true){
				bootbox.alert("请输入营养含量,且营养为正整数");
				return ;
			}
			  
			//日期选择起始日期不能大于结束日期
			var startTime = $.trim($("#fixingsStartTimeStr").val());
			var endTime = $.trim($("#fixingsEndTimeStr").val());
			if(startTime == ""){
				bootbox.alert("请选择起始日期");
				return false;
			}
			if(endTime == ""){
				bootbox.alert("请选择结束日期");
				return false;
			}
			
			var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
		    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
		    if(end<start){  
		    	bootbox.alert("起始日期不能大于结束日期");
		    	return false;
		    }  
		    
		 	//午餐晚餐checkbox处理
		    var checkFlag = false;
		  	if ($('#lunchCountSelect').attr('checked')) {
		  		checkFlag = true;
		  		var lunchCount = $.trim($("#lunchCount").val());
		  		if(lunchCount == ""){
					bootbox.alert("请输入午饭数量");
					return false;
				}
				if(!positiveInt.test(lunchCount)){  
					bootbox.alert("午饭数量应为正整数");
					return false; 
				}  
			}else{
				$('#lunchCount').val("");
			}
		  	
		  	if ($('#dinnerCountSelect').attr('checked')) {
		  		checkFlag = true;
		  		var dinnerCount = $.trim($("#dinnerCount").val());
		  		if(dinnerCount == ""){
					bootbox.alert("请输入晚饭数量");
					return false;
				}
				if(!positiveInt.test(dinnerCount)){  
					bootbox.alert("晚饭数量应为正整数");
					return false; 
				}  
			}else{
				$('#dinnerCount').val("");
			}
			if(checkFlag == false){
				bootbox.alert("午餐和晚餐不能同时为空");
		    	return false;
			}
			
		    //ajax提交form表单
		    $.ajax({
	            cache: true,
	            type: "POST",
	            url:'<%=basePath%>smart/editSmart.do',
	            data:$('#smartForm').serialize(),// 你的formid
	            async: false,
	            error: function(request) {
	                bootbox.alert("Connection error");
	            },
	            success: function(data) {
	            //console.log(data);
		        if (data.status == 200) {
						//bootbox.alert(data.msg);
					$("#toAfter").show();
					$("#smartId").val(data.data);
		        	bootbox.alert("修改智能配菜成功");
				  
					} else {
						result=false;
						//bootbox.alert(data.msg);
						bootbox.alert("修改智能配菜失败");
					}
				if(result==true){
				   //只有返回成功，才会关闭当前dialog;
				 //top.Dialog.close();
				}
	           }
			  });
		}
		
	    function request(name)
		{
	     	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     	var r = window.location.search.substr(1).match(reg);
	     	if(r!=null)return  unescape(r[2]); 
	     	return null;
		}
	</script>
</body>
</html>