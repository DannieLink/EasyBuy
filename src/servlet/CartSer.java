package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import dao.ShoppingDao;
import dao.UserDao;

import entity.Order;
import entity.OrderDetail;
import entity.User;

/**
 * ���ﳵ����
 * @author pc
 *
 */
public class CartSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CartSer() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserDao ud=new UserDao();
		OrderDao od=new OrderDao();
		OrderDetailDao odd=new OrderDetailDao();
		ProductDao pd = new ProductDao();
		ShoppingDao sd=new ShoppingDao();
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
			//�ٴλ�ȡ��ǰ��¼�û�����ʱ�����û���Ϣ����
			User uNew=ud.SearchUserById(u.getEu_user_id());
			//��Ʒid����
			String[] productids=request.getParameterValues("productid");
			//�жϹ��ﳵ���Ƿ�����Ʒ
			if(productids==null){
				out.print("<script>alert('���ﳵ�տ���Ҳ,�޷������Ŷ,ȥ��ҳ������........');location.href='SelectAllProductSer';</script>");
			}else
			{
			//ÿ����Ʒ��������
			String[] numbers=request.getParameterValues("number");
			//���ۼ���
			String[] prices=request.getParameterValues("price");
			
			//��Ʒ�ܽ��
			String totalprice=request.getParameter("totalprice");
			ChongZhi chachong=new ChongZhi(); 
			if(uNew.getEu_money()<Double.parseDouble(totalprice)){
				out.print("<script>alert('���㣬�޷��������ȳ�ֵ!');location.href='manage/chongzhi.jsp';</script>"); 
			}else{
				//�û���Ǯ
				ud.CutMoney(Double.parseDouble(totalprice),u.getEu_user_id());
				//�¶���
		        String idstr=System.currentTimeMillis()+"";//����������У�������������
				Order o=new Order();
				o.setEo_id(idstr);
				o.setEu_user_id(u.getEu_user_id());
				o.setEo_cost(Double.parseDouble(totalprice));
				od.addOrder(o);
		         //ѭ������
		        for(int i=0;i<productids.length;i++){
		        	//ѭ������Ʒ����
		        	 pd.getProductNewStock(Integer.parseInt(productids[i]), Integer.parseInt(numbers[i]));
					 // ѭ�����ɶ�������
		        	 OrderDetail detail=new OrderDetail();
					 detail.setEp_id(Integer.parseInt(productids[i]));
					 detail.setEo_id(idstr);
					 detail.setEod_quantity(Integer.parseInt(numbers[i]));
					 detail.setEod_cost(Double.parseDouble(prices[i])*Integer.parseInt(numbers[i]));
					 odd.addOrderDetail(detail);
				     //ѭ��������ﳵ
				     sd.delShopGoods(Integer.parseInt(productids[i]), u.getEu_user_id());
				     //�����ɹ�
				     out.print("<script>location.href='shopping-result.jsp';</script>");
		          }
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
