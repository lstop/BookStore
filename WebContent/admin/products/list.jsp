<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/list.js"></script>
<script>
window.onload=function() {
	var t = "<%=request.getParameter("category")%>";
	var category = document.getElementById("search_category");

	var ops = category.options;
	for ( var i = 0; i < ops.length; i++) {
		if (ops[i].value == t) {
			ops[i].selected = true;
			return;
		}
	}
};
</script>
<script>
function addProduct() {
	window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
} 
$(document).ready(function(){
	var now = 1;
	
	function send(){
		$.ajax({
			url : "DividePageServlet",
			type : "post",
			data : {
				page : now,
				id : $("#search_id").val(),
				category : $("#search_category").val(),
				name : $("#search_name").val(),
				minprice : $("#minprice").val(),
				maxprice : $("#maxprice").val()
			},
			success : function(data){
				if(null != data){
					var s = JSON.parse(data);
					for (var i = 0; i < s.length; i++) {
						$("#tr"+i).show();
	                	 //每一项都是json对象
						var s1 = s[i];
	                	 //点表示调用get方法，点后跟的是get方法中含有的成员变量名，只是get后字母的首字母都是小写
						//alert(s1.name);
						// alert($("#tr"+i).attr("name"));
						$("#tr"+i+" .check").prop("id",s1.id);
						$("#tr"+i+" .check").prop("checked",false);
						$(".checkedall").prop("checked",false);
						$("#tr"+i+" #shopid").text(s1.id);
						$("#tr"+i+" #shopname").text(s1.name);
						$("#tr"+i+" #shopimgurl").prop("src",s1.imgurl);
						$("#tr"+i+" #shopprice").text(s1.price);
						$("#tr"+i+" #shoppnum").text(s1.pnum);
						$("#tr"+i+" #shopcategory").text(s1.category);
						var string1 = "${pageContext.request.contextPath}/admin/products/edit.jsp?id="+s1.id+"&name="+s1.name+"&imgurl="+s1.imgurl+"&price="+s1.price+"&category="+s1.category+"&pnum="+s1.pnum+"&description="+s1.description;
						$("#tr"+i+" #shopedit").prop("href",string1);
						var string2 = "${pageContext.request.contextPath}/DeleteOneServlet?id="+s1.id;
						$("#tr"+i+" #shopdel").prop("href",string2);
					}
					if(s.length<5){
						for(var i = s.length;i<5;i++){
							$("#tr"+i).hide();
						}
					}
				}
			}
		});
		
	};
	function onactive(){
		$("#"+now).css({'background-color':'#337ab7'});
		$("#"+now).css({'color':'#fff'});
	};
	function onleave(){
		$("#"+now).css({'background-color':'buttonface'});
		$("#"+now).css({'color':'buttontext'});
	};
	onactive();
	$("#last").click(function(){
		onleave();
		if(now>1)
			now--;
		onactive();
		send();
	});
	$("#next").click(function(){
		onleave();
		if(now<"<%=request.getAttribute("size")%>")
			now++;
		onactive();
		send();
	});
	$(".button_page").click(function(){
		onleave();
		now = Number($(this).attr("id"));
		onactive();
		send();
	});
});
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/SearchServlet" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品编号</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="search_id" size="15" value="${id}" id="search_id" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01"> 类别：</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="search_category" id="search_category">
											<option value="" selected="selected">--选择商品类加--</option>
											<option value="文学">文学</option>
											<option value="生活">生活</option>
											<option value="计算机">计算机</option>
											<option value="外语">外语</option>
											<option value="经营">经营</option>
											<option value="励志">励志</option>
											<option value="社科">社科</option>
											<option value="学术">学术</option>
											<option value="少儿">少儿</option>
											<option value="艺术">艺术</option>
											<option value="原版">原版</option>
											<option value="科技">科技</option>
											<option value="考试">考试</option>
											<option value="生活百科">生活百科</option>
									</select>
								</td>
							</tr>
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01"> 商品名称：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="search_name" size="15" value="${name}" id="search_name" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="minprice" id="minprice" size="10" value="${minprice}" /> - 
									<input type="text" name="maxprice" id="maxprice" size="10" value="${maxprice}" />
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br>
									<br>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit"  name="search" value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;
									</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="&#37325;&#32622;" class="button_view" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>商品列表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="float: right;" id="del">
						<button type="button" id="deletesome" name="deletesome" value="批量删除" class="button_add">批量删除</button>
					</td>
					<td class="ta_01" style="float: right;" id="addtd">
						<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">添加</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1" style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="8%">
									<input class="checkedall" type="checkbox"/> 全选/反选
								</td>
								<td align="center" width="20%">商品编号</td>
								<td align="center" width="14%">商品名称</td>
								<td align="center" width="6%">商品图片</td>
								<td align="center" width="9%">商品价格</td>
								<td align="center" width="9%">商品数量</td>
								<td width="8%" align="center">商品类别</td>
								<td width="8%" align="center">编辑</td>
								<td width="8%" align="center">删除</td>
							</tr>
							<%int i = 0; %>
							<c:forEach var="shop" items="${list}"> 
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';" id="tr<%=i%>" name="h">
									<%i++; %>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="23" in="shopcheck">
										<input id="${shop.id}" class="check" type="checkbox"/>
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" class="ids" width="23" id="shopid">${shop.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%" id="shopname">${shop.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="6%" >
										<img src="${shop.imgurl }" id="shopimgurl" width="40px" height="60px" alt="图片加载失败">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%" id="shopprice">${shop.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%" id="shoppnum">${shop.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" id="shopcategory">${shop.category }</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a id="shopedit"  href="${pageContext.request.contextPath}/admin/products/edit.jsp?id=${shop.id}&name=${shop.name}&imgurl=${shop.imgurl}&price=${shop.price}&category=${shop.category}&pnum=${shop.pnum}&description=${shop.description}">
											<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0" style="CURSOR: hand"> 
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%" ">
										<a id="shopdel"  href="${pageContext.request.contextPath}/DeleteOneServlet?id=${shop.id}">
											<img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
	<form action="${pageContext.request.contextPath}/ShopListServlet" method="post">
	<div align="center">
		<button type="button" id="last" name="last" value="上一页" class="button_last">上一页</button>
		<button type="button" id="pre5" name="pre5" value="<<" class="button_pre5"><<</button>
		<c:forEach var="page" begin="1" end="${size}" step="1">
			<button type="button" id="${page}" name="page" value="${page}" class="button_page">${page}</button>
		</c:forEach>
		<button type="button" id="next5" name="next5" value=">>" class="button_next5">>></button>
		<button type="button" id="next" name="next" value="下一页" class="button_next">下一页</button>
	</div>
	</form>
</body>
</HTML>

