package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class UpdateUserServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateUserServ() {
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
		//获得登录用户对象
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('您掉线啦，请重新登录......');location.href='login.jsp';</script>");
		}else
		{
			String birthday=request.getParameter("birthday");
			String userId=request.getParameter("userName");
			UserDao ud=new UserDao();
			String type=request.getParameter("type");
			if(type.equals("update")){
				User nuser=new User();
				nuser.setEu_user_id(userId);
				nuser.setEu_user_name(request.getParameter("name"));
				nuser.setEu_password(request.getParameter("passWord2"));
				nuser.setEu_sex(request.getParameter("sex"));
				nuser.setEu_birthday(birthday);
				nuser.setEu_mobile(request.getParameter("mobile"));
				nuser.setEu_address(request.getParameter("address"));
				int result=ud.updateUser(nuser);
				if(result>0){
				request.getRequestDispatcher("SearchUserById").forward(request, response);
				}
			}else if(type.equals("delete")){
				String id=request.getParameter("id");
				int delresult=ud.delUser(id);
				if(delresult>0){
					out.print("<script>alert('删除成功！')</script>");
					request.getRequestDispatcher("SearchUserById").forward(request, response);
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
