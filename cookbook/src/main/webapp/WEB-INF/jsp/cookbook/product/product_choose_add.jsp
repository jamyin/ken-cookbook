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
		
			
			$(target).toggleClass("sel");
			if($(target).hasClass("sel")||$(target).siblings().hasClass("sel")){
				product_id=$(target).data("id");
			}else{
				product_id='';
			}
			$(target).siblings().removeClass("sel");
		});
	});
	
	//修改
	function edit_Product(productId){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.ID="product_edit";
		 diag.Title ="编辑";
		 diag.URL = '<%=basePath%>productController/editPage.do?pageInfo=chooseEdit&id='+productId;
		 diag.Width = 535;
		 diag.Height = 585;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				nextPage('${page.currentPage}');
			}
			
			diag.close();
			top.Dialog.close();
			
		 };
		 diag.show();
	}

	
	
	
	 function isSure(){
		 //每次点确认按钮,需要清空product_id;
		 product_id='';
		 var infore=true;
		 var productName=$("#productName").val();
		 
		 if(productName==''){
			 $('#nameArea').html('');
		 }else{
		  	$.ajax({
				type: "POST",
				url: '<%=basePath%>productController/hasProduct.do',
		    	data: {productName:productName},
				dataType:'json',
				cache: false,
				success: function(data){
	
	if (true == data.success) {
					$('#nameArea').html('');
					var myObject = data.obj;
					var dislpayVal=new Array();
					if(infore==true){
					for (var i = 0; i < myObject.length; i++) {
						var owrap = $("<span class='keyWrap' style='margin: 5px;'><h5></h5></span>");
						
						owrap.text(myObject[i].name);
						owrap.data("id", myObject[i].id);
						dislpayVal.push(owrap);
						infore=false;
					}}
				$.each(dislpayVal,function(i,data){
						$("#nameArea").append(data);
					});
					//$("#nameArea").val(dislpayVal);
				} else {
                      $('#nameArea').html('');
				}
			}
		});}
	 
	}
	 
	 function selected_next(){
		  var text=$('#nameArea').html();
		 if(text==''){
			 bootbox.alert("没有发现成品,请选择右边按钮.","确定");
			 return false;
		 }else if(product_id==''||product_id==undefined){
			 bootbox.alert("请选择成品菜,再执行下一步操作.","确定");
				 //alert("请选择成品菜,再执行下一步操作.");
				 return false;
		  }
	debugger;
		 edit_Product(product_id);

	}
	 //跳到新添加成品菜页面
	  function no_selected_next(){
		  var text=$('#nameArea').html();
		 if(text!=''){
			 bootbox.alert("发现成品,请选择左边按钮.","确定");
			 return ;
		 }
		 var choose_diag = new top.Dialog();
		
		 choose_diag.Drag=true;
		 choose_diag.ID="product_add";
		 choose_diag.Title ="新增成品菜";
		 choose_diag.URL = '<%=basePath%>productController/addPage.do';
		 choose_diag.Width = 535;
		 choose_diag.Height = 585;
		 choose_diag.CancelEvent = function(){ //关闭事件
            //先关闭二级弹出框，再关闭一级弹出框;
		 	choose_diag.close();
		    top.Dialog.close();
		 
			
			 };
			 choose_diag.show();

	}



</script>
</head>
<body>
	
	
		<div id="zhongxin">
			<table style="padding-top: 30px;">
				<tr>
					<td style="padding-left: 30px; padding-top: 20px;"><h4>名称</h4></td>
					<td style="padding-top: 25px;"><input type="text"
						name="productName" id="productName" maxlength="32"
						
						placeholder="输入名称" title="名称" style="width: 120px;" /></td>
					<td style="padding-top: 15px;"><a
						class="btn btn-mini btn-primary" onclick="isSure();">确认</a></td>
				</tr>



			</table>
			<table>
				<tr>
					<td style="padding-left: 30px;"><h4>以下有您填写的成品吗</h4></td>
				</tr>
				<tr>
					<td style="padding-left: 30px;"><div name="nameArea"
							id="nameArea" cols="80" rows="5" style="width: 450px;height: 100px;border: 1px solid #7A7A7A"></div></td>
				</tr>
				<tr>
					<td style="text-align: center; padding-left: 292px;padding-top: 15px;"><a
						class="btn btn-mini btn-primary" onclick="selected_next();">已选定,下一步</a> <a
						class="btn btn-mini btn-danger" onclick="no_selected_next();">都没有,下一步</a>
					</td>
					<td style=""></td>
				</tr>
			</table>
		</div>

		<div id="zhongxin2" class="center" style="display: none">
			<br /> <br /> <br /> <br /> <img src="static/images/jiazai.gif" /><br />
			<h4 class="lighter block green"></h4>
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

	<script type="text/javascript">
		$(function() {
			 $('#productName').keydown(function(e){
				 
				 e.stopPropagation();
				 if(e.keyCode==13){
					isSure();
				 }
				 if(e.keyCode==9){
					e.preventDefault();
					 }
				 });
			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

		});
	</script>

</body>
</html>