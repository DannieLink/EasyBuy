package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileUpLoad;
import dao.ProductDao;

import entity.Product;
import entity.User;

public class UpdateProductSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateProductSer() {
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
		//获得登录用户对象
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('您掉线啦，请重新登录......');location.href='login.jsp';</script>");
		}else
		{
			ProductDao pd=new ProductDao();
			//获得要修改商品的信息
			Map<String, String> map=null;
			try {
				map=FileUpLoad.upLoad(request, response, "images/product",null, 10000000, 10000000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Product p=new Product();
			p.setEp_id(Integer.parseInt(map.get("productId")));
			p.setEp_name(map.get("productName"));
			p.setEp_description(map.get("description"));
			p.setEpc_Id(Integer.parseInt(map.get("parentId")));
			p.setEp_price(Double.parseDouble(map.get("productPrice")));
			p.setEp_stock(Integer.parseInt(map.get("productNumber")));
			p.setEp_file_name(map.get("photo"));
		
			int num=pd.updateProductById(p);
			if(num>0){
				out.write("<script>alert('商品修改成功！');location.href='SelectProductBackSer';</script>");
			}else{
				out.write("<script>alert('商品修改失败！');history.back();</script>");
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
