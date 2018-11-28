package inter;
import java.util.List;

import entity.News;
/**
 * ���Žӿ�
 * @author Administrator
 *
 */
public interface INews {
	/**
	 * ��ҳ��ѯ��������
	 * @param pageNo ��ǰҳ��
	 * @param pageSize ��ǰҳ����ʾ���ݵ�����
	 * @return
	 */
	List<News> getAllNewsBypage(int pageNo,int pageSize);
	/**
	 * ��ȡ��������������
	 * @return
	 */
	int getCount();
	/**
	 * ��ѯ��������
	 * @return
	 */
	List<News> getAllNews();
	/**
	 * �޸�����
	 * @param n ���Ŷ���
	 * @return
	 */
	int UpdateNews(News n);
	/**
	 * ɾ������
	 * @param en_id ����ID
	 * @return
	 */
	int DeleteNews(int en_id);
	/**
	 * �������
	 * @param n ���Ŷ���
	 * @return
	 */
	int InsertNews(News n);
	
	/**
	 * ��������ID��ȡһ����ϸ��������Ϣ
	 * @param en_id ����ID
	 * @return
	 */
	News getOneNewsByID(int en_id);

}
