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

<script type="text/javascript">

	$(top.hangge());
</script>
</head>
<body>
	<div id="zhongxin">
		<div>
			<font size="4" face="Verdana">名称：</font><input type="text" name="menuName" id="menuName"
				value="${pd.menuName}" placeholder="这里输入配菜名称" title="配菜名称"
				 class="isnull" message="配菜名称不能为空" />
		</div>
		<div>
			<h3 style="text-align: center;color: pink">配菜方式</h3>
		</div>
		<div style="text-align: center;">
			<button class="btn btn-large" type="button" onclick="add(1)">自主配菜</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-large" type="button" onclick="add(2)">智能配菜</button>
			<!-- <button class="btn btn-large" type="button" onclick="add(3)">指定套餐</button> -->
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
			//新增
			function add(type){
				 var menuName=$.trim($("#menuName").val());
				 if(menuName == ""|| menuName == null){
					$("#menuName").tips({
						side:3,
			            msg:'配菜名称为空',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#menuName").focus();
					return false;
				 }
				 top.jzts();
				 var diag = new top.Dialog();
				 diag.Drag=true;
				 if(type==1){
					 diag.ID="menuCustom";
					 diag.Title ="自主配菜";
				 }else if(type==2){
					 diag.ID="menuSmart";
					 diag.Title ="智能配菜";
				 }else if(type==3){
					 diag.Title ="指定套餐";
				 }
				
				 //var urlAddress="smart/menuStyleShow.do?menuName="+menuName+"&type="type;
				 var name = encodeURI(encodeURI(menuName));
				 diag.URL = '<%=basePath%>smart/menuStyleShow.do?menuName='+name+'&type='+type;
				 diag.Width = 600;
				 diag.Height = 755;
				 diag.CancelEvent = function(){ //关闭事件
					//刷新当前面板
					//search();
					diag.close();
				 };
				 diag.show();
				 window.parent.document.getElementById('_ButtonClose_0').click();
			}
		</script>

</body>
</html>