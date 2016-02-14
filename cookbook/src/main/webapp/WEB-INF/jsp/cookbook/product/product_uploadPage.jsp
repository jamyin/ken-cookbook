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
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
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
		<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<script type="text/javascript" >
		
		</script>
<script type="text/javascript">
	$(top.hangge());
	 var product_id='';
	$(document).ready(function(){
		
	});

	
	


	
	//图片上传
	function setImagePreview(e,imgClass) {  
      if( !e.value.match( /.jpg|.gif|.png|.bmp|.jpeg/i ) ){
          alert('图片格式无效！');
          $(e).val("");
          return false;      
      }
     
      var fileSize = $(e)[0].files[0].size;
      var size = 9000000;   //需要动态的提出来  
     
     if(fileSize>size){  
          alert("附件大小不能大于"+(size/1024/1024)+"M！");  
          $(e).val("");
          $(e).parents("tr").find(".localImag").val("");
          return false;
      }
      if(fileSize<=0){  
          alert("附件大小不能为0M！");  
          $(e).val("");
          return false;  
      }  
 
      var docObj=$(e)[0];
      var imgObjPreview=$("."+imgClass)[0];  
      if(docObj.files && docObj.files[0]){  
          //火狐下，直接设img属性  
          imgObjPreview.style.display = 'block';  
          imgObjPreview.style.width = '360px';  
          imgObjPreview.style.height = '260px';    
          imgObjPreview.style.border=2;
          //imgObjPreview.src = docObj.files[0].getAsDataURL();  
           
          //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
          imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);  
         
      }else{  
          //IE下，使用滤镜  
          docObj.select();  
          var localImagId = document.getElementById("localImag");  
          //必须设置初始大小  
          localImagId.style.width = "360px";  
          localImagId.style.height = "260px";  
          //图片异常的捕捉，防止用户修改后缀来伪造图片  
          try{  
              localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";  
              localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;  
          }catch(e){  
              alert("您上传的图片格式不正确，请重新选择!");  
              return false;  
          }  
          imgObjPreview.style.display = 'none';  
          document.selection.empty();  
      }  
      return true;  
  } ;
  
	
	//保存
	function save(){

	      $("#productUploadFrom").submit();	
	   // top.Dialog.getDialogById("prouctionmedth").close();
	   	
	}

</script>
</head>
<body>
	<form action="productController/upload.do"  name="productUploadFrom"  id="productUploadFrom"  method="post"  enctype="multipart/form-data">
		<div id="zhongxin">
			<table>
						
		    <tr>
		        <th style="text-align:left;padding-left: 20px;padding-top: 15px;">名称：</th>
				<td style="padding-top: 15px;"><input type="text" name="productionName" id="productionName"   value="${productDto.name}"  maxlength="32" title="名称" style="width: 136px;"/>
					<input type="hidden" name="productId" id="productId" value="${productDto.id}"/>
				</td>
				<th></th>
				<td></td>				
			</tr>
 
		  <tr>
		        <th style="text-align:left;padding-left: 20px;padding-top: 15px;">图片：</th>
				<td style="padding-top: 15px;"><input type="file" name="imgUrl"  id="imgUrl" onchange="setImagePreview(this,'minImg');" accept="image/*" /></td>
				<th></th>
				<td></td>				
			</tr>		 
				 <tr>
          
            <th></th>
              <td style="padding-top: 15px;"><img class="minImg"/></td>
        <th></th>
        <td></td>
           </tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<a class="btn btn-mini btn-primary" onclick="save();"  >保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>

		<div id="zhongxin2" class="left" style="display: none">
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