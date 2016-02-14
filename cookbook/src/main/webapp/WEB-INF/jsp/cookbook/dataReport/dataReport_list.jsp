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
	<style type="text/css">
      .nutr_show{
				display: inline-block;
		    text-align: center;
		    width: 30%;
		    height: 3em;
				margin-left: 2%;
				margin-top: 2%;
		    line-height: 3em;
		    border: 1px solid #666;
      }

	</style>
	</head>
<body>

<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">

  <div class="row-fluid">


	<div class="row-fluid">

			<!-- 检索  -->
			<form action="dataReportController/findDataReport.do" method="post" name="dataReportForm" id="dataReportForm">
			<table>
				<tr>
				    <td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="name" value="${pd.name }"  placeholder="这里输入成菜名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td><input class="span10 date-picker" name="createTimeStart" id="createTimeStart"  value="${pd.createTimeStart}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始时间"/></td>
					<td><input class="span10 date-picker" name="createTimeEnd" id="createTimeEnd"  value="${pd.createTimeEnd}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束时间"/></td>
					
				
				 	<td style="vertical-align:top;background: #f5f5f5!important;margin-left: 0;display: inline-block;border: 1px solid #ccc;height: 27px;margin: 2px;vertical-align: middle;">
				 	    单份克重：
					 	<select  name="materialCompare" id="materialCompare" data-placeholder="请选择" style="vertical-align:top;width: 92px;margin-bottom: 1px;height: 27px;">
						<option value="">请选择</option>
						<c:forEach items="${listparam}" var="sen">
							<option value="${sen.paramName }" <c:if test="${pd.materialCompare==sen.paramName}">selected</c:if>>${sen.paramName }</option>
						</c:forEach>
					  	</select>
					  	<input autocomplete="off" id="singleWeight" type="text" name="singleWeight" value="${pd.singleWeight }"  placeholder="这里输入克重" style="width: 88px;height: 17px;" />/克
					</td>
				    
				    <td style="vertical-align:top;background: #f5f5f5!important;margin-left: 0;display: inline-block;border: 1px solid #ccc;height: 27px;margin: 2px;vertical-align: middle;">
				 	    消耗成本：
					 	<select  name="costCompare" id="costCompare" data-placeholder="请选择" style="vertical-align:top;width: 92px;height: 27px;">
						<option value="">请选择</option>
						<c:forEach items="${listparam}" var="sen">
							<option value="${sen.paramName }" <c:if test="${pd.costCompare==sen.paramName}">selected</c:if>>${sen.paramName }</option>
						</c:forEach>
					  	</select>
					  	<input autocomplete="off" id="totalCost" type="text" name="totalCost" value="${pd.totalCost }"  placeholder="这里输入成本" style="width: 88px;height: 17px;" />/元
					</td>
				
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" title="检索" id="subs"><i id="nav-search-icon" class="icon-search"></i></button></td>
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="window.location.href='<%=basePath%>/user/listtabUsers.do';" title="切换模式"><i id="nav-search-icon" class="icon-exchange"></i></a></td>
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					<c:if test="${QX.edit == 1 }"><td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i></a></td></c:if>
					</c:if>
				</tr>
			</table>
			<!-- 检索  -->


			<table id="table_report" class="table table-striped table-bordered table-hover">

				<thead>
					<tr>
					    <th>序号</th>
					    <th>成品材创建时间</th>
						<th>成品菜名称</th>
						<th>所属类别名称</th>
						<th>菜系名称</th>
						<th>单份克重</th>
						<th>单份成本</th>
						<th>总成本</th>
					</tr>
				</thead>

				<tbody>

				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty listproduct}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${listproduct}" var="material" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<%-- <td>${material.createTime}</td> --%>
								<td><fmt:formatDate value="${material.createTime}" pattern="yyyy年MM月dd日" /></td>
								<td>${material.name }</td>
								<td>${material.productCategoryName}</td>
								<td>${material.productStyleName }</td>
								<td>${material.singleWeight}</td>
								<td>${material.singleCost}</td>
								<td>${material.totalCost}</td>
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

	<!--提示框-->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
    
		
		<script type="text/javascript">
		
		
		
		$(top.hangge());
		
		$("#subs").on("click",function(){
			//top.jzts();
			var singleWeight = $("#singleWeight").val();
		 	var pattern = new RegExp("^\\-?\\d+\\.?\\d*$|^.{0}$");
			if(singleWeight.length>0){
					if(pattern.test(singleWeight)==false){
						$("#singleWeight").tips({
									side:3,
						            msg:'只能输入数字和.',
						            bg:'#AE81FF',
						            time:2
						        });
								$("#singleWeight").focus(); 
								return false;
					}
			}
			$("#dataReportForm").submit();
		
	    });
	
		
		//查看营养
		function checkNutr(mid){
			$.ajax({
				type :"POST",
				url: '<%=basePath%>materialController/findNuByMid.do?mid='+mid,
				dataType:'json',
				success: function(data){
					var msg = "";
					for(var i=0;i<data.length;i++){
						var nuname = data[i].name;
						var ncontent = data[i].content;
						var nunit = data[i].unit;

						msg +="<div class='nutr_show'>"+nuname+"---"+ncontent+"---"+nunit+"</div>";
					}
					 bootbox.alert(msg,function(result){});
				}
			});

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
			 diag.Drag=true;
			 diag.Title ="新增原料";
			 diag.URL = '<%=basePath%>materialController/addMaterial.do';
			 diag.Width = 500;
			 diag.Height = 550;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage('${page.currentPage}');
					 }
				}
				diag.close();
			 };
		//	 diag.OnLoad = function() {
		//	 	$(diag.innerDoc.getElementsByClassName('dwrap')).css('width', '276px');
		//	 }
			 diag.show();
		}

		//修改
		function editUser(mid){
			 top.jzts();
			 var diag6 = new top.Dialog();
			 diag6.Drag=true;
			 diag6.Title ="修改原料";
			 diag6.URL = '<%=basePath%>materialController/editMPage.do?mid='+mid;
			 diag6.Width = 500;
			 diag6.Height = 550;
			 diag6.CancelEvent = function(){ //关闭事件
				 if(diag6.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage('${page.currentPage}');
				}
				diag6.close();
			 };
			 diag6.show();
		}

		//删除
		function delUser(mId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>materialController/delMaterial.do?mid="+mId;
					$.get(url,function(data){
						nextPage('${page.currentPage}');
					});
				}
			});
		}

		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					var emstr = '';
					var phones = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;

						  	if(emstr=='') emstr += document.getElementsByName('ids')[i].id;
						  	else emstr += ';' + document.getElementsByName('ids')[i].id;

						  	if(phones=='') phones += document.getElementsByName('ids')[i].alt;
						  	else phones += ';' + document.getElementsByName('ids')[i].alt;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!",
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);

						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });

						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>user/deleteAllU.do?tm='+new Date().getTime(),
						    	data: {USER_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage('${page.currentPage}');
									 });
								}
							});
						}else if(msg == '确定要给选中的用户发送邮件吗?'){
							sendEmail(emstr);
						}else if(msg == '确定要给选中的用户发送短信吗?'){
							sendSms(phones);
						}


					}
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
			
			
		/* 	var USERNAME = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var ROLE_ID = $("#role_id").val(); */
		  //  return false;
			
			var USERNAME = $("#nav-search-input").val();
			var createTimeStart = $("#createTimeStart").val();
			var createTimeEnd = $("#createTimeEnd").val();
            var materialCompare = $("#materialCompare").val();
            var singleWeight = $("#singleWeight").val();
			
			window.location.href='<%=basePath%>dataReportController/excel.do?name='+USERNAME+'&createTimeStart='+createTimeStart+'&createTimeEnd='+createTimeEnd
					+'&materialCompare='+materialCompare+'&singleWeight='+singleWeight;
		}

		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>materialController/goUploadExcel.do';
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 //console.log(diag.innerFrame.contentWindow.document.getElementById('dataReportForm'));
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage('${page.currentPage}');
					 }
				}
				diag.close();
				search();
			 };
			 diag.show();
		}

		</script>

	</body>
</html>
