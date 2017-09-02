
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="${ctx}/style/weui.css">
<link rel="stylesheet" href="${ctx}/style/weui-extend.css">
<link rel="stylesheet" href="${ctx}/style/jquery-weui.css">
<script src="${ctx}/js/jquery-2.1.4.js"></script>
<script src="${ctx}/js/jquery-weui.js"></script>
<script src="${ctx}/js/zepto.min.js"></script>
  </head>

 <body ontouchstart>

<form id="form"  method="post">
    
    <div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_hd"><img src="${ctx}/images/ji.jpg" style="margin-right:5px;display:block"/></div>
		 
				<div class="weui_cell_bd weui_cell_primary">
					<input name="sourceName" id="sourceName" type="hidden"/>
					<input name="sourceTel" id="sourceTel" type="hidden"/>
					<input name="sourceAddress" id="sourceAddress" type="hidden"/>
					 <div class="weui-row" id="source_msg_show">

					 </div>
					 <div class="weui-row" id="source_address_show">

					 </div>
				</div>
		
		<div class="weui_cell_hd"><img src="${ctx}/images/bianji.jpg" id="source" style="margin-right:5px;display:block"/></div>
      </div>

	
	<div class="weui_cell">
        <div class="weui_cell_hd"><img src="${ctx}/images/shou.jpg" style="margin-right:5px;display:block"/></div>
		 
				<div class="weui_cell_bd weui_cell_primary">
					<input name="tarName" id="tarName" type="hidden"/>
					<input name="tarTel" id="tarTel" type="hidden"/>
					<input name="tarAddress" id="tarAddress" type="hidden"/>
					 <div class="weui-row" id="tar_msg_show">

					 </div>
					 <div class="weui-row" id="tar_address_show">

					 </div>
				</div>
		
		<div class="weui_cell_hd"><img src="${ctx}/images/bianji.jpg" id="tar" style="margin-right:5px;display:block"/></div>
      </div>


</div>
	
	<div class="weui_cells weui_cells_form">
      <div class="weui_cell">
        <div class="weui_cell_hd"><label for="time" class="weui_label">取件时间</label></div>
        <div class="weui_cell_bd weui_cell_primary">
         <input class="weui_input" type="text" name="reqTime" id='buy_time'/>
        </div>
      </div>
    </div>


	<div class="weui_cells weui_cells_form">
		<div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">商品名称</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="text" name="name" required placeholder="请输入商品名称">
        </div>
      </div>

	  <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">体积</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="text" name="tiji" required placeholder="请输入体积">
        </div>
		<div class="weui_cell_hd">立方米</div>
      </div>

	   <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">重量</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="text" name="zhongliang" required  placeholder="请输入重量">
        </div>
			<div class="weui_cell_hd">kg</div>
      </div>

	  <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">包装方式</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input class="weui_input" type="text" name="pack" id="buy_bzfs" required placeholder="请选择包装方式">
        </div>
      </div>
   
	 <div class="weui_cells_tips">底部说明文字底部说明文字</div>
      <div class="weui_btn_area">
        <a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips">确定</a>
      </div>
	</div>
</form>

    <script>



var gloabText = '    <div class="weui_cells weui_cells_form"> '+
	  '<div class="weui_cell">'+
       ' <div class="weui_cell_hd"><label class="weui_label">姓名</label></div>'+
       ' <div class="weui_cell_bd weui_cell_primary">'+
       '   <input class="weui_input" id="name" type="text" placeholder="请输入姓名">'+
       ' </div>'+
      '</div>'+

	   '<div class="weui_cell">'+
       ' <div class="weui_cell_hd"><label class="weui_label">联系方式</label></div>'+
       ' <div class="weui_cell_bd weui_cell_primary">'+
       '   <input class="weui_input" id="tel" type="text" placeholder="请输入联系方式">'+
       ' </div>'+
      '</div>'+

      '<div class="weui_cell">'+
      '  <div class="weui_cell_hd"><label class="weui_label">联系地址</label></div>'+
      '  <div class="weui_cell_bd weui_cell_primary">'+
      '    <input class="weui_input" type="text" id="address" placeholder="请输入联系地址">'+
      '  </div>'+
      '</div>'+

    '</div>';
	$(document).ready(function(){


$(document).on("click", "#source", function() {
        $.modal({
          title: "添加联系人",
          text: gloabText
,
          buttons: [
            { text: "确定", onClick: function(){ 
					var res = $("#name").val() + "|" + $("#tel").val() + "|" + $("#address").val();

					if (res != false) {  
								   var values = res.split('|');
								   if(values.length!=3){
											 $.toptip('请检查联系人信息是否有误', 'error');
								   }else{
									    if(values[0]==""||values[1]==""||values[2]==""){
											 $.toptip('请检查联系人信息是否有误', 'error');
										}else{
											$.toptip('操作成功', 'success');
											$("#source_msg_show").html(values[0] + " " + values[1]);
											$("#source_address_show").html(values[2] );
											$("#sourceName").val(values[0]);
											$("#sourceTel").val(values[1]);
											$("#sourceAddress").val(values[2]);
											
										}
								   }
								}  

			} 
			},
            { text: "取消", className: "default"},
          ]
        });
      });


$(document).on("click", "#tar", function() {
        $.modal({
          title: "Hello",
          text:gloabText
,
          buttons: [
            { text: "确定", onClick: function(){ 
					var res = $("#name").val() + "|" + $("#tel").val() + "|" + $("#address").val();

					if (res != false) {  
								   var values = res.split('|');
								   if(values.length!=3){
											 $.toptip('请检查联系人信息是否有误', 'error');
								   }else{
									    if(values[0]==""||values[1]==""||values[2]==""){
											 $.toptip('请检查联系人信息是否有误', 'error');
										}else{
											$.toptip('操作成功', 'success');
											$("#tar_msg_show").html(values[0] + " " + values[1]);
											$("#tar_address_show").html(values[2] );
											$("#tarName").val(values[0]);
											$("#tarTel").val(values[1]);
											$("#tarAddress").val(values[2]);
											
										}
								   }
								}  

			} 
			},
            { text: "取消", className: "default"},
          ]
        });
      });

		  

		  $("#showTooltips").click(function() {
			  $("#form").validate(function(error){
			        if(error){
			        }else{
			        	
			        	$.ajax({  
			                type : 'POST',  
			                url : 'create',  
			                dataType : 'json',  
			                data : $("#form").serialize(),  
			                success : function(data) {  
			                    if(data.responseCode=='00000000'){  
			                    	$.toptip('发送成功', 'success');
			                    }else{  
			                    	$.toptip('发送失败', data.responseMsg);
			                    }  
			                }  
			            });  
			        }
			    });
		  });


		   $("#buy_bzfs").select({
			title: "选择包装方式",
			items: [
			  {
				title: "要爬楼哦",
				value: "1",
			  },
			  {
				title: "没包装袋",
				value: "2",
			  },
			  {
				title: "提前电话我",
				value: "3",
			  },
			  {
				title: "没有纸箱",
				value: "4",
			  },
			  {
				title: "带胶带哦",
				value: "5",
			  },
			  {
				title: "么么哒！",
				value: "6",
			  }
			]
		  });
		$("#buy_time").datetimePicker();
	});
</script>
  </body>
</html>
