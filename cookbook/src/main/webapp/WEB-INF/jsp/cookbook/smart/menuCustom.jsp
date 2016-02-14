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
		<title></title>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		
	<script type="text/javascript">
	
		$(top.hangge());

	</script>
	</head>
	<body>
		<input type="hidden" id="fixingsMasterId" value="${masterID}" />
		
		<!-- 该div用于展示下拉框 -->
		<div id="to_show" style="display:none;">
			
			<select id="category_show_1">
				<c:forEach items="${menuTypelist}" var="menuType" varStatus="vs">
				<option value="${menuType.id}">${menuType.name}</option>
				</c:forEach>
			</select>
			<select id="category_show_bak_1">
				<c:forEach items="${menuTypelist}" var="menuType" varStatus="vs">
				<option value="${menuType.id}">${menuType.name}</option>
				</c:forEach>
			</select>
			
			<select id="pid_show">
				<c:forEach items="${productList}" var="product" varStatus="vs">
					<option value="${product.id}">${product.name}</option>
				</c:forEach>
			</select>
			
			<select id="pid_show_bak">
			</select>
		
			<select id="pid_show_1">
				<c:forEach items="${productList}" var="product" varStatus="vs">
					<option value="${product.id}">${product.name}</option>
				</c:forEach>
			</select>
			
			<select id="pid_show_bak_1">
			</select>
			
		</div>
		
		<!-- 该div用于循环添加成品菜 -->
		<div id="to_add_product" style="display:none;">
			<div class="control-group">
			<div class="controls">
			   <select class="input-xlarge chengcai" onchange="checkProRep(this)" style="width:150px">
					<option value=""> ---请选择--- </option>
					<c:forEach items="${productList}" var="product" varStatus="vs">
						<option value="${product.id}">${product.name}</option>
					</c:forEach>
			 </select>
			 <a class='btn' onclick='deleteProEles(this)'> <i class='icon-minus'></i></a>
			</div>
			</div>
		</div>
		
		<!-- 该div用户循环添加大类 -->
		<div id="to_add_category" style="display:none;">
			<!-- <fieldset class="fieldsetid"> -->
			    <div class="control-group">
					<label class="control-label">大类</label>
			          <div class="controls">
			            <select class="input-xlarge dalei" style="width:150px" onchange="checkCateRep(this)">
			            	<!-- <option value=""> ---请选择--- </option> -->
					        <c:forEach items="${menuTypelist}" var="menuType" varStatus="vs">
								<option value="${menuType.id}">${menuType.name}</option>
							</c:forEach>
					    </select>
					    <a class='btn' onclick='deleteCateEles(this)'> <i class='icon-minus'></i></a>
			          </div>
			     </div>
			
			    <div class="control-group">
			          <label class="control-label"><a class="btn" onclick="toAddProduct(this)">增加成品菜<i class="icon-plus"></i></a></label>
			          <div class="controls">
			            <select class="input-xlarge chengcai" onchange="checkProRep(this)" name='pids' style="width:150px">
						   <option value=""> ---请选择--- </option>
						   <c:forEach items="${productList}" var="product" varStatus="vs">
								<option value="${product.id}">${product.name}</option>
							</c:forEach>
					     </select>
			          </div>
			     </div>
			
			    <div class="control-group">
			          <!-- Search input-->
			          <div class="controls">
			          	<!-- 因为没用到这里的dom，所以name='price'就不写了，一面造成程序异常 -->
			            <p>该品类成本:<input type="text" class="price" placeholder="该品类成本:50.0" disabled="disabled"></p>
			          </div>
			     </div>
			     <div id="legend" class="">
			        <legend class=""></legend>
			     </div>
		  <!--   </fieldset> -->
		
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="row-fluid">
						<div class="span12" >
							  <form class=".form-inline">
							  <div id="legend" class="">
							          <legend class="">自主配菜</legend>
							   </div>
							   <div id="addType">
							   		<label class="control-label"><a class="btn" onclick="toAddMenu(this);">增加新品类<i class="icon-plus"></i></a></label>
								    <fieldset id="addCatePro_1" class="fieldsetClas">
									    <div class="control-group">
											<label class="control-label">大类</label>
									          <!-- Select Basic -->
									          <div class="controls">
									            <select id="category_1" name="bcids" class="input-xlarge dalei" style="width:150px" onchange="checkCateRep(this)">
											      <option value=""> ---请选择--- </option>
											      <c:forEach items="${menuTypelist}" var="menuType" varStatus="vs">
														<option value="${menuType.id}">${menuType.name}</option>
													</c:forEach>
											    </select>
									          </div>
									     </div>
									
									    <div class="control-group">
									          <!-- Select Basic -->
									          <label class="control-label"><a class="btn" onclick="toAddProduct(this)">增加成品菜<i class="icon-plus"></i></a></label>
									          <div class="controls">
									            <select id="pid_1" name="pids" class="input-xlarge chengcai" onchange="checkProRep(this)" style="width:150px">
												   <option value=""> ---请选择--- </option>
												   <c:forEach items="${productList}" var="product" varStatus="vs">
														<option value="${product.id}">${product.name}</option>
													</c:forEach>
											     </select>
									          </div>
									     </div>
									
									    <div class="control-group">
									          <!-- Search input-->
									          <div class="controls">
									            <p>该品类成本:<input type="text" id="price" name="price" class="price" placeholder="该品类成本:50.0" disabled="disabled"></p>
									          </div>
									     </div>
									     <div id="legend" class="">
									        <legend class=""></legend>
									     </div>
								    </fieldset>
							    </div>
							    <fieldset>
									    <div class="control-group">
											<label class="control-label">日期选择</label>
									          <!-- Select Basic -->
									          <div class="controls">
									            <input class="span10 date-picker" name="fixingsTimeVO" id="fixingsTimeVO" value="${pd.fixingsStartTime}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="配餐日期"/>
									            <%-- -
									            <input class="span10 date-picker" name="fixingsEndTime" id="fixingsEndTime" value="${pd.fixingsEndTime}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期"/> --%>
									          </div>
									     </div>
								 </fieldset>
								 
								 <fieldset>
									    <div class="control-group">
											<label class="control-label">所需份数</label>
									          <!-- Select Basic -->
									          <div class="controls">
										          <input type='checkbox' id='lunch' name='lunchCountSelect' />中饭：<input type="text" name="lunchCount" id="lunchCount" value="${pd.lunchCount}" placeholder="数量" title="数量" style="width:44px;"/>份</br>
										          <input type='checkbox' id='dinner' name='dinnerCountSelect' />晚饭：<input type="text" name="dinnerCount" id="dinnerCount" value="${pd.dinnerCount}" placeholder="数量" title="数量" style="width:44px;"/>份
									          </div>
									     </div>
								 </fieldset>
								 
								 <fieldset>
									<a class="btn" onclick="sendData();">提&nbsp交</a>  
									<a class="btn"  id="myclose">关闭页面</a>
								 </fieldset>
							    
							  </form>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
						</div>
					</div>
				</div>
			</div>
		</div>
	
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
	</body>
	
