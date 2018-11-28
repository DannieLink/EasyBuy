<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="entity.ProductType"%>
<%@page import="dao.ProductTypeDao"%>
<%@page import="dao.NewsDao"%>
<%@page import="entity.News"%>
<%@page import="entity.User"%>
<%@page import="dao.ShoppingDao"%>
<%@page import="entity.Product"%>
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
<style type="text/css">
/* -- tab切换    但是没用呢，怎么搞都没用，索性不弄了 --*/
.tcurrent{ background:url(images/xuanting.png) right -30px no-repeat; }
.tcurrent a {color:#fff;  }
.tcommon{ background:url(images/xianshi.png) right -30px no-repeat;}
.tcommon  a {color:#105f4b; text-decoration:none;}
</style>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" ><!--
	//实现"新闻动态"滚动效果,循环垂直向上滚动
		function movedome(){
			var marginTop=0;
			var stop=false;
			var interval=setInterval(function(){
				if(stop) return;
				$("#express").children("li").first().animate({"margin-top":marginTop--},0,function(){
					var $first=$(this);
					if(!$first.is(":animated")){
						if((-marginTop)>$first.height()){
							$first.css({"margin-top":0}).appendTo($("#express"));
							marginTop=0;
						}
					}
				});
			},50);
			$("#express").mouseover(function(){
				stop=true;
			}).mouseout(function(){
				stop=false;
			});
		}
	//文档加载事件
    	$(function(){
    		  movedome();
    		  
    		  
		//tab切换    但是没用呢，怎么搞都没用，索性不弄了
			//流程
	//$("#tab_bg >li").hover(function(){
		//$(this).siblings('li').removeClass('tcommon');
		//	$(this).addClass('tcurrent');
	//});
    	})
</script>

</head>
<body>
<div id="welcomeImage">
    <img width="100%" height="550" src="images/001.jpg" alt="welcome">
</div>
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
	<div id="tab_bg" class="navbar">
		<ul class="clearfix">
			<li  class="current"><a href="#">首页</a></li>
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
					<dd>
					<a href="PrintProductSer?id=<%=pt.getEpc_id()%>"><%=pt.getEpc_name()%></a>
					</dd>
				<%}%>
			</dl>
		</div>
		
	</div>
	<div class="main">
		<div class="price-off">
            <div class="slideBox">
                <ul id="slideBox">
                    <li><a href="PrintProductSer?id=1"><img src="images/change/1.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=2"><img src="images/change/2.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=3"><img src="images/change/3.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=4"><img src="images/change/4.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=5"><img src="images/change/5.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=6"><img src="images/change/6.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=7"><img src="images/change/7.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=8"><img src="images/change/8.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=9"><img src="images/change/9.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=10"><img src="images/change/10.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=11"><img src="images/change/11.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=12"><img src="images/change/12.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=13"><img src="images/change/13.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=14"><img src="images/change/14.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=15"><img src="images/change/15.jpg" /></a></li>
                    <li><a href="PrintProductSer?id=16"><img src="images/change/16.jpg" /></a></li>
                </ul>
            </div>
			<h2>商品列表</h2>
			<ul class="product clearfix">
				<%ArrayList<Product> plist = (ArrayList<Product>)request.getAttribute("list"); %>
						<%for(Product pt:plist){%>
						<li>
							<dl>
								<dt><a href="product-view.jsp?id=<%=pt.getEp_id() %>" target="_self"><img src="images/product/<%=pt.getEp_file_name() %>" /></a></dt>
								<dd class="title"><a href="product-view.jsp?id=<%=pt.getEp_id() %>" target="_self"><%=pt.getEp_name() %></a></dd>
								<dd class="price"><%=pt.getEp_price() %></dd>
							</dl>
						</li>
				<%} %>
			</ul>
			<div class="pager">
				<ul class="clearfix">
					<li>[当前页数${pageBean.currentNo}/${pageBean.totalPages}] </li>
					<li><a href="SelectAllProductSer?pageNo=1">首页</a>&nbsp;</li>
					<li>
						<c:if test="${pageBean.currentNo!=1}">
						<a href="SelectAllProductSer?pageNo=${pageBean.currentNo-1}">上一页</a>&nbsp;
						</c:if>
						<c:if test="${pageBean.currentNo==1}">
						<a  href="javascript:alert('当前页已是第一页')">上一页</a>&nbsp;
						</c:if>
					</li>
					<li class="current">
						<c:if test="${pageBean.currentNo!=pageBean.totalPages}">
						<a href="SelectAllProductSer?pageNo=${pageBean.currentNo+1}">下一页</a>&nbsp;
						</c:if>
						<c:if test="${pageBean.currentNo==pageBean.totalPages}">
						<a  href="javascript:alert('当前页已是最后一页')">下一页</a>&nbsp;
						</c:if>
					</li>
					<li><a href="SelectAllProductSer?pageNo=${pageBean.totalPages}">末页</a>&nbsp;</li>
					<li>共${pageBean.totalCount} 条记录</li>
				</ul>
			</div>
		</div>
		
		<div class="side">			
			<div class="spacer"></div>
			<div class="news-list">
				<div class="news-list_bg"><img src="images/dd_book_mess.gif" alt="mess" style=" vertical-align:text-bottom;"/>新闻动态</div>
				<div class="news_class">
                	<div id="dome">
                   		<ul id="express">
							<%
							request.setCharacterEncoding("UTF-8");
						    response.setCharacterEncoding("UTF-8");
							NewsDao nd=new NewsDao();
						    List<News> news=nd.getAllNews();
						    if(news!=null){
							    for(News n:news){
						    %>
								<li><a href="SelectNewsServ?id=<%=n.getEn_id() %>"  target="_self"><%=n.getEn_title() %></a></li>
							<% }
						    }%>
						</ul>
					</div>
				</div>
		    </div>
			<div class="last-view">
				<h2>最近浏览</h2>
				<%
				Product pro=(Product)session.getAttribute("pro");
				if(pro!=null){
				%>
				<dl class="clearfix">
					<dt><a href="product-view.jsp?id=<%=pro.getEp_id() %>" target="_self"><img src="images/product/<%=pro.getEp_file_name() %>" width="50" height="56"  /></a></dt>
					<dd><a href="product-view.jsp?id=<%=pro.getEp_id() %>"  target="_self"><%=pro.getEp_name() %></a></dd>
			 	</dl>
			  <%}else {%>
			  	<dl class="clearfix">
			  		<dt>&nbsp;</dt>
			  		<dd>暂无浏览记录</dd>
			  	</dl>
			 <%} %> 
		  </div>
    </div>
    </div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
