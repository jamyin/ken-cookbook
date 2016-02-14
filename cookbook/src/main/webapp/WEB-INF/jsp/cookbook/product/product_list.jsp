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
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	</head> 
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="productController/findProduct.do" method="post" name="productForm" id="productForm">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="productName"  value="${pd.productName}" style="width: 120px;" placeholder="这里输入名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
						</td>
							<td>
						<span class="input-icon">
							<input autocomplete="off" id="demandNumber"  style="vertical-align:top;width: 120px;" type="text" name="demandNumber" value="" placeholder="数量/份" />
						<i id="nav-search-icon" class="icon-search"></i>
						</span>
						</td>
						<td>
						<span class="input-icon">
							<input autocomplete="off" id="singleWeight"  style="vertical-align:top;width: 120px;" type="text" name="singleWeight"  placeholder="单份克重/克" />
						<i id="nav-search-icon" class="icon-search"></i>
						</span>
						</td>
						<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="categoryId" id="categoryId" data-placeholder="请选择类别" style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.id}" <c:if test="${pd.categoryId==category.id}">selected</c:if>>${category.name}</option>
						</c:forEach>
					  	</select>
					   </td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="styleId" id="styleId" data-placeholder="请选择菜系" style="vertical-align:top;width: 150px;padding-left: 10px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listStyle}" var="style">
							<option value="${style.id}" <c:if test="${pd.styleId==style.id}">selected</c:if>>${style.name}</option>
						</c:forEach>
					  	</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="tasteId" id="tasteId" data-placeholder="请选择口味" style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listTaste}" var="taste">
							<option value="${taste.id}" <c:if test="${pd.tasteId==taste.id}">selected</c:if>>${taste.name}</option>
						</c:forEach>
					  	</select>
					</td>
				<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="shapeId" id="shapeId" data-placeholder="请选择形状" style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listShape}" var="shape">
							<option value="${shape.id}" <c:if test="${pd.shapeId==shape.id}">selected</c:if>>${shape.name}</option>
						</c:forEach>
					  	</select>
					   </td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="colorId" id="colorId" data-placeholder="请选择颜色" style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listColor}" var="color">
							<option value="${color.id}" <c:if test="${pd.colorId==color.id}">selected</c:if>>${color.name}</option>
						</c:forEach>
					  	</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="cuisineId" id="cuisineId" data-placeholder="请选择烹饪" style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listCuisine}" var="cuisine">
							<option value="${cuisine.id}" <c:if test="${pd.cuisineId==cuisine.id}">selected</c:if>>${cuisine.name}</option>
						</c:forEach>
					  	</select>
					 </td>
					 <td>
					    <a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i>导入</a>
						<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i>导出</a>
					    <button class="btn btn-mini btn-light" onclick="search();" title="检索" ><i id="nav-search-icon" class="icon-search"></i>搜索</button>
				        <button class="btn btn-mini btn-light" onclick="clear_product();" title="清空" ><i id="nav-refresh-icon" class="icon-refresh"></i>清空</button>
			        </td>
                </tr>
                   <tr><td></td></tr>
                  <tr><td></td></tr>
                  <tr><td></td></tr>
				   <tr><td></td></tr>
		          <tr><td></td></tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
					    <th>序号</th>
						<th>名称</th>
						<th>类别</th>
						<th>菜系</th>
						<th>口味</th>
						<th>形状</th>
						<th>烹饪</th>
					    <th>颜色</th>
						<th>原料</th>
					    <th>单份成本</th>
					    <th>单份定价</th>
						<th>单份克重</th>
					    <th>需求数</th>
						<th>备注</th>
						<th>总成本</th>
					    <th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty listProduct}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${listProduct}" var="product" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${product.name}</td>
								<td>${product.productCategoryName}</td>
								<td>${product.productStyleName}</td>
								<td>${product.productTasteName}</td>
								<td>${product.productShapeName}</td>
								<td>${product.productCuisineName}</td>
								<td>${product.productColorName}</td>
								<td align="center"><button class="btn btn-mini btn-primary" data-toggle="modal" data-target="#myModal" onclick="checkMaterial('${product.id}');">查看</button></td>
								<td>${product.singleCost}元</td>
								<td>${product.eachPricing}元</td>
								<td>${product.singleWeight}克</td>
							    <td>${product.demandNumber}份</td>
						        <td>${product.remark}</td>
								<td>${product.totalCost}元</td>
								<td style="width: 80px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editProduct('${product.id}');"><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="delProduct('${product.id}','${product.name}');"><i class='icon-trash'></i></a>
										<a class='btn btn-mini btn-upload' title="上传图片" onclick="uploadImg_Product('${product.id}');"><i class='icon-edit'></i></a>
									</div>
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
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
				</td>
			    <td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
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
			$("#productForm").submit();
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
	       }
	      }
		
		//清空
		function clear_product(){
			$("input[name='productName']").val('');
			$("input[name='demandNumber']").val('');
			$("input[name='singleWeight']").val('');
		    $("#categoryId").val('');
			$("#styleId").val('');
			$("#tasteId").val('');
			$("#colorId").val('');
			$("#shapeId ").val('');
			$("#cuisineId").val(''); 
		}
	
	
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
	         diag.Title ="新增";
			 diag.URL = '<%=basePath%>productController/addChoosePage.do';
			 diag.Width = 505;
			 diag.Height = 275;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage('${page.currentPage}');
					 }
				}
		         //刷新当前面板
				 search();
		         diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function editProduct(productId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.ID="product_edit";
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>productController/editPage.do?id='+productId;
			 diag.Width = 535;
			 diag.Height = 585;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${page.currentPage}');
				}
				 //刷新当前面板
				 search();
				diag.close();
				
			 };
			 diag.show();
		}
		
		//删除
		function delProduct(productId,msg){
			var success=false;
		
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>productController/delete.do?id="+productId;
					$.get(url,function(data){
						alert(data.msg);
						 //刷新当前面板
						 search();
						
					});
					 
				}
			});
		 	
		}
		
		function uploadImg_Product(productId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.ID="product_uploadImg";
			 diag.Title ="上传图片";
			 diag.URL = '<%=basePath%>productController/uploadPage.do?id='+productId;
			 diag.Width = 490;
			 diag.Height = 435;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${page.currentPage}');
				}
				 //刷新当前面板
				 search();
				diag.close();
				
			 };
			 diag.show();
		}
		
		//查看营养
		function checkMaterial(productId){
		
			$.ajax({
				type :"POST",
				url: '<%=basePath%>productController/findMaterialByProductId.do?productId='+productId,
				dataType:'json',
				success: function(data){
				var msg = "";
			    //原料名称
			
					for(var j=0;j<data.length;j++)
						{   
						  if(j>0){
							  msg+="  ";
						  }
							msg += data[j].name;
						}
		
					$("#product_modal-body").html(msg);
				}
			});
		}
