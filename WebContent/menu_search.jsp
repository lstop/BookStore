<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/my.js">
	
</script>
<script type="text/javascript">
<!--
	function fillNameValue(subDiv) {
		document.getElementById("name").value = subDiv.innerHTML;
		
		document.getElementById("content").style.display="none";
	}

	function searchName() {
		var nameElement = document.getElementById("name");
		//获取输入的信息
		var nameValue = nameElement.value;

		var div = document.getElementById("content");
		div.innerHTML = "";
		//1.获取XMLHttpRequest对象
		var xmlhttp = getXMLHttpRequest();

		//2.绑定回调函数
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var jsonObj = eval("(" + xmlhttp.responseText + ")");
				
				if(jsonObj.length>0){
					document.getElementById("content").style.display="block";
					for ( var i = 0; i < jsonObj.length; i++) {
						div.innerHTML += "<div onclick='fillNameValue(this)' onmouseover='changeBackground_over(this)' onmouseout='changeBackground_out(this)'>"
								+ jsonObj[i] + "</div>"
					}
				}

			}
		};
		//3.open
		xmlhttp.open("GET",
				"${pageContext.request.contextPath}/findProductName?name="
						+ window.encodeURIComponent(nameValue, "utf-8")
						+ "&time=" + new Date().getTime());
		//4.send
		xmlhttp.send(null);
	};
	
	function changeBackground_over(div){
		div.style.background="gray";
	}
	
	function changeBackground_out(div){
		div.style.background="white";
	}
//-->
</script>

<div id="divmenu">
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("文学", "utf-8")%>">文学</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("生活", "utf-8")%>">生活</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("计算机", "utf-8")%>">计算机</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("外语", "utf-8")%>">外语</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("经营", "utf-8")%>">经管</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("励志", "utf-8")%>">励志</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("社科", "utf-8")%>">社科</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("学术", "utf-8")%>">学术</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("少儿", "utf-8")%>">少儿</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("艺术", "utf-8")%>">艺术</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("原版", "utf-8")%>">原版</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("科技", "utf-8")%>">科技</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("考试", "utf-8")%>">考试</a>
	<a
		href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=<%=URLEncoder.encode("生活百科", "utf-8")%>">生活百科</a>
	<a href="${pageContext.request.contextPath}/ShowProductByPageServlet"
		style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/findProductBySearch"
		method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">
				Search <input
					type="text" name="name" class="inputtable" onkeyup="searchName();"
					id="name" /> 
					<input type="image" src="images/serchbutton.gif"
					border="0" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>

	</form>
</div>
<div id="content"
	style="background-color:white;width:128px; text-align:left;margin-left:945px;color:black;float:left;margin-top: -20px;display: none">
</div>