package inter;

import java.util.List;

import entity.User;

/**
 * �û��ӿ���
 * @author pc
 *
 */
public interface IUser {
	/**
	 * ��¼����
	 * @return
	 */
	User login(String userId,String password);
	
	/**
	 * ע�Ṧ��
	 * @return
	 */
	int regist(User user);
	
	/**
	 * ����ID��ѯ�û�
	 * @return
	 */
	User SearchUserById(String userId);
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	int delUser(int id);
	
	/**
	 * �޸��û�
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * ��userIdΪ�գ���ȡ�����û���Ϣ
	 * ��userId��Ϊ�գ�����ID��ѯ�û�
	 * @param userId
	 * @return
	 */
	List<User> getUserLists(int pageNo,int pageSize,String userId);
	
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	int delUser(String userId);
	
	/**
	 * ������û��������ڷ�ҳ
	 * @return
	 */
	int getUCount(User u);
	
	/**
	 * �û���ֵ���
	 * @param euid
	 * @return
	 */
	int ChongMoney(double money,String euid);
	
	/**
	 * �����Ǯ
	 * @param euid
	 * @return
	 */
	int CutMoney(double money,String euid);
}
