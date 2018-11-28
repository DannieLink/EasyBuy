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

public class AddCommentServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddCommentServ() {
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
	 * get����ʽ
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	/**
	 * post����ʽ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommentDao cd=new CommentDao();
		
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}else
		{
			Comment c=new Comment();
			c.setEc_nick_name(u.getEu_user_name());
			c.setEc_content(request.getParameter("guestContent"));
			int num=cd.InsertComment(c);
			
			if(num>0){
				out.print("<script>alert('������Գɹ���');location.href='SelectCommentServ'</script>");
			}else{
				out.print("<script>alert('�������ʧ�ܣ�');location.href='SelectCommentServ'</script>");
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
