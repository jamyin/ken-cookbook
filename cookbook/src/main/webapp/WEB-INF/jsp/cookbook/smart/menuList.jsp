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
	<%-- 
			<!-- 检索  -->
			<form action="smart/menuList.do" method="post" name="menuForm" id="menuForm">
			<table border='0'>
				<tr>
				
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="menuName" value="${pd.menuName}" placeholder="配菜名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					
					
					<td style="vertical-align:top;"> 
					<span>配菜总成本：</span>
					 	<select class="chzn-select" name="totalcostOperators" id="totalcostOperators" data-placeholder=" " style="vertical-align:top;width: 50px;">
						<option value=""></option>
							<option value=">"  <c:if test="${pd.totalcostOperators=='>'}">selected</c:if>>&gt;</option>
							<option value="&lt;" <c:if test="${pd.totalcostOperators=='<'}">selected</c:if>>&lt;</option>
							<option value="=" <c:if test="${pd.totalcostOperators=='='}">selected</c:if>>=</option>
					  	</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<input autocomplete="off" id="nav-search-input" type="text" name="totalcostPrice" value="${pd.totalcostPrice}" placeholder="价格"  style="vertical-align:top;width: 50px;" />
					</td>
					
					
					<td style="vertical-align:top;"> 
					<span>配菜总定价：</span>
					 	<select class="chzn-select" name="totalPricingOperators" id="totalPricingOperators" data-placeholder=" " style="vertical-align:top;width: 50px;">
						<option value=""></option>
							<option value=">"  <c:if test="${pd.totalPricingOperators=='>'}">selected</c:if>>&gt;</option>
							<option value="&lt;" <c:if test="${pd.totalPricingOperators=='<'}">selected</c:if>>&lt;</option>
							<option value="=" <c:if test="${pd.totalPricingOperators=='='}">selected</c:if>>=</option>
					  	</select>
					</td>
					<td style="vertical-align:top;"> 
					 	<input autocomplete="off" id="nav-search-input" type="text" name="totalPricingPrice" value="${pd.totalPricingPrice}" placeholder="价格"  style="vertical-align:top;width: 50px;" />
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if>
				</tr>
			</table> --%>
					<!-- 检索  -->
			<form action="smart/menuList.do"  method="post"  name="productForm"  id="productForm">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							名称<input class="dde" autocomplete="off" id="nav-search-input" type="text" name="productName"  style="width: 120px;" placeholder="这里输入名称" />
					
						</span>
						</td>
							<td>
						<span class="input-icon">
						配菜总成本<select class="chzn-select" name="chengBen" id="chengBen" data-placeholder="请选择"  style="vertical-align:top;width: 150px;">
						<option value=""></option>
						<option value="<"><</option>
						<option value=">">></option>
						<option value="=">=</option>					
					  	</select>
							<input class="dde" autocomplete="off" id="nav-search-input" type="text" name="demandNumber" />/元
						
						</span>
						</td>
						<td>
						<span class="input-icon">
						配菜总定价<select class="chzn-select" name="dingJia" id="dingJia" data-placeholder="请选择"  style="vertical-align:top;width: 150px;">
							<option value=""></option>
						<option value="<"><</option>
						<option value=">">></option>
						<option value="=">=</option>			
					  	</select>
							<input class="dde" autocomplete="off" id="nav-search-input" type="text" name="singleWeight"  />/元											
						</span>						
						</td>				
				</tr>
			
				<tr>
				<td style="vertical-align:top;" >
					<c:if test="${QX.cha == 1 }">
					<button class="btn btn-mini btn-light" onclick="search();" title="检索" ><i id="nav-search-icon" class="icon-search"></i>搜索</button></c:if>
				<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i>导出</a>
					<c:if test="${QX.edit == 1 }">
					<a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i>导入</a>
					</c:if></td>
			    <td style="vertical-align:top;"></td>
				<td style="vertical-align:top;"></td>
				<td style="vertical-align:top;"></td>
				</tr>
		          <tr><td></td></tr>
			</table>
			<!-- 检索  -->

			<table id="table_report" class="table table-striped table-bordered table-hover">
			
						
						<thead>
			
				<tr>
				<th class="center">序号</th>
				<th class="center">配菜名称</th>
				<c:choose>
				<c:when test="${not empty typeList}">
				<c:forEach items="${typeList}" var="tl" >
					<th class="center" >${tl.typeName}</th>
					</c:forEach>
				</c:when>		
				</c:choose>				
				<th class="center">操作</th>								
				</tr>	
				</thead>
				<tbody>					
			
						<!-- 开始循环 -->	
				<c:choose>
		<c:when test="${not empty iflist}">
								
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${iflist}" var="iflist" varStatus="ls">					
						<tr>					
						<td class='center' style="width: 30px;">${ls.index+1}</td>
							
								<td>${iflist.fixingsName}</td>
									<c:choose>
									
							<c:when test="${not empty nameList}">
							<% int x =0;%>
							<% int y =0;%>
							<% String menu ="";%>
							<c:forEach items="${typeList}" var="tl" >
								<c:forEach items="${nameList}" var="nl" >
									<c:if test="${tl.categoryId == nl.productCategoryId}">
									<% x =1;%>
									</c:if>
									<% 
										if(x==1&&y==0){
										    y=1;
								 	%> 
								 	<td>${nl.productName}</td>
								 	<% 
										}
									%>
									
								</c:forEach>
							 	<% 
									if(x==0){
							 	%> 
							 	<td></td>
							 	<% 
									}
								%>
								<% x =0;y =0;%>
							 
							 
								</c:forEach>
								</c:when>
								</c:choose>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editProduct('${product.id}');"><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="delProduct('${iflist.fixingsName}');"><i class='icon-trash'></i></a>
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
			$("#menuForm").submit();
		}
		
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>smart/menuTypeShow.do';
			 diag.Width = 350;
			 diag.Height = 255;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		function test(){
			alert(1);
		}
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
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
		
		

		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			var STATUS = $("#STATUS").val();
			window.location.href='<%=basePath%>happuser/excel.do?USERNAME='+USERNAME+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID+'&STATUS='+STATUS;
		}
		</script>
		
	</body>
</html>

