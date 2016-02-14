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
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />

<style>
	.keyWrap {
		background: #E3E3E3; 
	}
	.keyWrap.sel{
		color: #CD8162;
		border:1px solid #CD8162;
	}
</style>

<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
		$("#nav-search-icon").click(function(e) {
			initMaterialDialog();
		});
		//总成本价计算
		$("#demandNumber").blur(function(){
			
			//单份成本校验
		    if($("#singleCost").val()==""){
				$("#materialNameList").tips({
					side:3,
		            msg:'请先选择原料',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#materialNameList").focus();
			    return false;
			  }
			//需求数量校验
		    if($("#demandNumber").val()==""){
				$("#demandNumber").tips({
					side:3,
		            msg:'请输入需求数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#demandNumber").focus();
			    return false;
			  }
		var singleCost=$("#singleCost").val();
		var demandNumber=$("#demandNumber").val();
	    //总成本计算
		totalCost(singleCost,demandNumber);
		});
		
		function totalCost(singleCost,demandNumber){
		    //ajax提交
		    $.ajax({
	            cache: true,
	            type: "POST",
	            url:'<%=basePath%>productController/totalCost.do',
	            data: {singleCost:singleCost,demandNumber:demandNumber},
	            async: false,
	            error: function(request) {
	                alert("Connection error");
	            },
	            success: function(data) {
		     
	            if (data.success == true) {

			        $("#totalCost").val(data.obj);
				} else {

				    bootbox.alert(data.msg);
					}
				}
			});
		}
	});

	function initMaterialDialog() {
		var dialog = new top.Dialog();
		dialog.id = "material_p";
		dialog.Drag = true;
		dialog.Title = "原料面板";
		dialog.URL = '<%=basePath%>productController/materialAddPage.do?id=${productDto.id}&pageInfo=add';
		dialog.Width = 550;
		dialog.Height = 280;
		dialog.CancelEvent = function(){ //关闭事件
		
			 dialog.close();
		 };
		 dialog.show();
		
	}
	
	
	//保存
	function save(){
		var result=true;
		//名称校验
	    if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'请填写成品菜名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#name").focus();
			return false;
		  }
	  //类别校验
	    if($("#productCategoryId").val()==""){
			$("#productCategoryId").tips({
				side:3,
	            msg:'请选择类别',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productCategoryId").focus();
			return false;
		  }
	  //菜系校验
	    if($("#productStyleId").val()==""){
			$("#productStyleId").tips({
				side:3,
	            msg:'请选择菜系',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productStyleId").focus();
			return false;
		  }
	  //口味校验
	    if($("#productTasteId").val()==""){
			$("#productTasteId").tips({
				side:3,
	            msg:'请选择口味',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productTasteId").focus();
			return false;
		  }
	  //形状校验
	    if($("#productShapeId").val()==""){
			$("#productShapeId").tips({
				side:3,
	            msg:'请选择形状',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productShapeId").focus();
			return false;
		  }
	  //颜色校验
	    if($("#productColorId").val()==""){
			$("#productColorId").tips({
				side:3,
	            msg:'请选择颜色',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productColorId").focus();
			return false;
		  }
	  //名称校验
	    if($("#productCuisineId").val()==""){
			$("#productCuisineId").tips({
				side:3,
	            msg:'请选择烹饪',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#productCuisineId").focus();
			return false;
		  }
	  //原料校验
	    if($("#materialNameList").val()==""){
			$("#materialNameList").tips({
				side:3,
	            msg:'请选择原料',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#materialNameList").focus();
			return false;
		  }
	  //每份定价校验
	    if($("#eachPricing").val()==""){
			$("#eachPricing").tips({
				side:3,
	            msg:'请选择每份定价',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#eachPricing").focus();
			return false;
		  }else {
			  var digit =/^\+?[1-9]\d*$/;
		        if (!digit.test($("#eachPricing").val())) 
		        { 
		          $("#eachPricing").tips({
						side:3,
			            msg:'定价只能输入正整数',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#eachPricing").focus();
		           return false;
		        }
		        if($("#eachPricing").val().length>7){
		        	
		        		$("#eachPricing").tips({
		    				side:3,
		    	            msg:'定价最多为7位',
		    	            bg:'#AE81FF',
		    	            time:2
		    	        });
		        	  inputObj.focus();
			            return false;
		         }
		       
		  }
	   
	    //单份克重校验
	    if($("#singleWeight").val()==""){
			$("#singleWeight").tips({
				side:3,
	            msg:'请选择单份克重',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#singleWeight").focus();
			return false;
		  }else {
			  var digit =/^\+?[1-9]\d*$/;
		        if (!digit.test($("#singleWeight").val())) 
		        { 
		          $("#singleWeight").tips({
						side:3,
			            msg:'单份克重只能输入正整数',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#singleWeight").focus();
		           return false;
		        }
		        if($("#singleWeight").val().length>7){
		        	 
		        		$("#singleWeight").tips({
		    				side:3,
		    	            msg:'单份克重最多为7位',
		    	            bg:'#AE81FF',
		    	            time:2
		    	        });
		        	  inputObj.focus();
			            return false;
		         }
		   }
	     //需求数量校验
	    if($("#demandNumber").val()==""){
			$("#demandNumber").tips({
				side:3,
	            msg:'请输入需求数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#demandNumber").focus();
			return false;
		  }else {
			  var digit =/^\+?[1-9]\d*$/;
		        if (!digit.test($("#demandNumber").val())) 
		        { 
		        	$("#demandNumber").tips({
						side:3,
			            msg:'需求数只能输入正整数',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#demandNumber").focus();
		           return false;
		        }
		        if($("#demandNumber").val().length>7){
		        	 
		        		$("#demandNumber").tips({
		    				side:3,
		    	            msg:'需求数长度最多为7位',
		    	            bg:'#AE81FF',
		    	            time:2
		    	        });
		        	  inputObj.focus();
			            return false;
		         }
		   }
	    
	
	    //ajax提交form表单
	    $.ajax({
            cache: true,
            type: "POST",
            url:'<%=basePath%>productController/add.do',
            data:$('#productForm').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
	        if (data.success == true) {
	        		alert(data.msg)
					//bootbox.alert(data.msg);
			  
				} else {
					result=false;
					bootbox.alert(data.msg);
				 
				}
	        /* $("#zhongxin").hide();
			$("#zhongxin2").show();*/
			if(result==true){
			   //只有返回成功，才会关闭当前dialog;
			 top.Dialog.close();
			}
           }
		  });
		
	    
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}

	
	//单选框
	function setType(type){
		alert(type);
	}
	
</script>
	</head>
<body>
	<form  name="productForm" id="productForm" method="post">

		<input type="hidden" name="id" id="productId" value="${productDto.id}"/>
		
		<div id="zhongxin">
		<table>
		    <tr>
		        <th style="text-align:left;padding-left: 20px;padding-top: 15px;">名称：</th>
				<td style="padding-top: 15px;"><input type="text" name="name" id="name"  maxlength="32" placeholder="输入成品菜名称" title="名称" style="width: 136px;"/></td>
				<th></th>
				<td></td>
				
			</tr>
			<tr >
			    <th style="text-align:left;padding-left: 20px;">类别：</th>
				<td>
				<select class="chzn-select" name="productCategoryId" id="productCategoryId" data-placeholder="请选择类别" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listCategory}" var="category">
					<option value="${category.id}" >${category.name}</option>
				</c:forEach>
				</select>
		        <!--添加类别按钮 -->
				<i class="icon-plus" id="addCategory"></i>
				</td>
				    <th style="text-align:left;">菜系：</th>
				<td>
				<select class="chzn-select" name="productStyleId" id="productStyleId" data-placeholder="请选择菜系" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listStyle}" var="style">
					<option value="${style.id}" >${style.name}</option>
				</c:forEach>
				</select>
				<!--添加菜系按钮 -->
				<i class="icon-plus" id="addStyle"></i>
				</td>
			</tr>
		   <tr >
			    <th style="text-align:left;padding-left: 20px;">口味：</th>
				<td  style="padding-top: 7px;">
				<select class="chzn-select" name="productTasteId" id="productTasteId" data-placeholder="请选择口味" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listTaste}" var="taste">
					<option value="${taste.id}" >${taste.name}</option>
				</c:forEach>
				</select>
				<!--添加口味按钮 -->
				<i class="icon-plus" id="addTaste"></i>
				</td>
				    <th style="text-align:left;">形状：</th>
				<td style="padding-top: 7px;">
				<select class="chzn-select" name="productShapeId" id="productShapeId" data-placeholder="请选择形状" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listShape}" var="shape">
					<option value="${shape.id}" >${shape.name}</option>
				</c:forEach>
				</select>
				<!--添加形状按钮 -->
				<i class="icon-plus" id="addShape"></i>
				</td>
			</tr>
				<tr>
			    <th style="text-align:left;padding-left: 20px;">颜色：</th>
				<td  style="padding-top: 7px;">
				<select class="chzn-select" name="productColorId" id="productColorId" data-placeholder="请选择颜色" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listColor}" var="color">
					<option value="${color.id}" >${color.name}</option>
				</c:forEach>
				</select>
				<!--添加颜色按钮 -->
				<i class="icon-plus" id="addColor"></i>
				</td>
			    <th style="text-align:left;">烹饪：</th>
				<td style="padding-top: 7px;">
				<select class="chzn-select" name="productCuisineId" id="productCuisineId" data-placeholder="请选择烹饪" style="vertical-align:top;width: 150px;">
				<option value=""></option>
				<c:forEach items="${listCuisine}" var="cuisine">
					<option value="${cuisine.id}" >${cuisine.name}</option>
				</c:forEach>
				</select>
					<!--添加烹饪按钮 -->
				<i class="icon-plus" id="addCusine"></i>
				</td>
			</tr>
			   <tr>
		        <th style="text-align:left;padding-left: 20px;">原料：</th>
				<td  style="padding-top: 10px;">
				<span>
				<input type="text" name="materialIdList"   id="materialIdList"    maxlength="32" style="display: none;">
				<input type="text" name="materialNameList" id="materialNameList"  maxlength="32" style="width: 136px;" placeholder=""  value="" title="原料" readonly="readonly"/>
				</span>
				<!--添加原料按钮 -->
				<i  id="nav-search-icon" class="icon-plus" title="添加原料" ></i>
				<!--删除原料按钮 -->
				<i class="icon-remove" id="deleteMaterial"></i>
			</td>
			</tr>
		    <tr>
		        <th style="text-align:left;padding-left: 20px;">营养：</th>
				<td ><input type="text" name="nutrition" id="nutrition"  maxlength="32" style="width: 186px;"  title="营养" readonly="readonly"/></td>
			</tr>
		    <tr>
		        <th style="text-align:left;padding-left: 20px;">单份成本：</th>
				<td >
				<input type="hidden" name="materialWeightList" id="materialWeightList"/>
				<input type="text" name="singleCost" id="singleCost"  maxlength="32" style="width: 136px;" placeholder="" title="单份成本" readonly="readonly"/><font style="font-weight: bolder;">/元</font></td>
			</tr>
		   <tr>
		        <th style="text-align:left;padding-left: 20px;">每份定价：</th>
				<td ><input type="text" name="eachPricing" id="eachPricing"  maxlength="32" style="width: 136px;" placeholder="输入每份定价" title="每份定价"/><font style="font-weight: bolder;">/元</font></td>
			</tr>
		   <tr>
		        <th style="text-align:left;padding-left: 20px;">单份克重：</th>
				<td><input type="text" name="singleWeight" id="singleWeight"  maxlength="32" style="width: 136px;" placeholder="输入单份克重" title="单份克重"/><font style="font-weight: bolder;">/克</font></td>
			</tr>
			   <tr>
		        <th style="text-align:left;padding-left: 20px;">需求数：</th>
				<td ><input type="text" name="demandNumber" id="demandNumber"  maxlength="32" style="width: 136px;" placeholder="输入需求数" title="需求数"/><font style="font-weight: bolder;">/份</font></td>
			</tr>
			   <tr>
		        <th style="text-align:left;padding-left: 20px;">总成本：</th>
				<td ><input type="text" name="totalCost" id="totalCost"  maxlength="32" style="width: 136px;" title="总成本" readonly="readonly"/><font style="font-weight: bolder;">/元</font></td>
			</tr>
			<tr>
		        <th style="text-align:left;padding-left: 20px;">备注：</th>
				<td><input type="text" name="remark" id="remark"  maxlength="32" style="width: 190px;height: 48px;"  title="备注"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript">
		
		$(function() {
			
			 $('#remark').keydown(function(e){
				  //备注后点table事件会继续走，跳到首页面bug修复
				 if(e.keyCode==9){
					e.preventDefault();
					 }
				 });
			
			//单选框
			$chosen = $(".chzn-select");
			$chosen.chosen(); 
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			 //添加成品菜-类别
		    $("#addCategory").click(function(){
		    	addCategory();
		    });
			 
		    //添加成品菜-菜系
		    $("#addStyle").click(function(){
		    	addStyle();
		    });
		 
		    //添加成品菜-口味
		    $("#addTaste").click(function(){
		    	addTaste();
		    });
		    
		    //添加成品菜-形状
		    $("#addShape").click(function(){
		    	addShape();
		    });
		    
		    //添加成品菜-颜色
		    $("#addColor").click(function(){
		    	addColor();
		    });
		    
		    //添加成品菜-烹饪
		    $("#addCusine").click(function(){
		    	addCusine();
		    });
		    
		    //删除原料数据框:需要清空:原料框,营养框,单份成本框,需求数量框,总成本框
		    $("#deleteMaterial").click(function(){
		    $("#materialNameList").val("");
		    $("#materialIdList").val("");
		    $("#nutrition").val("");
		    $("#singleCost").val("");
		    $("#totalCost").val("");
		    $("#demandNumber").val("");
		    $("#materialWeightList").val("");
		    //bootbox.alert("清空原料成功");
		    });
		  
		    //新增烹饪
			function addCusine(){	
				 top.jzts();
				 var colorDialog = new top.Dialog();
				 colorDialog.Drag=true;
				 colorDialog.Title ="新增烹饪";
				 colorDialog.URL = '<%=basePath%>cuisineController/list.do';
				 colorDialog.Width = 770;
				 colorDialog.Height = 350;
				 colorDialog.CancelEvent = function(){ //关闭事件
					 //刷新烹饪
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>cuisineController/reloadCuisine.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							//获取添加成品菜框的烹饪元素
							 top.Dialog.getDialogById("product_add").getElementById("productCuisineId").innerHTML=a;
							 $chosen.trigger("liszt:updated");
					      }
				     });
				     colorDialog.close();
				 };
				 colorDialog.show();
			}
		    
		    //新增颜色
			function addColor(){	
				 top.jzts();
				 var colorDialog = new top.Dialog();
				 colorDialog.Drag=true;
				 colorDialog.Title ="新增颜色";
				 colorDialog.URL = '<%=basePath%>colorController/list.do';
				 colorDialog.Width = 770;
				 colorDialog.Height = 350;
				 colorDialog.CancelEvent = function(){ //关闭事件
					 //刷新颜色
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>colorController/reloadColor.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							//获取添加成品菜框的颜色元素
							 top.Dialog.getDialogById("product_add").getElementById("productColorId").innerHTML=a;
						     $chosen.trigger("liszt:updated");
					      }
				     });
				     colorDialog.close();
				 };
				 colorDialog.show();
			}
		    
		    //新增形状
			function addShape(){	
				 top.jzts();
				 var styleDialog = new top.Dialog();
				 styleDialog.Drag=true;
				 styleDialog.Title ="新增形状";
				 styleDialog.URL = '<%=basePath%>shapeController/list.do';
				 styleDialog.Width = 770;
				 styleDialog.Height = 350;
				 styleDialog.CancelEvent = function(){ //关闭事件
					 //刷新形状
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>shapeController/reloadShape.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							//获取添加成品菜框的形状元素
							 top.Dialog.getDialogById("product_add").getElementById("productShapeId").innerHTML=a;
							 $chosen.trigger("liszt:updated");
					      }
				     });
				     styleDialog.close();
				 };
				 styleDialog.show();
			}
		    
		    //新增口味
			function addTaste(){	
				 top.jzts();
				 var styleDialog = new top.Dialog();
				 styleDialog.Drag=true;
				 styleDialog.Title ="新增口味";
				 styleDialog.URL = '<%=basePath%>tasteController/list.do';
				 styleDialog.Width = 770;
				 styleDialog.Height = 350;
				 styleDialog.CancelEvent = function(){ //关闭事件
					 //刷新口味
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>tasteController/reloadTaste.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							//获取添加成品菜框的口味元素
							 top.Dialog.getDialogById("product_add").getElementById("productTasteId").innerHTML=a;
						     $chosen.trigger("liszt:updated");
					      }
				     });
				     styleDialog.close();
				 };
				 styleDialog.show();
			}
		    
		    
		    //新增菜系
			function addStyle(){	
				 top.jzts();
				 var styleDialog = new top.Dialog();
				 styleDialog.Drag=true;
				 styleDialog.Title ="新增菜系";
				 styleDialog.URL = '<%=basePath%>styleController/list.do';
				 styleDialog.Width = 770;
				 styleDialog.Height = 350;
				 styleDialog.CancelEvent = function(){ //关闭事件
					 //刷新菜系
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>styleController/reloadStyle.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							 //获取添加成品菜框的菜系元素
							 top.Dialog.getDialogById("product_add").getElementById("productStyleId").innerHTML=a;
						     $chosen.trigger("liszt:updated");
					      }
				     });
				     styleDialog.close();
				 };
				 styleDialog.show();
			}
		    
		  //新增类别
			function addCategory(){	
				 top.jzts();
				 var categoryDialog = new top.Dialog();
				 categoryDialog.Drag=true;
				 categoryDialog.Title ="新增类别";
				 categoryDialog.URL = '<%=basePath%>categoryController/list.do';
				 categoryDialog.Width = 770;
				 categoryDialog.Height = 350;
				 categoryDialog.CancelEvent = function(){ //关闭事件
					 //刷新大类
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>categoryController/reloadCategory.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							//获取添加成品菜框的类别元素
							 top.Dialog.getDialogById("product_add").getElementById("productCategoryId").innerHTML=a;
							 //window.parent.document.getElementById("_DialogFrame_1").contentDocument.getElementById("productCategoryId").innerHTML=a;
						     $chosen.trigger("liszt:updated");
					      }
				     });
				     categoryDialog.close();
				 };
				 categoryDialog.show();
			}
		});
		</script>
	
</body>
</html>