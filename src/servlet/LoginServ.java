package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class LoginServ extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		//��֤��
		String code=request.getParameter("code");
		String yzCode=(String)request.getSession().getAttribute("numrand");
		
		//��¼
		UserDao ud=new UserDao();
		User u=ud.login(userId, password);
		
		//�ж���֤��
		if(u!=null&&yzCode.equals(code)){
			//����¼���û�����session��
			request.getSession().setAttribute("user", u);
			
			/**
			 * ��ʱ��¼��ʾ��ʱ��
			 * */
			SimpleDateFormat formater = new SimpleDateFormat("yyyy��MM��dd��");
			String strCurrentTime = formater.format(new Date());
			
			request.getSession().setAttribute("strCurrentTime", strCurrentTime);
			out.print("<script>alert('��¼�ɹ���');location.href='manage/index.jsp';</script>");
		}else if(!yzCode.equals(code)){
			out.print("<script>alert('��֤�����');location.href='login.jsp';</script>");
		}else if(u==null&&yzCode.equals(code)){
			out.print("<script>alert('�û������������');location.href='login.jsp';</script>");
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
