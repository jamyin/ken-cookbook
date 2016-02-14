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
	<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.dataTables.bootstrap.js"></script>
		
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<script type="text/javascript" >
		
		</script>
		
		
</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


	<div class="row-fluid">
	
			<!-- 检索  -->

			<form action="productController/materialAddPage.do" method="post" name="materialForm" id="materialForm">
		<%-- 	<table>
				<tr>
					<td>
						<span class="input-icon">
						<input name="pageInfo" value="${pageInfo}" style="display: none;"/>
							<input autocomplete="off" id="nav-search-input" type="text" name="materianName" value="${materialDto.name}"  placeholder="输入原料名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
			
				<button  class="btn btn-mini btn-primary" onclick="search();" style="margin-top: -10px;" title="检索">搜索</button>
					</td>
			   </tr>
			  
			</table> --%>

			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
					    <th>序号</th>
						<th>原料名称</th>
					    <th>成本/元</th>
						
						<th>备注</th>
						<th>选择</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty listMaterial}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${listMaterial}" var="material" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${material.name }</td>
							<!--	<c:forEach items="${listMType}" var="mtype">
								  <c:if test="${mtype.paramId == material.type}"><td>${mtype.paramName}</td></c:if>
								</c:forEach>   -->
							    <td>${material.cost/100}</td>
					            <td>${material.remark}</td>
								<td style="width: 60px;">
									<button class="btn btn-mini btn-primary" data-toggle="modal" data-target="#myModal" onclick="add_material('${material.id}','${material.name}');">选择</button>
								<%-- <a class='btn btn-mini btn-info' title="选择" onclick="add_material('${material.id}','${material.name}');"><i class='icon-edit'></i>选择</a> --%>
							    </td>
							</tr>
						
						</c:forEach>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="10" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="10" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
		</form>
		
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->

		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#materialForm").submit();
		}
		
     
		
		</script>
		
	<script type="text/javascript">
		$(top.hangge());
		$(function() {
		
		})
		
		//检索
		function search(){
			top.jzts();
			$("#materialForm").submit();
		}


		//添加成品菜-原料
		function add_material(materialId,msg){
			var pageInfo=${pageInfo};
			var dialogFrame;
			var dialog;
			var idList;
		    if(pageInfo==1){
		        //编辑
		        dialog = top.Dialog.getDialogById("product_edit");
				//dialogFrame = window.parent.document.getElementById("_DialogFrame_0");
			}else{
			    //添加
				dialog = top.Dialog.getDialogById("product_add");
				//dialogFrame = window.parent.document.getElementById("_DialogFrame_1");
			}
		  
			idList=dialog.getElementById("materialIdList").value;
			var singleCost=dialog.getElementById("singleCost").value;
			var materialWeightList = dialog.getElementById("materialWeightList").value;
	   
			if(idList==null||idList==""){
				idList="";
			 }
			   var msgs='确定要选择["+msg+"]吗';
			    $("#myModalLabel_a").html(msgs);
				$("#material_Add_model_body").html(msg);
					bootbox.confirm("确定要选择["+msg+"]吗?</br>克重:<input type='text' name='materialWeight' id='materialWeight' placeholder='输入克重(不输入为默认克重)'/>/克", function(result) {
						if(result) {
							var materialWeight=$("#materialWeight").val();
						    if(materialWeight!="")
						      {
						     
						         var digit = /^\+?[1-9]\d*$/;
						         if (!digit.test(materialWeight)) 
						         {
						            alert("克重只能输入正整数数字");
						            $("#materialWeight").focus();
						            return false;
						         }
						         if(materialWeight.length>7){
						        	  alert("克重长度最多为7位");
							            $("#materialWeight").focus();
							            return false;
						         }
						       }
						    var url = '<%=basePath%>productController/materialAdd.do?id=${productId}&materialId='+materialId+'&materialIdList='+idList+'&materialWeight='+materialWeight+'&singleCost='+singleCost+'&materialWeightList='+materialWeightList;
							$(function(){
								jQuery.ajax({
									url : url,
									type : 'POST',
									dataType : 'json',
									async : false,
									success : function(data) {
									if(true==data.success){
							            var list=data.obj.materialNameList;
							            var idList=data.obj.materialIdList;;
							            var materialWeightList=data.obj.materialWeightList;
							            var nutritionList=data.obj.nutritionNameList;
							            var dataAll='';
										var nutritionAll='';
										var idAll='';
									
										//循环原料id
									    $.each(idList,function(i,data){
									    	if(i>0){
									    		idAll+=",";
									    	}
									    	idAll+=data;
									   });  
							
									    //获取一级弹出框的原料元素;
									 dialog.getElementById("materialNameList").value=list;
									 dialog.getElementById("materialIdList").value=idList;
					
									 //成品菜原料关系
									 dialog.getElementById("materialWeightList").value=materialWeightList;
					
									 //获取一级弹出框的营养元素
									 dialog.getElementById("nutrition").value=nutritionList;
									   //获取一级弹出框的单份成本元素
								     dialog.getElementById("singleCost").value = data.obj.singleCost;
							        if(dialog.getElementById("singleCost").value!=null && dialog.getElementById("demandNumber").value!="")
							        {
							             //发送ajax计算总价
										    //ajax提交
										    $.ajax({
									            cache: true,
									            type: "POST",
									            url:'<%=basePath%>productController/totalCost.do',
									            data: {singleCost: dialog.getElementById("singleCost").value,
									            	   demandNumber:dialog.getElementById("demandNumber").value
									            	},
									            async: false,
									            error: function(request) {
									                alert("Connection error");
									            },
									            success: function(data) {
										     
									            if (data.success == true) {

									            	dialog.getElementById("totalCost").value=data.obj;
												} else {

												    bootbox.alert(data.msg);
													}
												}
											});
									  }
									   //关闭当前窗口
									   top.Dialog.close();
									  
								 }else{
									 bootbox.alert(data.msg);
									
								 }
									
									} 
								});
							});
						}
					});
				}
			
		 function CkeckData(obj)
	      {
	       var inputObj = obj;
	       var temp = inputObj.value;
	       if(temp!="")
	      {
	         var digit = /^-?\d+(\.\d+)?$/;
	         if (!digit.test(temp)) 
	         {
	            alert("只能输入数字");
	            inputObj.focus();
	         }
	       }}

		
		</script>
			<!-- 模态框（Modal） -->
  <div class="modal fade" id="material_Add_Modal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel_a">

            </h4>
         </div>
         <div class="modal-body" id="material_Add_model_body">
       
         </div>
   <!--       <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
      
         </div> -->
      </div>
   </div>
</div>
	</body>
</html>

