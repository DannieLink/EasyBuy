package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import dao.ShoppingDao;
import dao.UserDao;

import entity.Order;
import entity.OrderDetail;
import entity.User;

/**
 * 购物车结算
 * @author pc
 *
 */
public class CartSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CartSer() {
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
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserDao ud=new UserDao();
		OrderDao od=new OrderDao();
		OrderDetailDao odd=new OrderDetailDao();
		ProductDao pd = new ProductDao();
		ShoppingDao sd=new ShoppingDao();
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
			//再次获取当前登录用户，及时更新用户信息（余额）
			User uNew=ud.SearchUserById(u.getEu_user_id());
			//产品id集合
			String[] productids=request.getParameterValues("productid");
			//判断购物车中是否有商品
			if(productids==null){
				out.print("<script>alert('购物车空空如也,无法结算的哦,去首页看看吧........');location.href='SelectAllProductSer';</script>");
			}else
			{
			//每个商品数量集合
			String[] numbers=request.getParameterValues("number");
			//单价集合
			String[] prices=request.getParameterValues("price");
			
			//产品总金额
			String totalprice=request.getParameter("totalprice");
			ChongZhi chachong=new ChongZhi(); 
			if(uNew.getEu_money()<Double.parseDouble(totalprice)){
				out.print("<script>alert('余额不足，无法购买，请先充值!');location.href='manage/chongzhi.jsp';</script>"); 
			}else{
				//用户减钱
				ud.CutMoney(Double.parseDouble(totalprice),u.getEu_user_id());
				//下订单
		        String idstr=System.currentTimeMillis()+"";//主键随机序列（订单表主键）
				Order o=new Order();
				o.setEo_id(idstr);
				o.setEu_user_id(u.getEu_user_id());
				o.setEo_cost(Double.parseDouble(totalprice));
				od.addOrder(o);
		         //循环操作
		        for(int i=0;i<productids.length;i++){
		        	//循环减产品数量
		        	 pd.getProductNewStock(Integer.parseInt(productids[i]), Integer.parseInt(numbers[i]));
					 // 循环生成订单详情
		        	 OrderDetail detail=new OrderDetail();
					 detail.setEp_id(Integer.parseInt(productids[i]));
					 detail.setEo_id(idstr);
					 detail.setEod_quantity(Integer.parseInt(numbers[i]));
					 detail.setEod_cost(Double.parseDouble(prices[i])*Integer.parseInt(numbers[i]));
					 odd.addOrderDetail(detail);
				     //循环清除购物车
				     sd.delShopGoods(Integer.parseInt(productids[i]), u.getEu_user_id());
				     //操作成功
				     out.print("<script>location.href='shopping-result.jsp';</script>");
		          }
			}
			}
		}
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
