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
	<style media="screen">
		.popover-content{
			word-wrap: break-word;
		}
	</style>
<body>

<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">

  <div class="row-fluid">


	<div class="row-fluid">
			<!-- 检索  -->
			<form action="productionMethod/findAllProductionMethod.do" method="post" name="productionMethodForm" id="productionMethodForm">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="productName"  id="productName"  style="width: 120px;" placeholder="这里输入名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
						</td>

				</tr>

				<tr>
				<td style="vertical-align:top;" >
					<c:if test="${QX.cha == 1 }">
					<button class="btn btn-mini btn-light" onclick="search();" title="检索" ><i id="nav-search-icon" class="icon-search"></i>搜索</button></c:if>
				<a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i>导出</a>
					<c:if test="${QX.edit == 1 }">

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
					    <th>序号</th>
					    <th>图片</th>
						<th>名称</th>
			 			<th>做法</th>
					    <th class="center">操作</th>
					</tr>
				</thead>

				<tbody>

				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty pmlist}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${pmlist}" var="productMethod" varStatus="pm">
							<tr>
								<td class='center' style="width: 30px;">${pm.index+1}</td>
								<td><img alt="图片" src="${productMethod.imgUrl}" ></td>
								<td>${productMethod.productionName}</td>
								<td>
								<a id="infoid"  class="ben"  data-placement="right" data-animation="true" data-title="制作方法"  data-content="${productMethod.productionMethod}">点击查看</a></td>

								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editProductMethod('${productMethod.id }');"><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="delProductMethod('${productMethod.id }');"><i class='icon-trash'></i></a>
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
			$("#productionMethodForm").submit();
		}

		$(function(){
			$("a.ben").popover('hide');
		});

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
			 diag.ID="prouctionmedth";
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>productionMethod/add.do';
			 diag.Width = 505;
			 diag.Height = 275;
			 diag.CancelEvent = function(){ //关闭事件

		     	diag.close();

				 location.href="<%=basePath%>/productionMethod/findAllProductionMethod.do";

			 };
			 diag.show();
		}

		//修改
		function editProductMethod(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.ID="updateprouctionmedth";
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>productionMethod/update.do?id='+id;
			 diag.Width = 380;
			 diag.Height = 360;
			 diag.CancelEvent = function(){ //关闭事件
					diag.close();
					 location.href="<%=basePath%>/productionMethod/findAllProductionMethod.do";
			 };
			 diag.show();
		}
		//删除
		function delProductMethod(id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>productionMethod/deleteP.do?id="+id;
					$.get(url,function(data){
						nextPage('${page.currentPage}');
					});
				}
			});
		}



		</script>

		<script type="text/javascript">

		$(function() {

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

		//导出excel
		function toExcel(){
			var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val();
			window.location.href='<%=basePath%>productionMethod/excel.do';
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
