<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/admin/css/left.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="12"></td>
		</tr>
	</table>
	<table width="100%" border="0">
		<tr>
			<td>
				<div class="dtree">

					<a href="javascript: d.openAll();">展开所有</a> | <a
						href="javascript: d.closeAll();">关闭所有</a>
					<link rel="StyleSheet"
						href="${pageContext.request.contextPath}/admin/css/dtree.css"
						type="text/css" />
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/admin/js/dtree.js"></script>
					<script type="text/javascript">
					   /* 1.创建一个名称d的树结构 */
						d = new dTree('d');
					    /* 2.创建的树的根,参数1：当前结点的id标识，参数2：父级结点(-1) 参数3：该结点的名称 */
						d.add(0, -1, '系统菜单树');
					    /* 参数1：： 当前结点号1， 参数2： 父节点是0  参数3：该结点的名称  参数4：该结点对应的内容  
					          参数6：表示点击当前结点，内容要显示的位置，根据frame的name属性来定位*/
						d.add(1,0,'商品管理','${pageContext.request.contextPath}/admin/login/welcome.jsp','', 'mainFrame');
						
					    //子目录添加
						d.add(11,1,'商品查看','${pageContext.request.contextPath}/ShopListServlet','', 'mainFrame');
						
					    d.add(2, 0, '订单管理', '', '', 'mainFrame');

						//子目录添加
						d.add(22, 2, '订单列表', '', '', 'mainFrame');

						d.add(3, 0, '用户管理', '', '', 'mainFrame');

						//子目录添加
						d.add(33, 3, '用户列表', '', '', 'mainFrame');

						document.write(d);
					</script>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
