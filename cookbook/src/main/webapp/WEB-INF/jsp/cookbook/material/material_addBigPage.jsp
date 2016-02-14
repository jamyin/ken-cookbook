<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
        <!-- 多选下拉框样式 -->
        <link rel="stylesheet" href="static/css/inputiconplus.css" />
        <script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
	});
	
	//保存
	function save(){
	    if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'填写大类名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#name").focus();
			return false;
		  }
	    hasBigName($("#name").val());
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断大类名称是否存在
	function hasBigName(name){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>materialBigCategoryController/hasBigName.do?bigName='+name,
			dataType:'json',
			success: function(data){
				if(data.length > 0){
					bootbox.alert("该大类名称已经存在");
					return false;
				}else{
				    $("#materialbigForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show(); 
				}
			}
		});
	}
	
	
	
	//判断用户名是否存在
	function hasU(){
		var USERNAME = $.trim($("#loginname").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
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
	    	data: {NUMBER:NUMBER,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#NUMBER").tips({
							side:3,
				            msg:'编号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#NUMBER').val('')",2000);
				 }
			}
		});
	}

    
    
</script>
	</head>
<body>
	<form action="materialController/saveBig.do" name="materialbigForm" id="materialbigForm" method="post">
	   <input id="nutPids"  name="nutPids" type="hidden" value="1" />
	   <input id="nutContent" name="nutContent" type="hidden" value="34" />
	   <input id="imgUrl" name="imgUrl" type="hidden" value="/upload/aaa" />
		<div id="zhongxin" >
		<table>
		    <tr>
		     <td>&nbsp;</td>
		     <td>&nbsp;</td>
		    </tr>
		    <tr>
		        <td style="text-align:left">名称：</td>
				<td><input type="text" name="name" id="name"  maxlength="32" placeholder="这里输入大类名称" title="名称"/></td>
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
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/inputiconplus.js"></script><!-- 加减号 -->
		
		<script type="text/javascript">
		
		$(function() {
	
			//$(".dwrap").parent().height(120);
			//$(".dwrap").height(120);
			$(".mselect").css("margin-bottom","none");
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			//$('.date-picker').datepicker();
			 //为大类添加页面
		    $("#bigCategoryIdiconplus").click(function(){
		    	bigadd();
		    });

			$("#name").keydown(function(){
				if(event.keyCode == 13){
					return false;
				}
			});
			 
		  //新增
			function bigadd(){
				 top.jzts();
				 var diag2 = new top.Dialog();
				 diag2.Drag=true;
				 diag2.Title ="新增";
				 diag2.URL = '<%=basePath%>materialController/addBigCategory.do';
				 diag2.Width = 900;
				 diag2.Height = 650;
				 diag2.CancelEvent = function(){ //关闭事件
					diag2.close();
				 };
				 diag2.show();
			}
		});
		
		</script>
	
</body>
</html>