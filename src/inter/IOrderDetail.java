package inter;

import java.util.ArrayList;

import entity.OrderDetail;
import entity.OrderDetailKZ;

/**
 * ��������ӿ�
 * @author pc
 *
 */
public interface IOrderDetail {
	
	/**
	 * �޲�������ѯ���ж�������
	 * �в�������ѯָ���Ķ�������
	 * @return
	 */
	public ArrayList<OrderDetail> getAllInfo(String eo_id);
	
	/**
	 * (��ҳ��ʾ)���ݶ���Id��ȡ��Ӧ�Ķ�������
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getInfoByEo_Id( int pageNo,int pageSize,String eo_id);
	
	/**
	 * (��ҳ��ʾ)��ʾ���еĶ�������
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getAllInfo( int pageNo,int pageSize);
	
	/**
	 * (��ҳ��ʾ)�����û�id��ȡ��Ӧ�Ķ�������
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getInfoByUser_id( int pageNo,int pageSize,String uid);
	
	
	/**
	 * ��Ӷ�������
	 * @param o
	 * @return
	 */
	public int addOrderDetail(OrderDetail od);
	
	/**
	 * ����ܵĶ������������ڷ�ҳ
	 * �в����������ѯָ���Ķ�������
	 * �޲����������ѯ���еĶ�������
	 * @return
	 */
	public int getCount(String eo_id,String uid);
	
	/**
	 * ����idɾ����������
	 * @param id
	 * @return
	 */
	public int delOrderDetail(int id);
	
	/**
	 * ����id ��ö��������е���Ϣ
	 * */
	public OrderDetail getById(int id);


}
