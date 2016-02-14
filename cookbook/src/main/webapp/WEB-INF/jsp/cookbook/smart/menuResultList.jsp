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
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	</script>
	<style type="text/css">
		.content{
			display:none;
		}
		#data_ul{
                padding-left: 1em;
            }
            #data_ul ul{
            float: left;
                display: inline-block;
                list-style-type: none;
                width: 130px;
                border: 1px solid #ccc;
                margin-left: 0;
                border-bottom: none;
            }
           #data_ul > ul > li{
                border-bottom: 1px solid #ccc;
                text-indent: 0.5em;
                text-align:center;
                height: 2em;
                line-height: 2em;
            }
	</style>
	</head>
	<body>
		<ul id="m" class="nav nav-tabs"> </ul>
		<div class="contents" id="contents">
			<div class="content" id="content1"></div>
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
		
		<script type="text/javascript">
			$(function(){
				var pid = '${pid}';
				var lunchCount = ${lunchCount};
				var dinnerCount = ${dinnerCount};
				//start
				$.ajax({
					type: "POST",
					url: 'smart/QueryFixingsResult.do',
					data: {"pid":pid,"lunchCount":lunchCount,"dinnerCount":dinnerCount},
					dataType:'json',
					cache: false,
					success: function(data){
						$('#m').empty();
						var myObject = data;
						var index = 1;
						for(x in myObject) {
							var key = x;
							var value = myObject[x];
							renderContent(key, value, index++);
						}
					},
				 	complete:function(){ 
				 		$("#m li").click(function(){
							$(this).siblings("li").removeClass("active");
							$(this).addClass("active");
							
							$("#content"+ $(this).attr("name")).siblings(".content").hide();
							$("#content"+ $(this).attr("name")).show();
						});
				 		
					 	$("#m li:first").trigger("click");
				 	}
				});
				//end
			});
			
			function renderContent(k, v, i) {
				renderKey(k, i);
				renderValue(v, i);
			}
			
			function renderKey(k, i) {
				var li = $('<li name='+i+'>');
				var anchor = $('<a>');
				anchor.text(k);
				$('#m').append(li);
				$(li).append(anchor);
				var div = $('<div class="content" id="content'+i+'">');
				$('#contents').append(div);
				$('#m').append('<a style="line-height:39px;" href="javascript:void(0)">查看所有>></a>');
			}
			
			function renderValue(v, i) {
				console.log(v);
				renderLunch(v['mmap'], i);//午饭
				renderDinner(v['amap'], i);//晚饭
				renderNutrition(v['map'],i);//包含营养
				renderResult(v['map2'],i);//原料总消耗
				renderCostTotal(v,i);//成本消耗
			}
			//动态拼接营养
			function renderNutrition(v,i){
				var content = $('#content'+i);
				content.append('<h4>包含营养:</h4>');
				var p = $('<p>');
				for(x in v){
					var str = "";
					if(x != ""){
						str += v[x]["name"] + "&nbsp;:&nbsp;" + v[x]["content"] +"&nbsp;"+ v[x]["unit"] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					}
					p.append(str);
				}
				content.append(p);
				content.append($('<hr style="clear:both;">'));
			}
			//动态拼接原料消耗
			function renderResult(v,i){
				var content = $('#content'+i);
				content.append('<h4>原料统计结果:</h4>');
				var p = $('<p>');
				for(x in v){
					var str = "";
					if(x != ""){
						str += v[x]["name"] + "&nbsp;:&nbsp;" + v[x]["mweight"] + "克" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					}
					p.append(str);
				}
				content.append(p);
				content.append($('<hr style="clear:both;">'));
			}
			//动态拼接成本消耗
			function renderCostTotal(v,i){
				var content = $('#content'+i);
				content.append('<h4>成本:</h4>');
				var p = $('<div>');
				p.append('<div class="uhu">'+
						'<strong>单份成本&nbsp;:&nbsp;</strong><span>'+v["singleCost"]+'元/份</span>&nbsp;&nbsp;'+
						'<strong>单份定价&nbsp;:&nbsp;</strong><span>'+v["eachPricing"]+'元/份</span></div>'+
						'<div class="uhu"><br>'+
						'<strong>总成本&nbsp;:&nbsp;</strong><span>'+v["totalsingleCost"]+'元/份</span>&nbsp;&nbsp;'+
						'<strong>总定价&nbsp;:&nbsp;</strong><span>'+v["totaleachPricing"]+'元/份</span>'+
						'</div>');
				content.append(p);
				content.append($('<hr style="clear:both;">'));
			}
			
			//动态拼接午饭
			function renderLunch(v, i) {
				var content = $('#content'+i);
				content.append('<h4>中饭</h4>');
				renderDataUl(content, v)
			}
			//动态拼接晚饭
			function renderDinner(v, i) {
				var content = $('#content'+i);
				content.append('<h4>晚饭</h4>');
				renderDataUl(content, v);
			}
			//午饭和晚饭公共拼接方法			
			function renderDataUl(content, v) {
				var innerContent = $('<div id="data_ul">');
				var maxLen = 0;
				for(x in v) {
					if(v[x].length>maxLen) {
						maxLen = v[x].length;
					}
				}
				for(x in v) {
					var ul = $('<ul>');
					var li = $('<li>');
					li.text(x);
					ul.append(li);
					for(var i=0;i<maxLen;i++) {
						var innerLi = $('<li>');
						innerLi.text(v[x][i]||'');
						ul.append(innerLi);
					}
					innerContent.append(ul);
				}
				content.append(innerContent);
				content.append($('<hr style="clear:both;">'));
			}
		</script>
	</body>
</html>