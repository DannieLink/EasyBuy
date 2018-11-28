package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import dao.UserDao;
import entity.PageBean;
import entity.User;

public class SearchUserById extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchUserById() {
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
			//页面显示数量
			int pagesize=5;
			
			//初始页面
			int pageNo=1;   //当前页面
			
			String no=request.getParameter("pageNo");
			
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			
			//获取数据
			UserDao ud=new UserDao();
			List<User> ulist=ud.getUserLists(pageNo,pagesize,u.getEu_user_id());
			
			if(ulist!=null){
				PageBean<User> pageBean=new PageBean<User>();
				//set设置
				pageBean.setCurrentNo(pageNo);  //当前页码
				pageBean.setList(ulist);    //保存数据
				pageBean.setPageSize(pagesize);    //每页显示的条数
				pageBean.setTotalCount(ud.getUCount(u));   //总条数
				
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("ulist", ulist);
				
				//转发
				request.getRequestDispatcher("manage/user.jsp").forward(request, response);
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