<script type="text/javascript">
//-------------  全局变量  start ----------------

var i=1; // 控制categroy循环
//-------------  全局变量     end  ----------------


//-------------大类相关逻辑  start------------------

/**
 * 添加大类
 */
function toAddMenu(obj){
	
	// 新增dom元素，并且新增保存成品菜的show data 和show data bak下拉框
	i++;
	var rpID = "addCatePro_"+i;
	var preffix = "<fieldset id='"+rpID+"' class='fieldsetClas'>"
	var suffix = "</fieldset>";
	//var divs = $("#to_add_category").html();
	
	var firstHtm = "<div class='control-group'><label class='control-label'>大类</label>";
	firstHtm += "<div class='controls'>";
	firstHtm += "<select class='input-xlarge dalei' style='width:150px' name='bcids' onchange='checkCateRep(this)'>";
	firstHtm += "<option value=''> ---请选择--- </option>";
	firstHtm += $("#category_show_1").html();
	firstHtm += "</select>";
	firstHtm += "<a class='btn' onclick='deleteCateEles(this)'> <i class='icon-minus'></i></a>";
	firstHtm += "</div></div>";	
	
	var secondHtm = "<div class='control-group'>";
	secondHtm += ($("#to_add_category").children("div").eq(1)).html();
	secondHtm += "</div>";	
	
	var thirdHtm = "<div class='control-group'>";
	thirdHtm += "<div class='controls'>";
	thirdHtm +=	" <p>该品类成本:<input type='text' class='price' name='price' disabled='disabled' placeholder='该品类成本:50.0'></p>";
	thirdHtm += "</div></div>";
     
    var fourthHtm = "<div id='legend' class=''><legend class=''></legend></div>"
	
	var divs = firstHtm+secondHtm+thirdHtm+fourthHtm;
	// 组装大类
	$("#addType").append(preffix+divs+suffix);
	
	var showID = "pid_show_"+i;
	var showBakID = "pid_show_bak_"+1;
	var showHtm = $("#pid_show").html();
	var showBakHtm = $("#pid_show_bak").html(); // 实际上里面什么都没有
	
	$("#to_show").append("<select id='"+showID+"'>"+showHtm+"</select>");
	$("#to_show").append("<select id='"+showBakID+"'>"+showBakHtm+"</select>");
}

