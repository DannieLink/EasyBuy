package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entity.PageBean;
import entity.Product;
import entity.User;

public class PrintProductSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrintProductSer() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			//页面显示数量
			int pageSize =8;
			//初始页面
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			ProductDao pd = new ProductDao();
			Integer id=Integer.parseInt(request.getParameter("id")) ;
			List<Product> list=pd.getAllProduct(pageNo, pageSize, id);
			if(list.size()!=0){
				PageBean<Product> pageBean=new PageBean<Product>();
					//set设置
					pageBean.setCurrentNo(pageNo);
				    pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(pd.getCount(id));
					//转发
					request.setAttribute("id", id);
					request.setAttribute("list", list);
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("product-list.jsp").forward(request, response);
			}else{
				out.print("<script>alert('该商品类型下暂无商品，去首页看看吧.....');location.href='SelectAllProductSer';</script>");
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
