package inter;

import java.util.List;

import entity.Comment;
import entity.User;

/**
 * ���Խӿ�
 * @author Administrator
 *
 */
public interface IComment {
	/**
	 * ��ҳ��ѯ��������
	 * @param pageNo ��ǰҳ��
	 * @param pageSize ��ǰҳ����ʾ����������
	 * @param u ��ǰ��¼����
	 * @return
	 */
	List<Comment> getAllComment(int pageNo,int pageSize,User u);
	
	/**
	 * ��ȡ����������
	 * @return
	 */
	int getCount(User u);
	/**
	 * �޸�����
	 * @param c ���Զ���
	 * @return
	 */
	int UpdateComment(Comment c);
	/**
	 * ɾ������
	 * @param ec_id ����ID
	 * @return
	 */
	int DeleteComment(int ec_id);
	/**
	 * �������
	 * @param c ���Զ���
	 * @return
	 */
	int InsertComment(Comment c);
	/**
	 * ����ID��ȡһ��������Ϣ
	 * @param ec_id ����ID
	 * @return
	 */
	Comment getOneCommentByID(int ec_id);


}
