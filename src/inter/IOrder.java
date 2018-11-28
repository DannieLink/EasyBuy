package inter;

import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.User;

/**
 * 订单接口
 * @author pc
 *
 */
public interface IOrder {
	
	
	/**
	 * 查询所有订单
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Order> getAllInfo(int pageNo,int pageSize);
	
	/**
	 * 查询指定用户的订单
	 * @param pageIndex 
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Order> getAllInfo(int pageNo,int pageSize,User u);
	
	/**
	 * 获得总的订单数量，便于分页
	 * 有参数，代表查找指定用户的订单总量
	 * 无参数，代表查找所有用户的订单总量
	 * @return
	 */
	public int getCount(String uid);
	
	/**
	 * 添加订单
	 * @param o
	 * @return
	 */
	public int addOrder(Order o);
	
	/**
	 * 更新订单
	 * @param o
	 * @return
	 */
	public int updateOrder(Order o);
	/**
	 * 根据订单明细的删除，相应的修改订单的价格
	 * @param o
	 * @return
	 */
	public int updateOrderByDetail(String id,double price);
	
	
	/**
	 * 删除订单
	 * @param id
	 */
	public int deleteOrder(String id);
	
	/**
	 * 根据用户的输入的订单id或者订货人名称进行模糊查询
	 * @param id
	 * @param name
	 * @return
	 */
	public ArrayList<Order> GetByIdOrName(String id,String name,User u); 
	
	

}
