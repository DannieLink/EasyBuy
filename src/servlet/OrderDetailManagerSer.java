package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailKZ;
import entity.PageBean;
import entity.User;

public class OrderDetailManagerSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderDetailManagerSer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * get处理方式
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * post处理方式
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailDao odd=new OrderDetailDao();
		OrderDao od=new OrderDao();
		
		//获得登录用户对象
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('您掉线啦，请重新登录......');location.href='index.jsp';</script>");
		}else
		{
			//根据超链接获得的操作类型执行不同的操作
			String type=request.getParameter("type");
			
			//页面显示数量
			int pageSize =2;
			//初始页面
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			//根据类型执行操作
			if("search".equals(type)){
				//默认以用户id查询订单明细
				ArrayList<OrderDetailKZ> list=null;
				int count=0;
				//判断是普通用户还是管理员
				//普通用户查询自己的订单明细，管理员查询所有的
				if(u.getEu_status()==1){
					list=odd.getInfoByUser_id(pageNo, pageSize,u.getEu_user_id());
					count=odd.getCount(null,u.getEu_user_id());
				}else
				{
					list=odd.getAllInfo(pageNo, pageSize);
					count=odd.getCount(null,null);
				}
				if(list.size()!=0){
					PageBean<OrderDetailKZ> pageBean=new PageBean<OrderDetailKZ>();
					//set设置
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(count);
					//转发
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/orderdetail.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('暂时还没有订单明细,快去购物吧......');history.back();</script>");
				}
				
			}else if("searchByEo_Id".equals(type)){
				//以订单号查询明细
				String id="";
				String eoid=(String)request.getSession().getAttribute("eoid");//从删除界面跳转过来的
				if(eoid!=null){
					id=eoid;
					request.getSession().removeAttribute("eoid");
				}
				else
				{
					 id=request.getParameter("id");
				}
				ArrayList<OrderDetailKZ> odlist=odd.getInfoByEo_Id(pageNo,pageSize, id);
				if(odlist.size()!=0){
					PageBean<OrderDetailKZ> pageBean=new PageBean<OrderDetailKZ>();
					//set设置
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(odlist);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(odd.getCount(id,null));
					//转发
					request.setAttribute("id", id);
					request.setAttribute("mess", "searchByEo_Id");
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/orderdetail.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('此订单下暂时还没有订单明细......');location.href='OrderManagerSer?type=search';</script>");
				}
			}else if("del".equals(type)){
				//获得要删除的订单详情id
				Integer id=Integer.parseInt(request.getParameter("id"));
				//获得该订单详情的商品价格
				OrderDetail o=odd.getById(id);
				double price=o.getEod_cost();
				//获得订单号
				String eoid=request.getParameter("eoid");
				//获得该订单号下是否还有订单明细
				ArrayList<OrderDetail> list=odd.getAllInfo(eoid);
				
				//获得从何种操作而来（以用户id查询的明细还是以订单号查询的明细）
				String mess=request.getParameter("mess");
				
				int num=odd.delOrderDetail(id);
				if(num>0){
					//如果该订单号下，有订单明细，执行更改订单价格
					if(list.size()!=0){
						//更新订单表中的价格
						int num1=od.updateOrderByDetail(eoid, price);
					}else
					{
						//指定删除订单操作
						int num2=od.deleteOrder(eoid);
					}
					
					
					//跳转到不同的查询中去
					if(mess.equals("searchByEo_Id")){
						request.getSession().setAttribute("eoid", eoid);
						out.print("<script>alert('删除成功.....');location.href='OrderManagerSer?type=searchByEo_Id';</script>");
					}else if(mess.equals("searchByUid")){
						out.print("<script>alert('删除成功.....');location.href='OrderDetailManagerSer?type=search';</script>");
					}
				}else
				{
					out.print("<script>alert('删除失败.....');history.back();</script>");
				}
			}	
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
