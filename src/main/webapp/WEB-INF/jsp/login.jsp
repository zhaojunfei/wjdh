
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
<title>无标题文档</title>
<link rel="stylesheet" href="${ctx}/style/weui.css">
<link rel="stylesheet" href="${ctx}/style/weui-extend.css">
<link rel="stylesheet" href="${ctx}/style/jquery-weui.css">
<script src="${ctx}/js/jquery-2.1.4.js"></script>
<script src="${ctx}/js/jquery-weui.js"></script>
<script src="${ctx}/js/zepto.min.js"></script>
    <style>
      .swiper-container {
        width: 100%;
      } 

      .swiper-container img {
        display: block;
        width: 100%;
      }
		.weui_navbar_item{
			position: relative;
			display: block;
			-webkit-box-flex: 1;
			-webkit-flex: 1;
			flex: 1;
			padding: 6px;
			text-align: center;
			font-size: 15px;
			/* align-items: center; */
			line-height: 35px;
			-webkit-tap-highlight-color: rgba(0,0,0,0);
		}
		.weui_navbar_item::after{
			content: "";
			position: absolute;
			right: 0;
			top: 0;
			width: 1px;
			bottom: 0;
			border-right: 1px solid #CCCCCC;
			-webkit-transform-origin: 100% 0;
			transform-origin: 100% 0;
			-webkit-transform: scaleX(.5);
			transform: scaleX(.5);
		}
		.weui_navbar_item  
		.weui_bar_item_on {
			color: #2196F3;
			transition: all 0.5s ease-in;
		}
    </style>
</head>

<body ontouchstart>

    <div class="swiper-container">
      <!-- Additional required wrapper -->
      <div class="swiper-wrapper">
        <!-- Slides -->
        <div class="swiper-slide"><img src="${ctx}/images/swiper-1.jpg" /></div>
        <div class="swiper-slide"><img src="${ctx}/images/swiper-2.jpg" /></div>
        <div class="swiper-slide"><img src="${ctx}/images/swiper-3.jpg" /></div>
      </div>
      <!-- If we need pagination -->
      <div class="swiper-pagination"></div>
    </div>

 <div class="weui_tab">
      <div class="weui_navbar">
        <div  href="#tab1" class="weui_navbar_item weui_bar_item_on">
          个人注册
        </div>
        <div  href="#tab2" class="weui_navbar_item">
          企业注册
        </div>
        
      </div>
      <div class="weui_tab_bd">
		   <div id="tab1" class="weui_tab_bd_item weui_tab_bd_item_active">
				<form id="form"  action="bindUser">
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_hd"><label class="weui_label">手机号</label></div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="tel" id="mobile" name="mobile"  required pattern="[0-9]{11}" maxlength="11" placeholder="输入你现在的手机号" emptyTips="请输入手机号" notMatchTips="请输入正确的手机号">
							</div>
							<div class="weui_cell_ft">
								<i class="weui_icon_warn"></i>
							</div>
						</div>

						<div class="weui_cell">
							<div class="weui_cell_hd"><label class="weui_label">密码</label></div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="password" required placeholder="输入你密码" emptyTips="请输入密码" notMatchTips="请输入密码">
							</div>
							<div class="weui_cell_ft">
								<i class="weui_icon_warn"></i>
							</div>
						</div>
						<div class="weui_cell weui_vcode" style="height: 44px;">
							<div class="weui_cell_hd"><label class="weui_label">验证码</label></div>
							<div class="weui_cell_bd weui_cell_primary">
								<input class="weui_input" type="number" name="validateCode" required 
									   placeholder="点击验证码更换" tips="请输入验证码">
							</div>
							<div class="weui_cell_ft">
								<i class="weui_icon_warn"></i>
								<input type="button" class="weui_btn bg-blue weui_btn_mini  " value="点击发送验证码" onclick="sendCode(this)" />
							</div>
						</div>
					</div>
					<div class="weui_btn_area">
						<a id="formSubmitBtn" href="javascript:" class="weui_btn weui_btn_primary bg-blue">提交</a>
					</div>
				</form>
			</div>

			 <div id="tab2" class="weui_tab_bd_item">
                            我的........
                        </div>
      </div>


</div>


<script>
var $form = $("#form");
$("#formSubmitBtn").on("click", function(){
    $form.validate(function(error){
        if(error){
        }else{
            $.toptips('验证通过提交','ok');
        }
    });
    
});
var clock = '';
var nums = 10;
var btn;
function sendCode(thisBtn)
{ 
    var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
   
    if(!reg.test($("#mobile").val())){  
        $.toptip('请输入正确的手机号', 'error');
        return false;  
    }
	btn = thisBtn;
	btn.disabled = true; //将按钮置为不可点击
	btn.value = nums+'秒后可重新获取';
	clock = setInterval(doLoop, 1000); //一秒执行一次
	$.ajax({  
        type : 'POST',  
        url : 'sendMsg',  
        dataType : 'json',  
        data : {'mobile':$("#mobile").val()},  
        success : function(data) {  
            if(data.responseCode=='00000000'){  
            	$.toptip('发送成功', 'success');
            }else{  
            	$.toptip('发送失败', 'error');
            }  
        }  
    });  
}
function doLoop()
{
	nums--;
	if(nums > 0){
	 btn.value = nums+'秒后可重新获取';
	}else{
	 clearInterval(clock); //清除js定时器
	 btn.disabled = false;
	 btn.value = '点击发送验证码';
	 nums = 120; //重置时间
}
}
</script>



  </body>
</html>
