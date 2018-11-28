package inter;
import java.util.ArrayList;

import entity.Product;

/**
 * ��Ʒ�ӿ�
 * @author pc
 *
 */
public interface IProduct {
	/**
	 * ���������Ʒ
	 * @return
	 */
	ArrayList<Product> getAllProduct();
	/**
	 * ��ҳ��ʾָ����Ʒ�����µ���Ʒ����
	 * @param pageNo
	 * @param pageSize
	 * @param epcId
	 * @return
	 */
	ArrayList<Product> getAllProduct(int pageNo, int pageSize,int epcId);
	
	/**
	 *��ҳ��ʾ������Ʒ����
	 * @param pageNo
	 * @param pageSize
	 * @param epcId
	 * @return
	 */
	ArrayList<Product> getAllProduct(int pageNo, int pageSize);
	
	

	/**
	 * ������Ʒ���Id�����Ʒ
	 * @param pro
	 * @return һ����Ʒ
	 */
	ArrayList<Product> getAllProduct(int epc_id);
	
	/**
	 * ������ƷId�����Ʒ
	 * @param pro
	 * @return һ����Ʒ
	 */
	Product getProductById(int id);
	/**
	 * �����Ʒ
	 * @param pro
	 * @return ���ݿ���Ӱ�������
	 */
	int addProduct(Product pro);
	/**
	 * ������ƷId�޸���Ʒ
	 * @param pro
	 * @return ���ݿ���Ӱ�������
	 */
	int updateProductById(Product pro);
	/**
	 * ������ƷIdɾ����Ʒ
	 * @param pro
	 * @return ���ݿ���Ӱ�������
	 */
	int selectProductById(int id);
	
	/**
	 * �޲�epcIdʱ����ѯ������Ʒ������
	 * �в���epcIdʱ��������Ʒ����ID��ѯָ����Ʒ����
	 * @param epcId
	 * @return
	 */
	int getCount(int epcId);
	
	/**
	 * ���¿��
	 * @param epid
	 * @param stock
	 * @return ���
	 */
	public int getProductNewStock(int epid,int stock);

}
