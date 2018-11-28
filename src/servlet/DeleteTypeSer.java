package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dao.ProductTypeDao;
import entity.Product;
import entity.User;

public class DeleteTypeSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteTypeSer() {
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
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('���������������µ�¼......');location.href='index.jsp';</script>");
		}else
		{
			String idGet=request.getParameter("id");
			int id=Integer.parseInt(idGet);
			ProductDao pd= new ProductDao();
			ArrayList<Product> list = pd.getAllProduct(id);
			if(list.size()==0){
				ProductTypeDao ptd = new ProductTypeDao();
				int num = ptd.delProductTypeById(id);
				if(num>0){
					out.print("<script>alert('ɾ���ɹ���');location.href='TypeManageSer';</script>");
				}else{
					out.print("<script>alert('ɾ��ʧ�ܣ�');location.href='TypeManageSer';</script>");
				}
			}else{
				out.print("<script>alert('��Ʒ�����������Ʒ��ɾ��ʧ�ܣ�');location.href='TypeManageSer';</script>");
				
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
