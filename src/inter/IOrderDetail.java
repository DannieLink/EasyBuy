package inter;

import java.util.ArrayList;

import entity.OrderDetail;
import entity.OrderDetailKZ;

/**
 * 订单详情接口
 * @author pc
 *
 */
public interface IOrderDetail {
	
	/**
	 * 无参数，查询所有订单详情
	 * 有参数，查询指定的订单详情
	 * @return
	 */
	public ArrayList<OrderDetail> getAllInfo(String eo_id);
	
	/**
	 * (分页显示)根据订单Id获取相应的订单详情
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getInfoByEo_Id( int pageNo,int pageSize,String eo_id);
	
	/**
	 * (分页显示)显示所有的订单详情
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getAllInfo( int pageNo,int pageSize);
	
	/**
	 * (分页显示)根据用户id获取相应的订单详情
	 * @return
	 */
	public ArrayList<OrderDetailKZ> getInfoByUser_id( int pageNo,int pageSize,String uid);
	
	
	/**
	 * 添加订单详情
	 * @param o
	 * @return
	 */
	public int addOrderDetail(OrderDetail od);
	
	/**
	 * 获得总的订单数量，便于分页
	 * 有参数，代表查询指定的订单详情
	 * 无参数，代表查询所有的订单详情
	 * @return
	 */
	public int getCount(String eo_id,String uid);
	
	/**
	 * 根据id删除订单详情
	 * @param id
	 * @return
	 */
	public int delOrderDetail(int id);
	
	/**
	 * 根据id 获得订单表所有的信息
	 * */
	public OrderDetail getById(int id);


}
