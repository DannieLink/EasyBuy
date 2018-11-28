package inter;

import java.util.List;

import entity.User;

/**
 * 用户接口类
 * @author pc
 *
 */
public interface IUser {
	/**
	 * 登录功能
	 * @return
	 */
	User login(String userId,String password);
	
	/**
	 * 注册功能
	 * @return
	 */
	int regist(User user);
	
	/**
	 * 根据ID查询用户
	 * @return
	 */
	User SearchUserById(String userId);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int delUser(int id);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * 若userId为空，获取所有用户信息
	 * 若userId不为空，根据ID查询用户
	 * @param userId
	 * @return
	 */
	List<User> getUserLists(int pageNo,int pageSize,String userId);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int delUser(String userId);
	
	/**
	 * 获得总用户数量便于分页
	 * @return
	 */
	int getUCount(User u);
	
	/**
	 * 用户充值金额
	 * @param euid
	 * @return
	 */
	int ChongMoney(double money,String euid);
	
	/**
	 * 购买减钱
	 * @param euid
	 * @return
	 */
	int CutMoney(double money,String euid);
}
