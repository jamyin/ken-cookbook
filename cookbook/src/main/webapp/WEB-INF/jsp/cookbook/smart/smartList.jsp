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
	<title>智能配菜列表显示页面</title>
	<%@ include file="../admin/top.jsp"%> 
	</head> 
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">


	<div class="row-fluid">	
			<!-- 检索  -->
			<form  method="post" id="SmartListForm" action="smart/findSmartList.do">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" type="text" name="setMealName"  id="setMealName"  style="width: 120px;" placeholder="这里输入名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
								
				
					<td style="vertical-align:top;" >
					<button class="btn btn-mini btn-light" onclick="search();" title="检索" ><i id="nav-search-icon" class="icon-search"></i>搜索</button>
						<%-- <c:if test="${QX.cha == 1 }">
							<button class="btn btn-mini btn-light" onclick="search();" title="检索" ><i id="nav-search-icon" class="icon-search"></i>搜索</button></c:if>
							<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i>导出</a>
							<c:if test="${QX.edit == 1 }">
							<a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i>导入</a>
						</c:if> --%>
					</td>
				</tr>
		          <tr><td></td></tr>
			</table>
			<!-- 检索  -->
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
					    <th style="width: 30px">序号</th>
					    <th style="width: 100px">套餐名称</th>
						<th style="width: 100px">午餐份数</th>
			 			<th style="width: 100px">晚餐份数</th>
			 			<th style="width: 100px">创建时间</th>
			 			<th style="width: 100px">更新时间</th>
					    <th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty intelligentFixingsDto}">
						<c:forEach items="${intelligentFixingsDto}" var="smartList" varStatus="pm">
							<tr>
								<td class='center' style="width: 30px;">${pm.index+1}</td>																
								<td>${smartList.setMealName}</td>															
								<td>${smartList.lunchCount}</td>																
								<td>${smartList.dinnerCount}</td>
								<td>${smartList.createTimeStr}</td>						
								<td>${smartList.lastUpdateTimeStr}</td>						
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editSmartMethod('${smartList.id }');"><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="delSmartMethod('${smartList.id }');"><i class='icon-trash'></i></a>
										<a class='btn btn-mini btn-info' title="查询配菜结果" id="findResult" smartId="${smartList.id }" lunchCount="${smartList.lunchCount}" dinnerCount="${smartList.dinnerCount}" productId="${smartList.productId}">
										<i class='icon-search'></i></a>
								</td>
							</tr>						
						</c:forEach>
						
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
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
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
			$("#SmartListForm").submit();
		}
		
		
		//去发送电子邮件页面
		function sendEmail(EMAIL){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送电子邮件";
			 diag.URL = '<%=basePath%>head/goSendEmail.do?EMAIL='+EMAIL;
			 diag.Width = 660;
			 diag.Height = 470;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//去发送短信页面
		function sendSms(phone){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="发送短信";
			 diag.URL = '<%=basePath%>head/goSendSms.do?PHONE='+phone+'&msg=appuser';
			 diag.Width = 600;
			 diag.Height = 265;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		//新增
		function add(){
			top.jzts();
			 var diag = new top.Dialog();
			 //diag.ID="addSmartMethod";
			 diag.Drag=true;
			 diag.Title ="新增智能配菜";
			 diag.URL = '<%=basePath%>smart/menuTypeShow.do';
			 diag.Width = 350;
			 diag.Height = 255;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						  //nextPage(${page.currentPage});
					 }
				}
				//刷新当前面板
				//search();
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function editSmartMethod(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.ID="editSmartMethod";
			 diag.Drag=true;
			 diag.Title ="编辑智能配菜";
			 diag.URL = '<%=basePath%>smart/toEditSmart.do?smartId='+id;
			 diag.Width = 600;
			 diag.Height = 755;
			 diag.CancelEvent = function(){ //关闭事件
				//刷新当前面板
				search();
				diag.close();					
				<%-- location.href="<%=basePath%>/SmartionMethod/findAllSmartionMethod.do"; --%>
			 };
			 diag.show();
		}		
		//删除
		function delSmartMethod(id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = '<%=basePath%>smart/delSmart.do?id='+id;
					$.get(url,function(data){
						nextPage('${page.currentPage}');
					}); 
				}
			});
		}
		
		
		
		</script>
		
		<script type="text/javascript">
		
		$(function(){
			
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
			
			$("#findResult").on('click', function(){
				 var id = $("#findResult").attr("smartId");
				 var productId = $("#findResult").attr("productId");
				 var lunchCount = $("#findResult").attr("lunchCount");
				 var dinnerCount = $("#findResult").attr("dinnerCount");
				 if(lunchCount == ""){
					 lunchCount = null;
				 }
				 if(dinnerCount == ""){
					 dinnerCount = null;
				 }
				 
				 //console.log("id="+id+";productId="+productId+";lunchCount="+lunchCount+";dinnerCount="+dinnerCount);
				 //debugger;
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
			});
		});
		
		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			window.location.href='<%=basePath%>SmartionMethod/excel.do';
		}
		
		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>user/goUploadExcel.do';
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
	</body>
</html>

