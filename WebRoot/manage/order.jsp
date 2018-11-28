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
<script type="text/javascript" >
	$(function(){
			//订单号验证，只能为数字
	    $("#orderForm").submit(function(){
	    	var flag =true;
	    	var reg=/^\d*$/;
	    	var uInput=$("#orderId").val();
	       if(!reg.test(uInput)){
	    	   flag=false;
	           alert("订单号只能是数字！");
	       }
	        return flag;
	    });
	
	
	})

</script>
 

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
		<h2>订单管理</h2>
		<div class="manage">
					<div class="search">
							<!--<form  id="orderForm"  method="post" action="OrderManagerSer?type=likeSearch" >
							-->
							<form  id="orderForm"  method="post" action="OrderManagerSer?type=likeSearch" >
								订单号：<input type="text" class="text" name="orderId" id="orderId" /> 
								<c:if test="${user.eu_status==1}">订货人：<input  readonly="readonly" type="text" class="text" name="userName" id="userName" value="${user.eu_user_id }"/> </c:if>
								<c:if test="${user.eu_status==2}">订货人：<input type="text" class="text" name="userName" id="userName" /> </c:if>
								<label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
							</form>			
					</div>
					<div class="spacer"></div>
						<table class="list">
						<tr>
							<th>订单ID</th>
							<th>姓名</th>
							<th>订单时间</th>
							<th>金额</th>
							<th>订单状态</th>
							<th>操作</th>
						</tr>
						<c:forEach var="o" items="${pageBean.list}" >
						<tr>
							<td class="first w4 c"><a href="OrderDetailManagerSer?type=searchByEo_Id&id=${o.eo_id }">${o.eo_id }</a></td><!-- 根据订单id查询订单详情 -->
							<td class="w1 c">${o.eu_user_id }</td>
							<td class="w1 c">${o.eo_create_time }</td>
							<td class="w1 c">${o.eo_cost }</td>
							<c:if test="${o.eo_status == 1}">
							<td class="w1 c">未发货</td>
							</c:if>
							<c:if test="${o.eo_status == 2}">
							<td class="w1 c">已发货</td>
							</c:if>
							<c:if test="${o.eo_status == 3}">
							<td class="w1 c">交易成功</td>
							</c:if>
							<td class="w1 c">
								<c:if test="${user.eu_status==1}">
									<c:if test="${o.eo_status == 1}"><a href="javascript:alert('已成功提醒卖家发货.....');">提醒发货</a></c:if>
									<c:if test="${o.eo_status == 2}"><a href="OrderManagerSer?type=update&id=${o.eo_id }&status=3">确认收货</a></c:if>
									<c:if test="${o.eo_status == 3}"><a href="OrderManagerSer?type=del&id=${o.eo_id}">删除</a></c:if>
								</c:if>
								<c:if test="${user.eu_status==2}">
									<c:if test="${o.eo_status == 1}"><a href="OrderManagerSer?type=update&id=${o.eo_id}&status=2">发货</a></c:if>
									<c:if test="${o.eo_status == 2}"><a href="javascript:alert('买家还没收货，请耐心等待.....');">等待收货</a></c:if>
									<c:if test="${o.eo_status == 3}"><a href="OrderManagerSer?type=del&id=${o.eo_id}">删除</a></c:if>
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</table>
			<div class="pager">
				<ul class="clearfix">
					<li>[当前页数${pageBean.currentNo}/${pageBean.totalPages}] </li>
					<li><a href="OrderManagerSer?type=search&pageNo=1">首页</a>&nbsp;</li>
					<li>
						<c:if test="${pageBean.currentNo!=1}">
						<a href="OrderManagerSer?type=search&pageNo=${pageBean.currentNo-1}">上一页</a>&nbsp;
						</c:if>
						<c:if test="${pageBean.currentNo==1}">
						<a href="javascript:alert('当前页已是第一页')">上一页</a>&nbsp;
						</c:if>
					</li>
					
					<li>
						<c:if test="${pageBean.currentNo!=pageBean.totalPages}">
						<a href="OrderManagerSer?type=search&pageNo=${pageBean.currentNo+1}">下一页</a>&nbsp;
						</c:if>
						<c:if test="${pageBean.currentNo==pageBean.totalPages}">
						<a href="javascript:alert('当前页已是最后一页')">下一页</a>&nbsp;
						</c:if>
					</li>
					<li><a href="OrderManagerSer?type=search&pageNo=${pageBean.totalPages}">末页</a>&nbsp;</li>
					<li>共${pageBean.totalCount} 条记录</li>
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
