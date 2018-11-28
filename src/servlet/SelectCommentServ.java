package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import entity.Comment;
import entity.PageBean;
import entity.User;

public class SelectCommentServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectCommentServ() {
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
		
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}else
		{
			int pageNo=1;
			int pageSizeFor=3;
			int pageSizeBack=5;
			
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			
			CommentDao cd=new CommentDao();
			String type=request.getParameter("type");
			
			if(type!=null){
				List<Comment> coms=cd.getAllComment(pageNo,pageSizeBack,u);
				if(coms.size()!=0){
					PageBean<Comment> pb=new PageBean<Comment>();
					pb.setCurrentNo(pageNo);
					pb.setList(coms);
					pb.setPageSize(pageSizeBack);
					pb.setTotalCount(cd.getCount(u));
					
					request.setAttribute("pageBean", pb);
					request.getRequestDispatcher("manage/guestbook.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('����û������,����ȥ�������......');location.href='manage/index.jsp';</script>");
				}
			}else{
				List<Comment> coms=cd.getAllComment(pageNo,pageSizeFor,null);
				if(coms!=null){
					PageBean<Comment> pb=new PageBean<Comment>();
					pb.setCurrentNo(pageNo);
					pb.setList(coms);
					pb.setPageSize(pageSizeFor);
					pb.setTotalCount(cd.getCount(null));
					
					request.setAttribute("pageBean", pb);
					request.getRequestDispatcher("guestbook.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('��ʱ��û������,����ȥ�������......');location.href='index.jsp';</script>");
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
