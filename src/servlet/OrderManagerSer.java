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
import dao.ProductDao;
import dao.UserDao;
import entity.Order;
import entity.OrderDetail;
import entity.PageBean;
import entity.Product;
import entity.User;

public class OrderManagerSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderManagerSer() {
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
		OrderDao od=new OrderDao();
		OrderDetailDao odd=new OrderDetailDao();
		ProductDao pd = new ProductDao();
		UserDao ud=new UserDao();
		//获得登录用户对象
		User u=(User)request.getSession().getAttribute("user");

		String mess="";//该字段判断是否用户登录成功，用于服务器重启操作
		if(u!=null){
			mess="已登录用户";
		}
		if(u==null){
			out.print("<script>alert('登录后才能操作，请先登录......');location.href='login.jsp';</script>");
		}else if(u==null&&mess!="")
		{
			out.print("<script>alert('您掉线啦，请重新登录......');location.href='login.jsp';</script>");
		}
		else 
		{
			User uNew=ud.SearchUserById(u.getEu_user_id());
			//根据超链接获得的操作类型执行不同的操作
			//String type=new String(request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8");
			String type=request.getParameter("type");
			
			//页面显示数量
			int pageSize =5;
			//初始页面
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			
			//根据类型执行操作
			if("search".equals(type)){
				List<Order> list=null;
				int count=0;
				if(u.getEu_status()==1){
					list=od.getAllInfo(pageNo, pageSize,u);
					count=od.getCount(u.getEu_user_id());
				}else
				{
					list=od.getAllInfo(pageNo, pageSize);
					count=od.getCount(null);
				}
				if(list.size()!=0){
					PageBean<Order> pageBean=new PageBean<Order>();
					//set设置
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(count);
					//转发
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/order.jsp").forward(request, response);
				}else if(list.size()==0)
				{
					if(u.getEu_status()==1){
						out.print("<script>alert('您还没有订单,快去购物吧......');location.href='SelectAllProductSer';</script>");
					}else if(u.getEu_status()==2)
					{
						out.print("<script>alert('最近没有订单......');location.href='manage/index.jsp';</script>");
					}
					
				}
				
			}else if("likeSearch".equals(type)){
				String id=request.getParameter("orderId");
				String name=request.getParameter("userName");
				ArrayList<Order> list=od.GetByIdOrName(id, name, u);
				if(list.size()!=0){
					PageBean<Order> pageBean=new PageBean<Order>();
					//set设置
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(od.getCount(u.getEu_user_id()));
					//转发
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/order.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('你查询的订单不存在，请重新输入...默认显示所有订单.....');location.href='OrderManagerSer?type=search';</script>");
				}
				
			}else if("update".equals(type)){
				String id=request.getParameter("id");
				Integer status=Integer.parseInt(request.getParameter("status"));
				Order o=new Order();
				o.setEo_id(id);
				o.setEo_status(status);
				int num=od.updateOrder(o);
				if(num>0){
					if(u.getEu_status()==1){
						out.print("<script>alert('确认收货成功');location.href='OrderManagerSer?type=search';</script>");
					}else
					{
						out.print("<script>alert('发货成功');location.href='OrderManagerSer?type=search';</script>");
					}
					
				}else
				{
					out.print("<script>alert('操作失败.....');location.href='OrderManagerSer?type=search';</script>");
				}
				
			}else if("buy".equals(type)){//单件购买
				//获得要购买的单件商品id
				int id = Integer.parseInt(request.getParameter("id"));
				Product pro=pd.getProductById(id);
				//如果用户金额大于等于商品金额，进行购买
				if(uNew.getEu_money()>=pro.getEp_price()){
					//购买商品
					//用户减钱
					ud.CutMoney(pro.getEp_price(),u.getEu_user_id());
					//减产品数量
					pd.getProductNewStock(pro.getEp_id(), 1);
					//生成订单
					 String idstr=System.currentTimeMillis()+"";//主键随机序列
					 Order o=new Order();
					 o.setEo_id(idstr);
					 o.setEu_user_id(u.getEu_user_id());
					 o.setEo_cost(pro.getEp_price());
					 od.addOrder(o);
					//生成订单详情
					 OrderDetail detail=new OrderDetail();
					 detail.setEp_id(pro.getEp_id());
					 detail.setEo_id(idstr);
					 detail.setEod_quantity(1);
					 detail.setEod_cost(pro.getEp_price());
					 odd.addOrderDetail(detail);
					 out.print("<script>location.href='shopping-result.jsp';</script>");
				}else
				{
					//余额不足
					out.print("<script>alert('余额不足，购买失败，请充值.....');location.href='manage/chongzhi.jsp';</script>");
				}
				
				
			}else if("del".equals(type)){
				String id=request.getParameter("id");
				ArrayList<OrderDetail> odlist=odd.getAllInfo(id);
				//这边也要完善
				if(odlist.size()==0){
					int num=od.deleteOrder(id);
					if(num>0){
						out.print("<script>alert('删除成功.....');location.href='OrderManagerSer?type=search';</script>");
					}else
					{
						out.print("<script>alert('删除失败.....');location.href='OrderManagerSer?type=search';</script>");
					}
				}else
				{
					out.print("<script>alert('该订单下还有订单明细,删除失败.....');location.href='OrderManagerSer?type=search';</script>");
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
