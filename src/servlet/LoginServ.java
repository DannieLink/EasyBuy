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
		
		//验证码
		String code=request.getParameter("code");
		String yzCode=(String)request.getSession().getAttribute("numrand");
		
		//登录
		UserDao ud=new UserDao();
		User u=ud.login(userId, password);
		
		//判断验证码
		if(u!=null&&yzCode.equals(code)){
			//将登录的用户放入session中
			request.getSession().setAttribute("user", u);
			
			/**
			 * 定时登录显示的时间
			 * */
			SimpleDateFormat formater = new SimpleDateFormat("yyyy年MM月dd日");
			String strCurrentTime = formater.format(new Date());
			
			request.getSession().setAttribute("strCurrentTime", strCurrentTime);
			out.print("<script>alert('登录成功！');location.href='manage/index.jsp';</script>");
		}else if(!yzCode.equals(code)){
			out.print("<script>alert('验证码错误！');location.href='login.jsp';</script>");
		}else if(u==null&&yzCode.equals(code)){
			out.print("<script>alert('用户名或密码错误！');location.href='login.jsp';</script>");
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
