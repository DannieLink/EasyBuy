<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.Comment"%>
<%@page import="entity.PageBean"%>
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
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
				PageBean<Comment> pb=(PageBean<Comment>)(request.getAttribute("pageBean"));
				List<Comment> coms=pb.getList();
				for(Comment c:coms){
				 	request.setAttribute("c",c);
				 %>
				
				<tr>
					<td class="first w4 c"><%=c.getEc_id() %></td>
					<td class="w1 c"><%=c.getEc_nick_name() %></td>
					<td><%=c.getEc_content() %></td>
					<td class="w1 c">
					<% if(c.getEc_reply()==null){
					out.print("未回复");
					}else{
					out.print("已回复");
					}
					 %></td>
					<td class="w1 c">
					<c:if test="${not empty c.ec_reply}">
						<a href="" > &nbsp; </a>
					</c:if>
 					<c:if test="${ empty c.ec_reply}">
 						<c:if test="${ user.eu_status==2}">
 							<a href="UpdateComment?id=<%=c.getEc_id() %>" > 回复 </a>
 						</c:if>
					</c:if>
					<a class="manageDel" href="DeleteComment?id=<%=c.getEc_id() %>">删除</a></td>
				</tr>
				 <%} %>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li>[当前页数<%=pb.getCurrentNo() %>/<%=pb.getTotalPages() %>]</li>
					<li><a href="SelectCommentServ?type=sub&pageNo=1" >首页</a></li>
					<%
					 if(pb.getCurrentNo()!=1){
					 %>
					<li><a href="SelectCommentServ?type=sub&pageNo=<%=pb.getCurrentNo()-1 %>" >上一页</a></li>
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
                    <li><a href="SelectCommentServ?type=sub&pageNo=<%=pb.getCurrentNo()+1 %>" >下一页</a></li>
                    <%} %>
					<li><a href="SelectCommentServ?type=sub&pageNo=<%=pb.getTotalPages() %>" >末页</a></li>
					<li>共<%=pb.getTotalCount() %> 条记录</li>
				</ul>
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
