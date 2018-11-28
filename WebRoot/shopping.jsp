<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="entity.Product"%>
<%@page import="dao.ShoppingDao"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">  


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<%User u=(User)session.getAttribute("user");%>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	 <div class="help">
	<%if(u==null){ %>
		<a href="login.jsp">登录</a>
		<a href="register.jsp">注册</a>
	<%}else{ %>
		<label style="color:red;">欢迎您:<%=u.getEu_user_id() %></label>
		<% 
			ShoppingDao sd=new ShoppingDao();
			int count=sd.getTotalCount(u.getEu_user_id());
		%>
		<%if(u.getEu_status()==1) {%>
		<a href="ShowShopGoods" id="shoppingBag" class="shopping">购物车<label style="color:red;"><%=count %></label>件</a>
		<%} %>
		<a class="button" id="logout" href="RemoveUserServ">注销</a>
		<a href="javascript:alert('当前您已登录，请注销后再注册......')">注册</a>
		<a href="SelectCommentServ">留言</a>
		<a href="manage/index.jsp">后台管理</a>
	<%} %>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
			<ul class="clearfix">
			<li class="first"><a href="PrintProductSer?id=1">年货主题馆</a></li>
			<li><a href="PrintProductSer?id=2">女装</a></li>
			<li><a href="PrintProductSer?id=3">男装</a></li>
			<li><a href="PrintProductSer?id=4">百货宠物</a></li>
			<li><a href="PrintProductSer?id=5">家电</a></li>
			<li><a href="PrintProductSer?id=6">家居</a></li>
			<li><a href="PrintProductSer?id=7">家装</a></li>
			<li><a href="PrintProductSer?id=8">美食</a></li>
			<li><a href="PrintProductSer?id=9">美妆</a></li>
			<li><a href="PrintProductSer?id=10">母婴用品</a></li>
			<li><a href="PrintProductSer?id=11">女鞋男鞋</a></li>
			<li><a href="PrintProductSer?id=12">手机</a></li>
			<li><a href="PrintProductSer?id=13">数码</a></li>
			<li><a href="PrintProductSer?id=14">童装玩具</a></li>
			<li><a href="PrintProductSer?id=15">运动户外</a></li>
			<li ><a href="PrintProductSer?id=16">珠宝配饰</a></li>
			<li ><a href="PrintProductSer?id=17">教育培训</a></li>
			<li class="last"><a href="PrintProductSer?id=18">图书音像</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="SelectAllProductSer">易买网</a> &gt; 购物车
</div>
<%List<Product> lists=(List<Product>)request.getAttribute("lists");%>
<div class="wrap">
	<div id="shopping">
		<form id="cartShoppings" action="CartSer" method="post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<%for(Product product:lists){ %>
				<tr id="product_id_0">
				
				     <input type="hidden"  name="productid" value="<%=product.getEp_id() %>"/>
					<td class="thumb"><img src="images/product/<%=product.getEp_file_name() %>"  width="100" height="100" />
					<a href="product-view.jsp?id=<%=product.getEp_id() %>"><%=product.getEp_name() %></a></td>
					
					<td class="price" id="price_id_0">
						<span>￥<%=product.getEp_price() %></span>
					
						<input type="hidden" name="price" value="<%=product.getEp_price() %>" />
					</td>
					
					<td class="number"><div style="display: none"><%=product.getEp_stock() %></div>
                        <span name="del">-</span>
                        <% 
                        	ShoppingDao sd=new ShoppingDao();
                        	int count=sd.getShopCount(product.getEp_id(),u.getEu_user_id());
                        %>
                        <input id="buyNum" type="text"  readonly="readonly" name="number" value="<%=count %>" />
                        <span name="add">+</span>
					</td>
					<td class="delete">
					库存:<br/><%=product.getEp_stock()%><br/>
					<a href="DeleteShopGoods?epid=<%=product.getEp_id() %>&euid=<%=u.getEu_user_id()%>">删除</a><br/>
					</td>
				</tr>
				<%} %>
			</table>
            <div class="total"><span id="total">总计：￥0</span></div>
            <input id="totalprice" type="hidden" name="totalprice" value=" "/>
			<div class="button"><input type="submit" value=""  /></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
