package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewsDao;
import entity.News;
import entity.PageBean;
import entity.User;

public class SelectNewsServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectNewsServ() {
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
		
			String type=request.getParameter("type");
			NewsDao nd=new NewsDao();
			int pageNo=1;
			int pageSize=5;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			if(type!=null){
				if(u==null){
					out.print("<script>alert('您掉线啦，请重新登录......');location.href='login.jsp';</script>");
				}else
				{
					List<News> news=nd.getAllNewsBypage(pageNo,pageSize);
					if(news!=null){
						PageBean<News> pb=new PageBean<News>();
						pb.setCurrentNo(pageNo);
						pb.setList(news);
						pb.setPageSize(pageSize);
						pb.setTotalCount(nd.getCount());
						request.setAttribute("pageBean", pb);
						request.getRequestDispatcher("manage/news.jsp").forward(request, response);
					}
				}
				
			}else{
				Integer enId=Integer.parseInt(request.getParameter("id"));
				News n=nd.getOneNewsByID(enId);
				if(n!=null){
					request.setAttribute("news", n);
					request.getRequestDispatcher("news-view.jsp").forward(request, response);
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
