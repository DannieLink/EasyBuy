<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="dao.ProductTypeDao"%>
<%@page import="entity.ProductType"%>
<%@page import="entity.User"%>
<%@page import="dao.ProductDao"%>
<%@page import="entity.Product"%>
<%@page import="dao.ShoppingDao"%>
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
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" >
	$(function(){
		//location.reload();
	})
</script>
</head>

<body>

<div id="header" class="wrap">
		<%request.setCharacterEncoding("UTF-8"); %>
		<%response.setCharacterEncoding("UTF-8"); %>
	<div id="logo"><img src="images/logo.gif" /></div>
	<%
	User u=(User)session.getAttribute("user");
    %>
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

	<%
		ProductDao pd = new ProductDao();
		//商品id
		int id = Integer.parseInt(request.getParameter("id")); 
	%>
	<%
		Product pro=pd.getProductById(id);
		//将浏览的商品放入session中，显示最近浏览
		session.setAttribute("pro",pro);
		ProductTypeDao ptd=new ProductTypeDao();
		ProductType ptype=(ProductType)session.getAttribute("ptype");
		session.removeAttribute("ptype");
		if(ptype!=null){
	 %>
	您现在的位置：<a href="SelectAllProductSer">易买网</a> &gt; <a href="PrintProductSer?id=<%=ptype.getEpc_id()%>"><%=ptype.getEpc_name()%></a> &gt;<%=pro.getEp_name()%>
	<% }else
	{
	ProductType ptype2=ptd.getType(pro.getEpc_Id());
	%>
	您现在的位置：<a href="SelectAllProductSer">易买网</a> &gt; <a href="PrintProductSer?id=<%=ptype2.getEpc_id()%>"><%=ptype2.getEpc_name()%></a> &gt;<%=pro.getEp_name()%>
	<%}%>
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<%
			
			ArrayList<ProductType> list = ptd.getAllProductType();
			%>
			<dl>
				<%for(ProductType pt:list){%>
					<dt></dt>
					<dd><a href="PrintProductSer?id=<%=pt.getEpc_id() %>"><%=pt.getEpc_name() %></a></dd>
				<%} %>
			</dl>
		</div>
	</div>
	<div id="product" class="main">
		
		<h1><%=pro.getEp_name() %></h1>
		<div class="infos">
			<div class="thumb"><img src="images/product/<%=pro.getEp_file_name() %>" width="110" height="106" /></div>
			<div class="buy">
				商城价：<span class="price"><%=pro.getEp_price() %></span><br/>
				<%if(pro.getEp_stock()==0){
				%>
					库　存：无货
				<%}else{%>
				库　存：<%=pro.getEp_stock() %>
				 	 <%
					 //如果是管理员，则无法进行购买和购物车功能
				 	if(u!=null&&u.getEu_status()==1){
				 	 %>
					  	<div class="button">
						  <input type="button" name="button" value="" onclick="location.href='OrderManagerSer?type=buy&id=<%=id %>'" />
						  <a href="AddShoppingSer?epid=<%=pro.getEp_id()%>" >放入购物车</a>
					  	</div>
				  	<% }else if(u==null){%>
				  	<div class="button">
					  <input type="button" name="button" value="" onclick="location.href='OrderManagerSer?type=buy&id=<%=id %>'" />
					  <a href="AddShoppingSer?epid=<%=pro.getEp_id()%>" >放入购物车</a>
				  	</div>
				  	<% }%>
			  	<%} %>
		   	</div>
			<div class="clear"></div>
		</div>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				<%=pro.getEp_description() %>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
