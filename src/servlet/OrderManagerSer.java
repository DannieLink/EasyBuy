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
import dao.ProductDao;
import dao.UserDao;
import entity.Order;
import entity.OrderDetail;
import entity.PageBean;
import entity.Product;
import entity.User;

public class OrderManagerSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OrderManagerSer() {
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
		OrderDao od=new OrderDao();
		OrderDetailDao odd=new OrderDetailDao();
		ProductDao pd = new ProductDao();
		UserDao ud=new UserDao();
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");

		String mess="";//���ֶ��ж��Ƿ��û���¼�ɹ������ڷ�������������
		if(u!=null){
			mess="�ѵ�¼�û�";
		}
		if(u==null){
			out.print("<script>alert('��¼����ܲ��������ȵ�¼......');location.href='login.jsp';</script>");
		}else if(u==null&&mess!="")
		{
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}
		else 
		{
			User uNew=ud.SearchUserById(u.getEu_user_id());
			//���ݳ����ӻ�õĲ�������ִ�в�ͬ�Ĳ���
			//String type=new String(request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8");
			String type=request.getParameter("type");
			
			//ҳ����ʾ����
			int pageSize =5;
			//��ʼҳ��
			int pageNo=1;
			String no=request.getParameter("pageNo");
			if(no!=null){
				pageNo=Integer.parseInt(no);
			}
			
			//��������ִ�в���
			if("search".equals(type)){
				List<Order> list=null;
				int count=0;
				if(u.getEu_status()==1){
					list=od.getAllInfo(pageNo, pageSize,u);
					count=od.getCount(u.getEu_user_id());
				}else
				{
					list=od.getAllInfo(pageNo, pageSize);
					count=od.getCount(null);
				}
				if(list.size()!=0){
					PageBean<Order> pageBean=new PageBean<Order>();
					//set����
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(count);
					//ת��
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/order.jsp").forward(request, response);
				}else if(list.size()==0)
				{
					if(u.getEu_status()==1){
						out.print("<script>alert('����û�ж���,��ȥ�����......');location.href='SelectAllProductSer';</script>");
					}else if(u.getEu_status()==2)
					{
						out.print("<script>alert('���û�ж���......');location.href='manage/index.jsp';</script>");
					}
					
				}
				
			}else if("likeSearch".equals(type)){
				String id=request.getParameter("orderId");
				String name=request.getParameter("userName");
				ArrayList<Order> list=od.GetByIdOrName(id, name, u);
				if(list.size()!=0){
					PageBean<Order> pageBean=new PageBean<Order>();
					//set����
					pageBean.setCurrentNo(pageNo);
					pageBean.setList(list);
					pageBean.setPageSize(pageSize);
					pageBean.setTotalCount(od.getCount(u.getEu_user_id()));
					//ת��
					request.setAttribute("pageBean", pageBean);
					request.getRequestDispatcher("manage/order.jsp").forward(request, response);
				}else
				{
					out.print("<script>alert('���ѯ�Ķ��������ڣ�����������...Ĭ����ʾ���ж���.....');location.href='OrderManagerSer?type=search';</script>");
				}
				
			}else if("update".equals(type)){
				String id=request.getParameter("id");
				Integer status=Integer.parseInt(request.getParameter("status"));
				Order o=new Order();
				o.setEo_id(id);
				o.setEo_status(status);
				int num=od.updateOrder(o);
				if(num>0){
					if(u.getEu_status()==1){
						out.print("<script>alert('ȷ���ջ��ɹ�');location.href='OrderManagerSer?type=search';</script>");
					}else
					{
						out.print("<script>alert('�����ɹ�');location.href='OrderManagerSer?type=search';</script>");
					}
					
				}else
				{
					out.print("<script>alert('����ʧ��.....');location.href='OrderManagerSer?type=search';</script>");
				}
				
			}else if("buy".equals(type)){//��������
				//���Ҫ����ĵ�����Ʒid
				int id = Integer.parseInt(request.getParameter("id"));
				Product pro=pd.getProductById(id);
				//����û������ڵ�����Ʒ�����й���
				if(uNew.getEu_money()>=pro.getEp_price()){
					//������Ʒ
					//�û���Ǯ
					ud.CutMoney(pro.getEp_price(),u.getEu_user_id());
					//����Ʒ����
					pd.getProductNewStock(pro.getEp_id(), 1);
					//���ɶ���
					 String idstr=System.currentTimeMillis()+"";//�����������
					 Order o=new Order();
					 o.setEo_id(idstr);
					 o.setEu_user_id(u.getEu_user_id());
					 o.setEo_cost(pro.getEp_price());
					 od.addOrder(o);
					//���ɶ�������
					 OrderDetail detail=new OrderDetail();
					 detail.setEp_id(pro.getEp_id());
					 detail.setEo_id(idstr);
					 detail.setEod_quantity(1);
					 detail.setEod_cost(pro.getEp_price());
					 odd.addOrderDetail(detail);
					 out.print("<script>location.href='shopping-result.jsp';</script>");
				}else
				{
					//����
					out.print("<script>alert('���㣬����ʧ�ܣ����ֵ.....');location.href='manage/chongzhi.jsp';</script>");
				}
				
				
			}else if("del".equals(type)){
				String id=request.getParameter("id");
				ArrayList<OrderDetail> odlist=odd.getAllInfo(id);
				//���ҲҪ����
				if(odlist.size()==0){
					int num=od.deleteOrder(id);
					if(num>0){
						out.print("<script>alert('ɾ���ɹ�.....');location.href='OrderManagerSer?type=search';</script>");
					}else
					{
						out.print("<script>alert('ɾ��ʧ��.....');location.href='OrderManagerSer?type=search';</script>");
					}
				}else
				{
					out.print("<script>alert('�ö����»��ж�����ϸ,ɾ��ʧ��.....');location.href='OrderManagerSer?type=search';</script>");
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