/**
 * 检查大类是否已经被选中
 */
function checkCateRep(obj){
	
	var selecteV = $(obj).val();
	// 因为大类处于最上级，共用一个show data 和show data bak
	var cates = getOptsVals("category_show_1");
	var isExi = isExist(selecteV,cates);
	
	if(!isExi){
		alert("已被选中，请选择其他~");
		$(obj).html("<option value=''> ---请选择--- </option>"+$("#category_show_1").html());
	}else{
		
		var opt = $("#category_show_1 option[value='"+selecteV+"']");
		var text = opt.text();
		opt.remove();
		$("#category_show_bak_1").append("<option value='"+selecteV+"'>"+text+"</option>");
	}
}

/**
 * 删除大类（包括对应的成菜下拉框）
 */
function deleteCateEles(obj){
	
	var selected = $(obj).val();
	var text = $(obj).text();
	//删除dom并将选中的大类释放掉
	var cate = $(obj).parent().parent().parent();
	$("#category_show_1").append("<option value='"+selected+"'>"+text+"</option>");
	$("category_show_bak_1 option[value='"+selected+"']").remove();
	cate.remove();
}


//-------------大类相关逻辑         end------------------


//-------------成品菜相关逻辑  start------------------

/**
 * 添加成品菜下拉框
 */
function toAddProduct(obj){
	
	var div = $(obj).parent().parent();
	// 根节点第id
	var rpID = $(obj).parent().parent().parent().attr("id");
	var temp = rpID.split("_");
	var number = temp[temp.length-1];
	var innerHTM = $("#pid_show_"+number).html();
	if(innerHTM=="null" || innerHTM==null){
		alert("没有了~");
	}else{
		div.append("<div class='controls'><select class='input-xlarge chengcai' onchange='checkProRep(this)' style='width:150px'><option value=''> ---请选择--- </option>"+innerHTM+"</select><a class='btn' onclick='deleteProEles(this)'> <i class='icon-minus'></i></a></div>");
	}
}

/**
 * 删除陈品菜下拉框obj
 */
function deleteProEles(obj){

	var selectedV = $(obj).val();
	if(selectedV != ""){
		// 将选中的元素从对应的show data bak中删除，添加到对应的show data中
		var rpID = $(obj).parent().parent().parent().attr("id");
		var temp = reID.split("_");
		var number = temp[temp.length-1];
		
		//从对应的show data bak中删除
		var bak = $("#pid_show_bak_"+number+" option[value='"+selectedV+"']");
		var text = bak.text();
		bak.remove();
		// 添加到对应的show data中
		$("#pid_show_"+number).append("<option value='"+selectedV+"'>"+text+"</option>");
	}
	$(obj).parent().remove();
}

/**
 * 根据innerHTM组装成品菜下拉框
 */
function assebleProSele(innerHTM){

	var htmx = innerHTM + suffix;
	return htmx;
}

/**
 * 验证该select对象所选中的值是否已被其他下拉框选中
 */
function checkProRep(obj){
	
	var selectedV = $(obj).val();
	// 根据fieldset的属性id的序号判断showdata的下拉框
	// 根节点 fieldset的id
	var rparent = $(obj).parent().parent().parent();
	var rparentId = rparent.attr("id");
	// 切分根节点id，获取序号
	var seriNum = rparentId.split("_");
	var number = seriNum[seriNum.length - 1];
	// 根据序号获取对应的showdata下拉框的options values
	var showID = "pid_show_"+number;
	var cateV = rparent.children().find(" .dalei").val();
	if((cateV=="") || (cateV==null) || (cateV=="undefined")){
		
		alert("请选择成品菜大类~");
		$(obj).html("<option value=''> ---请选择--- </option>"+$("#"+showID).html());
		return false;
	}else{
		
		var pids = getOptsVals(showID);
		// 验证选项是否被选中:0表示被选中，1表示没被选中
		var isExi = isExist(selectedV, pids);
		// 如果已被选中，则提示已被选中；否则选中，并从show data 下拉框中删除相应option,添加到相应的show data bak中
		if(!isExi){
			alert("该项已被选中，请选择其他~");
			$(obj).html("<option value=''> ---请选择--- </option>"+$("#"+showID).html());
		}else{
			
			var opt = $("#"+showID+" option[value='" + selectedV + "']");
			var text = opt.text();
			opt.remove()
			$("#pid_show_bak_"+number).append("<option value='"+selectedV+"'>"+text+"</option>");
			// 计算选中的成品菜的成本价
			var pids = "";
			$(obj).parent().parent().children().find("select.chengcai").each(function(){
				
				pids += ($(this).val()+",");
			});
			//-------ajax start ---------
			$.ajax({
				type: "POST",
				url: '<%=basePath%>smart/getCostSum.do',
				data: {"pids":pids},
				dataType:'json',
				cache: false,
				success: function(data){
					if((data != "") && (data != null)){
						$(obj).parent().parent().parent().children().find(".price").val(data);
					}else{
						alert("查询成本价失败~");
					}
				}
			});
			
			//-------ajax end------------
		}
	}
}

