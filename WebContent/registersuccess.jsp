<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/my.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		url : "UserStatusServlet",
		type : "post",
		data : {
			username : $("#goactive").attr("name")
		},
		success : function(data){
			if("success" == data){
				$("#goactive").hide();
				$("#goto").show();
				var num = 5;
				function toimg() {
					$("#second").text(num--);
					if (num<0) {
						window.location.href = "login.jsp";
					}
				};
				var time = setInterval(toimg, 1000);
			}
		}
	});
});
</script>
</head>

<body class="main">

	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="menu_search.jsp" />



	<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center"><table width="60%"
						border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="width:98"><img
								src="images/IconTexto_WebDev_009.jpg" width="128" height="128" />
							</td>
							<td style="padding-top:30px;">
								<font style="font-weight:bold; color:#FF0000" id="goactive" name="<%=request.getParameter("username")%>">注册成功,去邮箱激活帐户</font>
							</td>
							<td style="padding-top:30px;">
								<a href="login.jsp" id="goto" style="display: none;">激活成功<span id="second">5</span>秒后自动为您转跳回登陆界面</a>
							</td>
						</tr>
					</table>
					<h1>&nbsp;</h1></td>
			</tr>
		</table>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%;"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px;" /></td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a></td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2008 &copy; eShop All Rights RESERVED.</b> </font></td>
			</tr>
		</table>
	</div>


</body>
</html>
