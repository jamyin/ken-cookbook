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
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
        <!-- 多选下拉框样式 -->
        <link rel="stylesheet" href="static/css/inputiconplus.css" />
				<style media="screen">
					#zhongxin{
						padding:2em 0 0 2em;
					}
				</style>

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
	            msg:'填写原料名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#name").focus();
			return false;
		  }
		if($("#cost").val()==""){
			$("#cost").tips({
				side:3,
	            msg:'填写原料成本',
	            bg:'#AE81FF',
	            time:2
			});
			$("#cost").focus();
			return false;
		  }else{
			  if(!validate($("#cost").val())){
				  bootbox.alert("原料成本只能含有数字和.");
				  return false;
			 }else if($("#cost").val().length>6){
				 bootbox.alert("原料成本的长度不能大于6");
				  return false;
			 }
		  }
		if($("#remark").val()==""){
			$("#remark").tips({
				side:3,
	            msg:'填写原料备注',
	            bg:'#AE81FF',
	            time:2
			});
			$("#remark").focus();
			return false;
		  }

		   //传营养的条数
		    var nutrsize = $(".select_wrap").length;
		   $("#nutrSize").val(nutrsize);
		   //验证营养不能为空
		    //验证营养的含量不能为空
		   if($(".select_wrap")[0].style.visibility=='hidden'){
			   bootbox.alert("营养不能为空");
			   return false;
		   }else{
			   var sn =$($("#selectNutr")[0]).children();
			   var sn_ids ='';
			   for(var j=0;j<sn.length;j++){
			      var sn_name = $($($($(sn[j])[0]).children()[1])[0]).val();
			      var sn_content =  $($($($(sn[j])[0]).children()[2])[0]).val();
			      var sn_id = $($($($($(sn[j])[0]).children()[1])[0])[0]).context.id;
			      sn_ids = sn_ids + sn_id +",";
			      
				   if(sn_content==null || sn_content==''){
					   bootbox.alert(sn_name +" :含量不能为空");
					   return false;
			       }else{
			    	   if( !validate(sn_content) ){
						   bootbox.alert(sn_name +" :含量只能含有数字和.");
						   return false;
					   }else if(sn_content.length>6){
						   bootbox.alert(sn_name +" :含量的长度不能大于6");
						   return false;
					   }
			       }
			   }
			   
			   $("#snIds").val(sn_ids);
		   }

		   hasMNAme($("#name").val());
	}
	
	
	//验证该原料名称是否存在
	function hasMNAme(name){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>materialController/hasMNAme.do?name='+name,
			dataType:'json',
			success: function(data){
				if(data.length>0){
					var mid = $("#material_id").val();
					if(data[0].id != mid){
						bootbox.alert("该原料名称已经存在");
						return false;
					}else{
						$("#materialForm").submit();
						$("#zhongxin").hide();
						$("#zhongxin2").show();
					}
				}else{
					$("#materialForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				}
			}
		});
	}

	//验证数字
	 function validate(param){
      var reg = new RegExp("^\\d+\\.?\\d*$|^.{0}$");
      return reg.test(param);
	 }

	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
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

	 //营养下拉框
	function iconlick(data){
	//	console.log($("#"+data));
		$("#"+data).remove();
		//console.log( $(".select_wrap"));
		//alert($(".select_wrap").length);
		var kk = "";
		if($(".select_wrap").length==0){
			kk += "<div  class='select_wrap' style='width: 214px; margin-left:0;visibility: hidden;'>";
			kk += "<input  type='text' class='kel' name='nutrname' value='' style='width: 75px;margin-bottom:0;'  />";
			kk += "<input type='text' class='kel' name='name' value='' style='width: 36px;margin-bottom:0;'> /";
			kk += "<span class='kel_label'><input type='text' class='kel'  value='' style='width: 31px;margin-bottom:0;'></span>";
			kk += "<i id='iconplus' class='icon icon-plus input_plus' style='visibility: visible;' ></i>";
			kk += "</div>";
			$("#selectNutr").append(kk);
		}
	 }

</script>
	</head>
