<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="dao.ProductTypeDao"%>
<%@page import="entity.ProductType"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="SelectAllProductSer">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="SelectAllProductSer">首页</a></li>
			<li><a href="SearchUserById">用户</a></li>
			<li>
			<c:if test="${user.eu_status==2}"><a href="SelectProductBackSer">商品</a></c:if>
			</li>
			<li><a href="OrderManagerSer?type=search">订单</a></li>
			<li><a href="SelectCommentServ?type=sub">留言</a></li>
			<li>
			<c:if test="${user.eu_status==2}"><a href="SelectNewsServ?type=sub">新闻</a></c:if>
			</li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		<c:if test="${user.eu_status==2}">管理员</c:if>&nbsp;${user.eu_user_id}&nbsp;&nbsp;您好，今天是${strCurrentTime}，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="SelectAllProductSer">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
			<c:if test="${user.eu_status==2}">
				<dt>用户管理</dt>
				<dd><em><a href="manage/chongzhi.jsp">充值</a></em><a href="SearchUserById">用户管理</a></dd>
			  	<dt>商品信息</dt>
				<dd><em><a href="manage/productClass-add.jsp">新增</a></em><a href="TypeManageSer">分类管理</a></dd>
				<dd><em><a href="manage/product-add.jsp">新增</a></em><a href="SelectProductBackSer">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><em><a href="OrderDetailManagerSer?type=search">订单明细</a></em><a href="OrderManagerSer?type=search">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="SelectCommentServ?type=sub">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="manage/news-add.jsp">新增</a></em><a href="SelectNewsServ?type=sub">新闻管理</a></dd>
			</c:if>
			<c:if test="${user.eu_status==1}">
				<dt>用户管理</dt>
				<dd><em><a href="manage/chongzhi.jsp">充值</a></em><a href="SearchUserById">用户管理</a></dd>
				<dt>订单管理</dt>
				<dd><em><a href="OrderDetailManagerSer?type=search">订单明细</a></em><a href="OrderManagerSer?type=search">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="SelectCommentServ?type=sub">留言管理</a></dd>
			</c:if>
			<c:if test="${user==null}">
			<%out.print("<script>alert('请先登录......');location.href='login.jsp';</script>") ;%>
			</c:if>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>添加商品</h2>
		<div class="manage">
			<form id="productAdd" action="AddProductSer" method="post" enctype="multipart/form-data">
				<table class="form">
					<tr>
						<td class="field">商品名称(*)：</td>
						<td><input type="text" class="text" name="productName"  /><span></span></td>
					</tr>
                    <tr>
						<td class="field">描述：</td>
						<td><input type="text" class="text" name="productDetail" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
						<%request.setCharacterEncoding("UTF-8"); %>
						<%response.setCharacterEncoding("UTF-8"); %>
						<%
						ProductTypeDao ptd = new ProductTypeDao();
						ArrayList<ProductType> list = ptd.getAllProductType();
						%>
							<select name="parentId">
							<%for(ProductType pt:list){%>
								<option value="<%=pt.getEpc_id() %>"><%=pt.getEpc_name() %></option>
							<%} %>
							
							</select>
						</td>
					</tr>					
					<tr>
						<td class="field">商品价格(*)：</td>
						<td><input type="text" class="text tiny" name="productPrice" /> 元<span></span></td>
					</tr>
					
					<tr>
						<td class="field">库存(*)：</td>
						<td><input type="text" class="text tiny" name="productNumber" /><span></span></td>
					</tr>
					<tr>
						<td class="field">商品图片(*)：</td>
						<td><input type="file" class="text" name="photo" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="确定" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
