package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

import entity.User;

public class AddUserServ extends HttpServlet {

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
		//获得登录用户对象
		User uL=(User)request.getSession().getAttribute("user");
		if(uL!=null){
			out.print("<script>alert('你已经登录，请注销后再注册......');location.href='SelectAllProductSer';</script>");
		}else
		{
			User u=new User();
			u.setEu_user_id(request.getParameter("userId"));
			u.setEu_user_name(request.getParameter("userName"));
			u.setEu_password(request.getParameter("confirmPassword"));
			u.setEu_sex(request.getParameter("sex"));
			u.setEu_birthday(request.getParameter("birthday"));
			u.setEu_identity_code(request.getParameter("identityCode"));
			u.setEu_email(request.getParameter("email"));
			u.setEu_mobile(request.getParameter("mobile"));
			u.setEu_address(request.getParameter("address"));
			u.setEu_status(1);
			u.setEu_money(0);
			UserDao ud=new UserDao();
			int result=ud.regist(u);
			if(result>0){
				out.print("<script>location.href='reg-result.jsp'</script>");
			}else{
				out.print("<script>alert('注册失败！');location.href='register.jsp'</script>");
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
