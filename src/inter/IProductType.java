package inter;
import java.util.ArrayList;

import entity.ProductType;
/**
 * ��Ʒ���ӿ�
 * @author pc
 *
 */
public interface IProductType {
	/**
	 * ��ѯ������Ʒ���
	 * @return
	 */
	ArrayList<ProductType> getAllProductType();
	/**
	 * ������Ʒ���idɾ��ɾ����Ʒ���
	 * @param id
	 * @return ��Ӱ�������
	 */
	int delProductTypeById(int id);
	/**
	 * �޸���Ʒ���
	 * @return ��Ӱ�������
	 */
	int updateProductType(ProductType pt);
	/**
	 * �����Ʒ���
	 * @return ��Ӱ�������
	 */
	int addProductType(ProductType pt);
	
	/**
	 * ����Ʒ����ҳ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<ProductType> getAllProductType(int pageNo, int pageSize);
	/**
	 * �����Ʒ����������
	 */
	int getCount();
	
	/**
	 * ������Ʒ��������жϸ���Ʒ����Ƿ����
	 * @param epcname
	 * @return
	 */
	public ProductType getType(String epcname);
	
	/**
	 * �޸�
	 * @param epcname
	 * @return
	 */
	public ProductType getType(int epcid, String epcname) ;
	
	/**
	 * ������Ʒ����ȡ��Ʒ������
	 * @param epcid
	 * @return
	 */
	public ProductType getType(int epcid);

}
