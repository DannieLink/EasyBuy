package inter;

import java.util.List;

import entity.Comment;
import entity.User;

/**
 * 留言接口
 * @author Administrator
 *
 */
public interface IComment {
	/**
	 * 分页查询所有留言
	 * @param pageNo 当前页号
	 * @param pageSize 当前页号显示的数据条数
	 * @param u 当前登录对象
	 * @return
	 */
	List<Comment> getAllComment(int pageNo,int pageSize,User u);
	
	/**
	 * 获取数据总条数
	 * @return
	 */
	int getCount(User u);
	/**
	 * 修改留言
	 * @param c 留言对象
	 * @return
	 */
	int UpdateComment(Comment c);
	/**
	 * 删除留言
	 * @param ec_id 留言ID
	 * @return
	 */
	int DeleteComment(int ec_id);
	/**
	 * 添加留言
	 * @param c 留言对象
	 * @return
	 */
	int InsertComment(Comment c);
	/**
	 * 根据ID获取一条留言信息
	 * @param ec_id 留言ID
	 * @return
	 */
	Comment getOneCommentByID(int ec_id);


}
