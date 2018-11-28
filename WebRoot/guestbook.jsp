<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.Comment"%>
<%@page import="dao.ProductTypeDao"%>
<%@page import="entity.ProductType"%>
<%@page import="entity.User"%>
<%@page import="entity.PageBean"%>
<%@page import="dao.ShoppingDao"%>
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
</head>
<body>
<div id="header" class="wrap">
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
	您现在的位置：<a href="SelectAllProductSer">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<%request.setCharacterEncoding("UTF-8"); %>
			<%response.setCharacterEncoding("UTF-8"); %>
			<%
			ProductTypeDao ptd = new ProductTypeDao();
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
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
				<%
				PageBean<Comment> pb=(PageBean<Comment>)(request.getAttribute("pageBean"));
				List<Comment> coms=pb.getList();
				for(Comment c:coms){
				 %>
				<li>
					<dl>
						<dt><%=c.getEc_content() %></dt>
						<dd class="author">网友：<%=c.getEc_nick_name() %><span class="timer"><%=c.getEc_create_time() %></span></dd>
						<dd>回复：
						<% if(c.getEc_reply()!=null){
						   out.print(c.getEc_reply());
						}else{
						   out.print("未回复");
						}
						 %>
						 </dd>
					</dl>
				</li>
				<%}%>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li>[当前页数<%=pb.getCurrentNo() %>/<%=pb.getTotalPages() %>]</li>
					<li><a href="SelectCommentServ?pageNo=1" >首页</a></li>
					 <%
					 if(pb.getCurrentNo()!=1){
					 %>
					<li><a href="SelectCommentServ?pageNo=<%=pb.getCurrentNo()-1 %>" >上一页</a></li>
					<%} %>
					 <%if(pb.getCurrentNo()==1){ %>
					<li><a  href="javascript:alert('当前页已是第一页')">上一页</a></li>
					 <%} %>
					<%if(pb.getCurrentNo()==pb.getTotalPages()){ %>
                    <li><a href="javascript:alert('当前页已是最后一页')">下一页</a></li>
                   <%} %>
					<%
					 if(pb.getCurrentNo()!=pb.getTotalPages()){
					 %>
                    <li><a href="SelectCommentServ?pageNo=<%=pb.getCurrentNo()+1 %>" >下一页</a></li>
                    <%} %>
					<li><a href="SelectCommentServ?pageNo=<%=pb.getTotalPages() %>" >末页</a></li>
					<li>共<%=pb.getTotalCount() %> 条记录</li>
				</ul>
			</div>
			<div id="reply-box">
				<form id="guestBook" action="AddCommentServ"  method="post">
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" disabled="disabled" value="${user.eu_user_name }"/></td>
						</tr>						
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea><span></span></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
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
