package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Order;
import entity.OrderDetail;
import entity.OrderDetailKZ;
import entity.PageBean;
import entity.User;

public class OrderDetailManagerSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderDetailManagerSer() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		OrderDetailDao odd=new OrderDetailDao();
		OrderDao od=new OrderDao();
		
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('���������������µ�¼......');location.href='index.jsp';</script>");
		}else
		{
			//���ݳ����ӻ�õĲ�������ִ�в�ͬ�Ĳ���
			String type=request.getParameter("type");
			
			//ҳ����ʾ����
			int pageSize =2;
			//��ʼҳ��
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			//��������ִ�в���
			if("search".equals(type)){
				//Ĭ�����û�id��ѯ������ϸ
				ArrayList<OrderDetailKZ> list=null;
				int count=0;
				//�ж�����ͨ�û����ǹ���Ա
				//��ͨ�û���ѯ�Լ��Ķ�����ϸ������Ա��ѯ���е�
				if(u.getEu_status()==1){
					list=odd.getInfoByUser_id(pageNo, pageSize,u.getEu_user_id());
					count=odd.getCount(null,u.getEu_user_id());
				}else
				{
					list=odd.getAllInfo(pageNo, pageSize);
					count=odd.getCount(null,null);
				}
				if(list.size()!=0){
					PageBean<OrderDetailKZ> pageBean=new PageBean<OrderDetailKZ>();
					//set����
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(count);
					//ת��
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/orderdetail.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('��ʱ��û�ж�����ϸ,��ȥ�����......');history.back();</script>");
				}
				
			}else if("searchByEo_Id".equals(type)){
				//�Զ����Ų�ѯ��ϸ
				String id="";
				String eoid=(String)request.getSession().getAttribute("eoid");//��ɾ��������ת������
				if(eoid!=null){
					id=eoid;
					request.getSession().removeAttribute("eoid");
				}
				else
				{
					 id=request.getParameter("id");
				}
				ArrayList<OrderDetailKZ> odlist=odd.getInfoByEo_Id(pageNo,pageSize, id);
				if(odlist.size()!=0){
					PageBean<OrderDetailKZ> pageBean=new PageBean<OrderDetailKZ>();
					//set����
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(odlist);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(odd.getCount(id,null));
					//ת��
					request.setAttribute("id", id);
					request.setAttribute("mess", "searchByEo_Id");
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/orderdetail.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('�˶�������ʱ��û�ж�����ϸ......');location.href='OrderManagerSer?type=search';</script>");
				}
			}else if("del".equals(type)){
				//���Ҫɾ���Ķ�������id
				Integer id=Integer.parseInt(request.getParameter("id"));
				//��øö����������Ʒ�۸�
				OrderDetail o=odd.getById(id);
				double price=o.getEod_cost();
				//��ö�����
				String eoid=request.getParameter("eoid");
				//��øö��������Ƿ��ж�����ϸ
				ArrayList<OrderDetail> list=odd.getAllInfo(eoid);
				
				//��ôӺ��ֲ������������û�id��ѯ����ϸ�����Զ����Ų�ѯ����ϸ��
				String mess=request.getParameter("mess");
				
				int num=odd.delOrderDetail(id);
				if(num>0){
					//����ö������£��ж�����ϸ��ִ�и��Ķ����۸�
					if(list.size()!=0){
						//���¶������еļ۸�
						int num1=od.updateOrderByDetail(eoid, price);
					}else
					{
						//ָ��ɾ����������
						int num2=od.deleteOrder(eoid);
					}
					
					
					//��ת����ͬ�Ĳ�ѯ��ȥ
					if(mess.equals("searchByEo_Id")){
						request.getSession().setAttribute("eoid", eoid);
						out.print("<script>alert('ɾ���ɹ�.....');location.href='OrderManagerSer?type=searchByEo_Id';</script>");
					}else if(mess.equals("searchByUid")){
						out.print("<script>alert('ɾ���ɹ�.....');location.href='OrderDetailManagerSer?type=search';</script>");
					}
				}else
				{
					out.print("<script>alert('ɾ��ʧ��.....');history.back();</script>");
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
