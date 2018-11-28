<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.PageBean"%>
<%@page import="entity.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h2>订单明细</h2>
		<div class="manage">
					<div class="spacer"></div>
						<table class="list" >
						<tr>
							<th>订单ID</th>
							<th>商品</th>
							<th>数量</th>
							<th>金额</th>
						</tr>
						<c:forEach var="od" items="${pageBean.list}" >
						<tr>
							<!--<td class="first w4 c"><input type="hidden"  name="id" value="${od.eod_id }"/></td>
							-->
							<td class="w1 c">${od.eo_id }</td>
							<td  width="600"><img src="images/product/${od.ep_file_name }"  style="vertical-align:middle"  width="100"  height="100"/><a href="product-view.jsp?id=${od.ep_id}">${od.ep_name }</a></td>
							<td class="w1 c">${od.eod_quantity }</td>
							<td class="w1 c">${od.eod_cost }</td>
						</tr>
						</c:forEach>
					</table>
			<div class="pager">
				<ul class="clearfix">
				    <!--如果是按照订单号查询的订单详情，进入第一个if  -->
					<c:if test="${mess=='searchByEo_Id'}">
						<li>[当前页数${pageBean.currentNo}/${pageBean.totalPages}] </li>
						<li><a href="OrderDetailManagerSer?type=searchByEo_Id&pageNo=1&id=${id}">首页</a>&nbsp;</li>
						<li>
							<c:if test="${pageBean.currentNo!=1}">
							<a href="OrderDetailManagerSer?type=searchByEo_Id&pageNo=${pageBean.currentNo-1}&id=${id}">上一页</a>&nbsp;
							</c:if>
							<c:if test="${pageBean.currentNo==1}">
							<a href="javascript:alert('当前页已是第一页')">上一页</a>&nbsp;
							</c:if>
						</li>
						<li>
							<c:if test="${pageBean.currentNo!=pageBean.totalPages}">
							<a href="OrderDetailManagerSer?type=searchByEo_Id&pageNo=${pageBean.currentNo+1}&id=${id}">下一页</a>&nbsp;
							</c:if>
							<c:if test="${pageBean.currentNo==pageBean.totalPages}">
							<a href="javascript:alert('当前页已是最后一页')">下一页</a>&nbsp;
							</c:if>
					  </li>
					  <li><a href="OrderDetailManagerSer?type=searchByEo_Id&pageNo=${pageBean.totalPages}&id=${id}">末页</a>&nbsp;</li>
					  <li>共${pageBean.totalCount} 条记录</li>
					</c:if>
					
					  <!--如果是按照用户id查询的订单详情，进入第二个if  -->
					<c:if test="${mess==null}">
						<li>[当前页数${pageBean.currentNo}/${pageBean.totalPages}] </li>
						<li><a href="OrderDetailManagerSer?type=search&pageNo=1">首页</a>&nbsp;</li>
						<li>
							<c:if test="${pageBean.currentNo!=1}">
							<a href="OrderDetailManagerSer?type=search&pageNo=${pageBean.currentNo-1}">上一页</a>&nbsp;
							</c:if>
							<c:if test="${pageBean.currentNo==1}">
							<a href="javascript:alert('当前页已是第一页')">上一页</a>&nbsp;
							</c:if>
					   </li>
					
						<li>
							<c:if test="${pageBean.currentNo!=pageBean.totalPages}">
							<a href="OrderDetailManagerSer?type=search&pageNo=${pageBean.currentNo+1}">下一页</a>&nbsp;
							</c:if>
							<c:if test="${pageBean.currentNo==pageBean.totalPages}">
							<a href="javascript:alert('当前页已是最后一页')">下一页</a>&nbsp;
							</c:if>
					   </li>
					   <li><a href="OrderDetailManagerSer?type=search&pageNo=${pageBean.totalPages}">末页</a>&nbsp;</li>
					   <li>共${pageBean.totalCount} 条记录</li>
					</c:if>
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