<body>
	<form action="materialController/updateMaterial.do" name="materialForm" id="materialForm" method="post">
	  <!--  <input id="nutPids"  name="nutPids" type="hidden" value="1" />
	   <input id="nutContent" name="nutContent" type="hidden" value="34" />  -->
	   <input id="nutrSize" name="nutrSize" type="hidden" />
	   <input id="imgUrl" name="imgUrl" type="hidden" value="/upload/aaa" />
	   <input id="snIds" name="snIds" type="hidden"    />
	   <input id="material_id" name="material_id" type="hidden" value="${materialDto.id}"  />
		<div id="zhongxin" >
		<table>
		    <tr>
		        <td style="text-align:left">名称：</td>
				<td><input type="text" name="name" id="name"  maxlength="32" value="${materialDto.name}" title="名称"/></td>
			</tr>
			<tr >
			    <td style="text-align:left">大类：</td>
				<td style="position: relative;padding-bottom:12px;">
				<select class="chzn-select" name="bigCategoryId" id="bigCategoryId" data-placeholder="请选择大类" style="vertical-align:top;" >
				<option value=""></option>
				<c:forEach items="${listMB}" var="bigcate">
					<option value="${bigcate.id }" <c:if test="${bigcate.id ==materialDto.bigCategoryId }">selected </c:if> >${bigcate.name }</option>
				</c:forEach>
				</select>
				<i id="bigCategoryIdiconplus" class="icon icon-plus input_big_plus_e"></i>
				</td>

			</tr>
			<tr >
			    <td style="text-align:left">品牌：</td>
				<td>
				<select class="chzn-select" name="brandId" id="brandId" data-placeholder="请选择品牌" style="vertical-align:top;">
				<option value=""></option>
				<c:forEach items="${listbrand}" var="brand">
					<option value="${brand.id }" <c:if test="${brand.id == materialDto.brandId}">selected </c:if> >${brand.name }</option>
				</c:forEach>
				</select>
				<i id="brandIdiconplus" class="icon icon-plus input_brand_plus_e"></i>
				</td>
			</tr>
		    <tr>
		        <td style="text-align:left;padding: 10px 0 0 0;">类型：</td>
		        <td style="padding: 10px 0 0 0;">
				   <label style="float:left;padding-left: 20px;"><input name="type" id="type"  type="radio" value="1"  <c:if test="${materialDto.type==1}"> checked </c:if> ><span class="lbl" style="width:60px">原料</span></label>
		           <label style="float:left;padding-left: 20px;"><input name="type" id="type"  type="radio" value="2" <c:if test="${materialDto.type==2}"> checked </c:if> ><span class="lbl" style="width:60px">半成品</span></label>
		        </td>
		    </tr>
		     <tr>
		        <td style="text-align:left;padding: 10px 0 0 0;">敏感食材：</td>
		        <td style="padding: 8px 0 0 0;">
				   <label style="float:left;padding-left: 20px;"><input name="isSensitiveMaterial" id="isSensitiveMaterial"  type="radio" value="0"  <c:if test="${materialDto.isSensitiveMaterial==0}">checked</c:if>   ><span class="lbl" style="width:60px">否</span></label>
		           <label style="float:left;padding-left: 20px;"><input name="isSensitiveMaterial" id="isSensitiveMaterial"  type="radio" value="1"  <c:if test="${materialDto.isSensitiveMaterial==1}">checked</c:if>   ><span class="lbl" style="width:60px">是</span></label>
		        </td>
		    </tr>
			<tr>
		        <td style="text-align:left">成本：</td>
				<td><input type="text" name="cost" id="cost"  maxlength="32" style="width: 90px;position:relative;top:8px;" value="${materialDto.strcost}" title="成本"/><span style="position:relative;top:5px;">/元</span></td>
			</tr>
			<tr >
			 <td style="vertical-align: bottom;">营养:</td>
			 <td>
			    <div id="selectNutr" class="dwrap" style="width:276px;">
			        ${nutInfo}
                <!--    <div id='0' class="select_wrap" style="width: 214px; margin-left:0;"><input type="hidden" id='nutrPid_0' name='nutrPid_0' value='1'  /><input  type="text" id='nutrPName_0' class="kel" name="nutrname" value='维生素' style="width: 75px;margin-bottom:0;"  /><input type="text" class="kel" name='ncontent_0' id='ncontent_0' value='23'  style="width: 36px;margin-bottom:0;margin-left: 6px;"> / <span class="kel_label"><input type="text" class="kel" id="unit" name="unit" value='毫克' style="width: 31px;margin-bottom:0;"></span><i class="icon icon-minus input_minus"    onClick="iconlick(0)"></i></div>  -->
                </div>
			 </td>
			</tr>
			<tr>
		        <td style="text-align:left;">备注：</td>
				<td><textarea  name="remark" id="remark"  maxlength="32" style="width: 206px;height: 118px;margin-top:16px;"   title="备注"/>${materialDto.remark}</textarea ></td>
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
		<!--  <script type="text/javascript" src="static/js/inputiconplus.js"></script>   -->
		<!-- 加减号 -->

		<script type="text/javascript">

		$(function() {

			$(".mselect").css("margin-bottom","none");

			//单选框
			$chosen = $(".chzn-select");
			$chosen.chosen();
			$(".chzn-select-deselect").chosen({allow_single_deselect:true});

			//日期框
			 //为大类添加页面
		    $("#bigCategoryIdiconplus").click(function(){
		    	bigadd();
		    });




			 //根据选择的营养刷新单位
			$("#nutrSelect").change(function(){
				$.ajax({
					type:"POST",
					dataType:'json',
					url: '<%=basePath%>materialController/findNutrById.do?paramId='+$(this).val(),
					success:function(data){
						console.log(data);
						var pname = data.remark;
						window.parent.document.getElementById("_DialogFrame_0").contentDocument.getElementById("unit").value=pname;
						 $chosen.trigger("liszt:updated");
					}
				});
			});

			//为营养添加页面
			$("#iconplus").live('click', function(){
				 top.jzts();
				 var diag4 = new top.Dialog();
				 diag4.ID="nulist";
				 diag4.Drag=true;
				 diag4.Title ="新增营养";
				 diag4.URL = '<%=basePath%>materialController/findNutrsList.do';
				 diag4.Width = 900;
				 diag4.Height = 650;
				 diag4.CancelEvent = function(){ //关闭事件
					 //刷新品牌
				      $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>materialController/freshBrand.do',
						 dataType:'json',
						 success: function(data){
						 /*     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							 window.parent.document.getElementById("_DialogFrame_0").contentDocument.getElementById("brandId").innerHTML=a;
							 $chosen.trigger("liszt:updated"); */
						 }
				     });
					diag4.close();
				 };
				 diag4.show();
			});


			 //为品牌添加页面
			$("#brandIdiconplus").click(function(){
				 top.jzts();
				 var diag3 = new top.Dialog();
				 diag3.Drag=true;
				 diag3.Title ="新增品牌";
				 diag3.URL = '<%=basePath%>materialController/addBrand.do';
				 diag3.Width = 900;
				 diag3.Height = 650;
				 diag3.CancelEvent = function(){ //关闭事件
					 //刷新品牌
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>materialController/freshBrand.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							 window.parent.document.getElementById("_DialogFrame_0").contentDocument.getElementById("brandId").innerHTML=a;
							 $chosen.trigger("liszt:updated");
						 }
				     });
					diag3.close();
				 };
				 diag3.show();
			});

		  //新增
			function bigadd(){
				 top.jzts();
				 var diag2 = new top.Dialog();
				 diag2.Drag=true;
				 diag2.Title ="新增大类";
				 diag2.URL = '<%=basePath%>materialController/addBigCategory.do';
				 diag2.Width = 900;
				 diag2.Height = 650;
				 diag2.CancelEvent = function(){ //关闭事件
					 //刷新大类
				     $.ajax({
				    	 type: "POST",
						 url: '<%=basePath%>materialController/freshBig.do',
						 dataType:'json',
						 success: function(data){
						     var myObject = data;
							 var a = "<option value=''></option>";
							 //var b ="";
							 for (var i = 0; i < myObject.length; i++) {
									a += "<option value=\'"+myObject[i].id+"\'>"
											+ myObject[i].name + "</option>";
							 }
							 window.parent.document.getElementById("_DialogFrame_0").contentDocument.getElementById("bigCategoryId").innerHTML=a;
							 $chosen.trigger("liszt:updated");
						 }
				     });
					diag2.close();
				 };
				 diag2.show();
			}

		 //初始化营养




		});

		</script>

</body>
</html>
