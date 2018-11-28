package inter;

import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.User;

/**
 * �����ӿ�
 * @author pc
 *
 */
public interface IOrder {
	
	
	/**
	 * ��ѯ���ж���
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Order> getAllInfo(int pageNo,int pageSize);
	
	/**
	 * ��ѯָ���û��Ķ���
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Order> getAllInfo(int pageNo,int pageSize,User u);
	
	/**
	 * ����ܵĶ������������ڷ�ҳ
	 * �в������������ָ���û��Ķ�������
	 * �޲�����������������û��Ķ�������
	 * @return
	 */
	public int getCount(String uid);
	
	/**
	 * ��Ӷ���
	 * @param o
	 * @return
	 */
	public int addOrder(Order o);
	
	/**
	 * ���¶���
	 * @param o
	 * @return
	 */
	public int updateOrder(Order o);
	/**
	 * ���ݶ�����ϸ��ɾ������Ӧ���޸Ķ����ļ۸�
	 * @param o
	 * @return
	 */
	public int updateOrderByDetail(String id,double price);
	
	
	/**
	 * ɾ������
	 * @param id
	 */
	public int deleteOrder(String id);
	
	/**
	 * �����û�������Ķ���id���߶��������ƽ���ģ����ѯ
	 * @param id
	 * @param name
	 * @return
	 */
	public ArrayList<Order> GetByIdOrName(String id,String name,User u); 
	
	

}
