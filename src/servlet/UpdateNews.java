package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewsDao;
import entity.News;
import entity.User;

public class UpdateNews extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateNews() {
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
			NewsDao nd=new NewsDao();
			if(type!=null){
				Integer enId=Integer.parseInt(request.getParameter("id"));
				News n=nd.getOneNewsByID(enId);
				if(n!=null){
					request.setAttribute("news", n);
					request.getRequestDispatcher("manage/news-modify.jsp").forward(request, response);
				}
			}else{
				News n=new News();
				n.setEn_title(request.getParameter("title"));
				n.setEn_content(request.getParameter("content"));
				n.setEn_id(Integer.parseInt(request.getParameter("enid")));
				int num=nd.UpdateNews(n);
				if(num>0){
					out.print("<script>alert('修改新闻成功！');location.href='SelectNewsServ?type=sub'</script>");
				}else{
					out.print("<script>alert('修改新闻失败！');location.href='manage/news-modify.jsp'</script>");
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
