package inter;

import java.util.List;

import entity.Product;
import entity.Shopping;

/**
 * ������ӿ�
 * @author pc
 *
 */
public interface IShopping {
	/**
	 * ��ȡ������Ʒ��Ϣ����ʾ���ﳵ�б�
	 * @return
	 */
	List<Product> getShopList(String euid);
	
	/**
	 * ɾ�����ﳵ����Ʒ
	 * @param epid ��ƷId
	 * @param euid �û�Id
	 * @return
	 */
	int delShopGoods(int epid,String euid);
	
	/**
	 * �����Ʒ��Ϣ�����ﳵ
	 * @param epid ��Ʒid
	 * @param euid �û�id
	 * @return
	 */
	int addShopGoods(int epid,String euid);
	
	/**
	 * ��ȡ�û�������ͬ��Ʒ������
	 * @param epid
	 * @param euid
	 * @return
	 */
	int getShopCount(int epid,String euid);
	
	/**
	 * ��ȡ���ﳵ������
	 * @param euid
	 * @return
	 */
	int getTotalCount(String euid);
	
	
}
