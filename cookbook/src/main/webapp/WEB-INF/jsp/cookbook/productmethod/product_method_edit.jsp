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
<script type="text/javascript">
	$(top.hangge());
	 var product_id='';
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
		$('#nameArea').click(function(e) {
			var event = e || window.event;
			var target = event.target || event.srcElement;
			console.log($(target).data("id"));

			$(target).toggleClass("sel");
			if($(target).hasClass("sel")||$(target).siblings().hasClass("sel")){
				product_id=$(target).data("id");
			}else{
				product_id='';
			}
			$(target).siblings().removeClass("sel");
		});
	});




	function ismail(mail) {
		return (new RegExp(
				/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/)
				.test(mail));
	}

	//判断用户名是否存在
	function hasU() {
		var USERNAME = $.trim($("#loginname").val());
		$.ajax({
			type : "POST",
			url : '<%=basePath%>user/hasU.do',
	    	data: {USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#loginname").css("background-color","#D16E6C");
					setTimeout("$('#loginname').val('此用户名已存在!')",500);
				 }
			}
		});
	}

	//判断邮箱是否存在
	function hasE(USERNAME){
		var EMAIL = $.trim($("#EMAIL").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasE.do',
	    	data: {EMAIL:EMAIL,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#EMAIL").tips({
							side:3,
				            msg:'邮箱已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#EMAIL').val('')",2000);
				 }
			}
		});
	}

	//判断编码是否存在
	function hasN(USERNAME){
		var NUMBER = $.trim($("#NUMBER").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasN.do',
			data : {
				NUMBER : NUMBER,
				USERNAME : USERNAME,
				tm : new Date().getTime()
			},
			dataType : 'json',
			cache : false,
			success : function(data) {
				if ("success" != data.result) {
					$("#NUMBER").tips({
						side : 3,
						msg : '编号已存在',
						bg : '#AE81FF',
						time : 3
					});
					setTimeout("$('#NUMBER').val('')", 2000);
				}
			}
		});
	}

	//保存
		function save(){
			var result=true;
			//名称校验
		    if($("#productionName").val()==""){
				$("#productionName").tips({
					side:3,
		            msg:'请填写菜谱名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productionName").focus();
				return false;
			  }
		  //图片校验
		    if($("#imgUrlSrc").val()==""){
				$("#imgUrl").tips({
					side:3,
		            msg:'请上传图片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#imgUrl").focus();
				return false;
			  }
		  //制作方法校验
		    if($("#productionMethod").val()==""){
				$("#productionMethod").tips({
					side:3,
		            msg:'请填写制作方法',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#productionMethod").focus();
				return false;
			  }

			  $("#productMethodFrom2").submit();
	}


</script>
</head>
<body>
	<form name="productMethodFrom2" action="productionMethod/edit.do"
		id="productMethodFrom2"  method="post"  >

		<input type="hidden" name="id" id="id" value="${pd.id}" />
		<div id="zhongxin">
			<table style="margin:0 auto;margin-top:1em;">
		    <tr>
		        <th style="text-align:left;padding-top: 15px;">名称：</th>
				<td style="padding-top: 15px;"><input type="text" name="productionName" id="productionName"  value="${pd.productionName }" maxlength="32" placeholder="输入成品菜名称" title="名称" style="width: 207px;"/></td>
				<th></th>
				<td></td>
			</tr>
			   <tr>
		        <th style="text-align:left;padding-top: 15px;">图片：</th>
				<td style="padding-top: 15px;">
				<img alt="图片" src="${pd.imgUrl }"  name="imgUrlSrc">
				<input type="file" name="imgUrl" id="imgUrl"  maxlength="32" style="width: 136px;position: relative; top: -4px;" accept="image/*"/></td>
				<th></th>
				<td></td>
			</tr>

			<tr>
		        <th style="text-align:left;padding-top: 15px;">制作方法：</th>
				<td style="padding-top: 15px;">
				<textarea  rows="6" cols="10"   name="productionMethod" id="productionMethod"  maxlength="1000000" placeholder="输入成品菜制作方法" title="制作方法" >${pd.productionMethod }</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>

		<div id="zhongxin2" class="center" style="display: none">
			<br /> <br /> <br /> <br /> <img src="static/images/jiazai.gif" /><br />
			<h4 class="lighter block green"></h4>
		</div>

	</form>




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

	<script type="text/javascript">
		$(function() {

			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

		});
	</script>

</body>
</html>
