package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductTypeDao;
import entity.PageBean;
import entity.ProductType;
import entity.User;

public class TypeManageSer extends HttpServlet {


	
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
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}else
			{
			//ҳ����ʾ����
			int pageSize =5;
			//��ʼҳ��
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			
			ProductTypeDao pd= new ProductTypeDao();
			ArrayList<ProductType> list=pd.getAllProductType(pageNo, pageSize);
			if(list.size()!=0){
				PageBean<ProductType> pageBean=new PageBean<ProductType>();
				//set����
				pageBean.setCurrentNo(pageNo);
				pageBean.setList(list);
				pageBean.setPageSize(pageSize);
				pageBean.setTotalCount(pd.getCount());
				//ת��
				request.setAttribute("list", list);
				request.setAttribute("pageBean", pageBean);
				request.getRequestDispatcher("manage/productClass.jsp").forward(request, response);
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
