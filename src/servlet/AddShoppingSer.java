package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShoppingDao;

import entity.Product;
import entity.User;

public class AddShoppingSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddShoppingSer() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		String mess="";//���ֶ��ж��Ƿ��û���¼�ɹ������ڷ�������������
		if(u!=null){
			mess="�ѵ�¼�û�";
		}
		if(u==null){
			out.print("<script>alert('��¼����ܲ��������ȵ�¼......');location.href='login.jsp';</script>");
		}else if(u==null&&mess!="")
		{
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}
		else 
		{
			int epid=Integer.parseInt(request.getParameter("epid"));
			ShoppingDao sd=new ShoppingDao();
			int result=sd.addShopGoods(epid,u.getEu_user_id());
			if(result>0){
				request.setAttribute("result", result);
				out.print("<script>alert('�ɹ���������ﳵ��');location.href='ShowShopGoods';</script>");
//				List<Product> lists=sd.getShopList(u.getEu_user_id());
//				request.setAttribute("lists", lists);
//				request.getRequestDispatcher("shopping.jsp").forward(request, response);
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