//-------------成品菜相关逻辑  end------------------

//----------------公用方法 start---------------

/**
 * 获取下拉框所有的options values
 */
function getOptsVals(selId){
	
	  var vastr = $("#"+selId+" option").map(function(){return $(this).val();}).get().join(",")
	  return vastr.split(",");
}

/**
 * 验证数据是否存在于数组中
 */
function isExist(val, arr){
	
	var flag = 0;
	var len = arr.length;
	for(var i=0; i<len; i++){
		if(arr[i]==val){
			flag=1;
			break;
		}
	}
	return flag;
}

//日期框
$('.date-picker').datepicker();
//-----------------公用方法 end-----------------

//-----------------提交数据 start--------------------

function sendData(){
	
	var bcids = "";
	var pids = "";
	var prices = "";
	
	$(".fieldsetClas").each(function(k){
		
		// 循环获取被选中的大类
		var fsetObj = $(this);
		var tempBcid = fsetObj.find(" .dalei").val();
		bcids += (tempBcid + ";"); 
		
		//循环遍历被选中的成品菜
		fsetObj.children().find(" .chengcai").each(function(m){
			var tempPid = $(this).val();
			pids += (tempPid + ";");
		});
		pids += ",";
		
		// 循环遍历每一组被选中的大类、成品菜的成品价
		var tempPrice = fsetObj.find(".price").val();
		if((tempPrice=="") || (tempPrice == null)){
			
			alert("请填写成本价~");
			return false;
		}
		prices += (tempPrice + ";");
	});
	
	var fixingsMasterId = $('#fixingsMasterId').val();
	var fixingsTimeVO = $("#fixingsTimeVO").val();
	if(fixingsTimeVO=="" || fixingsTimeVO==null){
		alert("请填写配餐日期");
		return false;
	}
	
	var lunchCount;
	var dinnerCount;
	var lunch = $("#lunch").is(':checked');
	var dinner = $("#dinner").is(':checked');
	var footType = "";
	if(lunch){
		footType +="1,";
		lunchCount = $("#lunchCount").val();
		if(lunchCount=="" || lunchCount==null){
			alert("请填写午餐数量");
			return false;
		}
	}
	if(dinner){
		footType+="2,";
		dinnerCount = $("#dinnerCount").val();
		if(dinnerCount=="" || dinnerCount==null){
			alert("请填写晚餐数量");
			return false;
		}
	}
	
	if(!(dinner || lunch)){
		
		alert("请选择午餐还是晚餐");
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: 'smart/save.do',
		data: {"fixingsMasterId":fixingsMasterId,"fixingsTimeVO":fixingsTimeVO,"lunchCount":lunchCount,"dinnerCount":dinnerCount,"bcidsVO":bcids,"pidsVO":pids,"priceVO":prices,"foodType":footType},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status == 200){
				
				
				 var id = data.data.rid;
				 var lunchCount = data.data.lunchCount;
				 var dinnerCount = data.data.dinnerCount;
				 var choose_diag = new top.Dialog();
				 choose_diag.Drag=true;
				 choose_diag.ID="product_display";
				 choose_diag.Title ="成品菜";
				 choose_diag.URL = '<%=basePath%>smart/menuResultListPage.do?pid=' + id + '&lunchCount=' + lunchCount + '&dinnerCount='+dinnerCount;
				 choose_diag.Width = 535;
				 choose_diag.Height = 585;
				 choose_diag.CancelEvent = function(){ //关闭事件
		            //先关闭二级弹出框，再关闭一级弹出框;
				 		choose_diag.close();
				    	top.Dialog.close();
					 };
					choose_diag.show();
			}else{
				alert("新增失败");
			}
		}
	});
}


//-----------------提交数据  end--------------------

</script>
</html>