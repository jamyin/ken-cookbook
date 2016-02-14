<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
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
	<div id="zhongxin">
			<input type="hidden" name="productId" id="productId" value="${smartResultDto.productId}"/>
			<input type="hidden" name="smartId" id="smartId" value="${smartResultDto.smartId}"/>
			<input type="hidden" name="lunchCount" id="lunchCount" value="${smartResultDto.lunchCount}"/>
			<input type="hidden" name="dinnerCount" id="dinnerCount" value="${smartResultDto.dinnerCount}"/>
		<div>
			套餐名称：<input type="text" name="setMealName" id="setMealName"
				 placeholder="套餐名称" title="套餐名称"
				class="isnull" message="套餐名称不能为空" />
		</div>
		<div align="center">
			<a class="btn btn-big btn-danger" onclick="save()">保存套餐</a>
		</div>
		
	</div>


	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->

	<script type="text/javascript">
		 /* function request(name){
		     	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     	var r = window.location.search.substr(1).match(reg);
		     	if(r!=null)return  unescape(r[2]); 
		     	return null;
			} */
			//新增
			
			function save(){
				//校验..
				//var smartId = request("smartId");
				var smartId = $("#smartId").val();
				var productId = $("#productId").val();
				var lunchCount = $("#lunchCount").val();
				var dinnerCount = $("#dinnerCount").val();
				//alert("smartId = "+smartId + "productId = "+productId);
				//console.log("smartId = "+smartId);
				var setMealName = $.trim($("#setMealName").val());
				 $.ajax({
			            cache: true,
			            type: "POST",
			            url:'<%=basePath%>smart/addSmartName.do',
			            data:{'setMealName':setMealName,'id':smartId},
			            error: function(request) {
			                bootbox.alert("Connection error");
			            },
			            success: function(data) {
			            //console.log(data);
				        if (data.status == 200) {
				        	//bootbox.alert("添加套餐成功");
				        	var id = data.data;
							 var choose_diag = new top.Dialog();
							 choose_diag.Drag=true;
							 choose_diag.ID="product_display";
							 choose_diag.Title ="成品菜";
							 choose_diag.URL = '<%=basePath%>smart/menuResultListPage.do?pid=' + productId +'&lunchCount='+lunchCount +'&dinnerCount='+dinnerCount;
							 choose_diag.Width = 535;
							 choose_diag.Height = 585;
							 choose_diag.CancelEvent = function(){ //关闭事件
					            //先关闭二级弹出框，再关闭一级弹出框;
							 		choose_diag.close();
							    	top.Dialog.close();
								 };
								choose_diag.show();
							} else {
								//bootbox.alert(data.msg);
								bootbox.alert("添加套餐失败");
							}
			           }
					  });
			}
		</script>

</body>
</html>