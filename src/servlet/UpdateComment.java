package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import entity.Comment;
import entity.User;

public class UpdateComment extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateComment() {
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
			String type=request.getParameter("type");
			CommentDao cd=new CommentDao();
			if(type!=null){
				Comment c=new Comment();
			    c.setEc_reply(request.getParameter("replyContent"));
			    c.setEc_id(Integer.parseInt(request.getParameter("ecid")));
			    int num=cd.UpdateComment(c);
			    if(num>0){
					out.print("<script>alert('回复留言成功！');location.href='SelectCommentServ?type=sub'</script>");
				}else{
					out.print("<script>alert('回复留言失败！');location.href='UpdateComment'</script>");
				}
			}else{
				Integer ecid=Integer.parseInt(request.getParameter("id"));
				Comment c=cd.getOneCommentByID(ecid);
				if(c!=null){
					request.setAttribute("Comment", c);
					request.getRequestDispatcher("manage/guestbook-modify.jsp").forward(request, response);
				}
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
