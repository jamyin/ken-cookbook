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
			<form action="materialController/findMaterial.do" method="post" name="materialForm" id="materialForm">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="name" value="${pd.name }"  placeholder="这里输入原料名称" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
				 	<td style="vertical-align:top;">
					 	<select class="chzn-select" name="isSensitiveMaterial" id="isSensitiveMaterial" data-placeholder="请选择是否敏感食材" style="vertical-align:top;width: 160px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listSen}" var="sen">
							<option value="${sen.paramId }" <c:if test="${pd.isSensitiveMaterial==sen.paramId}">selected</c:if>>${sen.paramName }</option>
						</c:forEach>
					  	</select>
					</td>
				    <td style="vertical-align:top;">
					 	<select class="chzn-select" name="bigCategoryId" id="bigCategoryId" data-placeholder="请选择原料大类" style="vertical-align:top;width: 160px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listMB}" var="bigCate">
							<option value="${bigCate.id }" <c:if test="${pd.bigCategoryId==bigCate.id}">selected</c:if>>${bigCate.name }</option>
						</c:forEach>
					  	</select>
					</td>
					<td style="vertical-align:top;">
					 	<select class="chzn-select" name="brandId" id="brandId" data-placeholder="请选择原料品牌" style="vertical-align:top;width: 160px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listbrand}" var="brand">
							<option value="${brand.id }" <c:if test="${pd.brandId==brand.id}">selected</c:if> >${brand.name }</option>
						</c:forEach>
					  	</select>
					</td>
					<td style="vertical-align:top;">
					 	<select class="chzn-select" name="type" id="type" data-placeholder="请选择原料类型" style="vertical-align:top;width: 160px;">
						<option value=""></option>
						<option value="">全部</option>
						<c:forEach items="${listMType}" var="mtype">
							<option value="${mtype.paramId }" <c:if test="${pd.type==mtype.paramId}">selected</c:if>>${mtype.paramName }</option>
						</c:forEach>
					  	</select>
					</td>
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
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
						<th>原料名称</th>
						<th>原料类型</th>
						<th>原料成本</th>
						<th>所属大类</th>
						<th>所属品牌</th>
						<th>是否敏感食材</th>
						<th>备注</th>
						<th>营养</th>
						<th class="center">操作</th>
					</tr>
				</thead>

				<tbody>

				<!-- 开始循环 -->
				<c:choose>
					<c:when test="${not empty listMaterial}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${listMaterial}" var="material" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${material.name }</td>
							<!--	<c:forEach items="${listMType}" var="mtype">
								  <c:if test="${mtype.paramId == material.type}"><td>${mtype.paramName}</td></c:if>
								</c:forEach>   -->
								<td>${material.typeName}</td>
								<td>${material.strcost }</td>
								<td>${material.bigCategoryName }</td>
								<td>${material.brandName}</td>
								<td>${material.isSensitiveName}</td>
								<td>${material.remark}</td>
								<td> <a onclick="checkNutr('${material.id }')">查看</a> </td>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<a class='btn btn-mini btn-info' title="编辑" onclick="editUser('${material.id }');"><i class='icon-edit'></i></a>
										<a class='btn btn-mini btn-danger' title="删除" onclick="delUser('${material.id }','${material.name}');"><i class='icon-trash'></i></a>
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
			$("#materialForm").submit();
		}

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
						var ncontent = data[i].contentstr;
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
			 diag.Width = 420;
			 diag.Height = 500;
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
			 diag6.Width = 440;
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
			//查询这个原料是否已经在成品菜中
			$.ajax({
				type :"POST",
				url: '<%=basePath%>materialController/findProductByMater.do?mid='+mId,
				dataType:'json',
				success: function(data1){
					if(data1==0){
						bootbox.alert("原料["+msg+"]已经在成品菜中使用,不能删除!");
						return false;
					}else{
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
			var name = $("#nav-search-input").val();
			var isSensitiveMaterial = $("#isSensitiveMaterial").val();
			var bigCategoryId = $("#bigCategoryId").val();
			var brandId = $("#brandId").val();
			var type = $("#type").val();
			window.location.href='<%=basePath%>materialController/excel.do?name='+name+
					'&isSensitiveMaterial='+isSensitiveMaterial+
					'&bigCategoryId='+bigCategoryId+
					'&brandId='+brandId+'&type='+type;
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
				 //console.log(diag.innerFrame.contentWindow.document.getElementById('materialForm'));
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