</script>
		
		<script type="text/javascript">
		
		$(function() { 
			
			
			
           $('#demandNumber').keydown(function(e){
        	
			
				 var temp= $('#demandNumber').val();
				 if(e.keyCode==13){
					 if(temp!="")
				      {
				         var digit =/^\+?[1-9]\d*$/;
				         if (!digit.test(temp)) 
				         { 
				            alert("只能输入正整数数字");
				            e.currentTarget.value="";
				        	e.stopPropagation();
				        	   
				         }
				         if(temp.length>7){
				        	 e.currentTarget.value="";
				        	  alert("数量长度最多为7位");
				        	  
					      
				         }
				       }
				 }
	
				 });
           
           $('#singleWeight').keydown(function(e){
				 

				 var temp= $('#singleWeight').val();
				 if(e.keyCode==13){
					 if(temp!="")
				      {
				         var digit =/^\+?[1-9]\d*$/;
				         if (!digit.test(temp)) 
				         {         	
				
				            alert("只能输入正整数数字");
				            e.currentTarget.value="";
				      	  $('#singleWeight').focus();
				         }
				         if(temp.length>7){
				        	    e.currentTarget.value="";
				        	  alert("单份克重长度最多为7位");
				        	  $('#singleWeight').focus();
					       
				         }
				       }
				 }
				 });
			
		     var obj = $("#demandNumber").bind("blur",
		             function () {
		                 CkeckData(this)
		             });
		     var obj2 = $("#singleWeight").bind("blur",
		             function () {
		                 CkeckData(this)
		             });      
			
			//日期框
			$('.date-picker').datepicker();
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
	      function CkeckData(obj)
	      {
	       var inputObj = obj;
	       var temp = inputObj.value;
	       if(temp!="")
	      {
	         var digit =/^\+?[1-9]\d*$/;
	         if (!digit.test(temp)) 
	         {
	            alert("只能输入正整数数字");
	            inputObj.focus();
	         }
	        if(temp.length>7){
	        	  alert("长度最多为7位");
	        	  inputObj.focus();
		            return false;
	         }
	       }}
		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			window.location.href='<%=basePath%>productController/excel.do';
		}
		
		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>productController/productUploadExcel.do';
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage('${page.currentPage}');
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		</script>
	<!-- 模态框（Modal） -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                   原料列表
            </h4>
         </div>
         <div class="modal-body" id="product_modal-body">
       
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
      
         </div>
      </div>
   </div>
</div>
	</body>
</html>

