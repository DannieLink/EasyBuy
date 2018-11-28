package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Order;
import entity.User;
import inter.IOrder;


/**
 * 订单实现类
 * @author pc
 *
 */
public class OrderDao  extends BaseDao implements IOrder{

	@Override
	/**
	 * 添加一个订单
	 */
	public int addOrder(Order o) {
		String sql="insert into [Order](EO_ID, EU_USER_ID, EO_CREATE_TIME, EO_COST, EO_STATUS) " +
		"  values(?,?,getdate(),?,1)";
		Object[] p={o.getEo_id(),o.getEu_user_id(),o.getEo_cost()};
		return execUpdate(sql, p);
	}

	@Override
	/**
	 * 获得所有订单（分页显示）
	 */
	public ArrayList<Order> getAllInfo(int pageNo, int pageSize) {
		ArrayList<Order> list=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Order o=null;
		try {
			conn=getConn();
			String sql="select top (?) * from [Order]" +
					"  where EO_ID not in " +
					"   (select top (?) EO_ID from [Order] order by " +
					"EO_CREATE_TIME desc)" +
					"  order by EO_CREATE_TIME desc";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, pageSize);
			pst.setInt(2, (pageNo -1)*pageSize);
			
			rs=pst.executeQuery();
			
			while(rs.next()){
				o=new Order();
				o.setEo_id(rs.getString(1));
				o.setEu_user_id(rs.getString(2));
				o.setEo_create_time(rs.getDate(3));
				o.setEo_cost(rs.getDouble(4));
				o.setEo_status(rs.getInt(5));
				
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return list;
	}

	
	@Override
	/**
	 * 获得指定用户的所有订单（分页显示）
	 */
	public ArrayList<Order> getAllInfo(int pageNo,int pageSize,User u) {
		ArrayList<Order> list=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Order o=null;
		try {
			conn=getConn();
			String sql="select top (?) * from [Order]" +
					"  where EO_ID not in " +
					"   (select top (?) EO_ID from [Order] where EU_USER_ID=?   " +
					"order by EO_CREATE_TIME desc)" +
					"  and EU_USER_ID=?  order by EO_CREATE_TIME desc";
			
			pst=conn.prepareStatement(sql);
			
			pst.setInt(1, pageSize);
			pst.setInt(2, (pageNo -1)*pageSize);
			pst.setString(3, u.getEu_user_id());
			pst.setString(4, u.getEu_user_id());
			
			rs=pst.executeQuery();
			while(rs.next()){
				o=new Order();
				o.setEo_id(rs.getString(1));
				o.setEu_user_id(rs.getString(2));
				o.setEo_create_time(rs.getDate(3));
				o.setEo_cost(rs.getDouble(4));
				o.setEo_status(rs.getInt(5));
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return list;
	}

	@Override
	/**
	 * 修改一个订单
	 */
	public int updateOrder(Order o) {
		String sql="update [Order] set  EO_STATUS=? where EO_ID=?";
		Object[] p={o.getEo_status(),o.getEo_id()};
        return execUpdate(sql, p);
	}

	@Override
	/**
	 * 删除一个订单
	 */
	public int deleteOrder(String id) {
		String sql="delete from [Order] where  EO_ID=?";
		Object[] p={id};
		return execUpdate(sql, p);
	}

	

	@Override
	/**
	 * 获得总的订单数量，便于分页
	 * 有参数，代表查找指定用户的订单总量
	 * 无参数，代表查找所有用户的订单总量
	 */
	public int getCount(String uid) {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			String sql="select count(*) from [Order]" ;
			
			if(uid!=null){
				sql+=" where EU_USER_ID=?";
			}
			
			pst=conn.prepareStatement(sql);
			
			if(uid!=null){
				pst.setString(1, uid);
			}
			
			rs=pst.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return count;
	}

	@Override
	/**
	 * 模糊查询
	 */
	public ArrayList<Order> GetByIdOrName(String id, String name,User u) {
		ArrayList<Order> list=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Order o=null;
		
		ArrayList<String> info=new ArrayList<String>();
		
		try {
			conn=getConn();
			
			String sql="select * from [Order] where 1=1" ;
			
			if(id!=""){
				sql+="  and EO_ID  like ?";
				info.add("%"+id+"%");
			}
			if(name!=""){
				
				sql+="  and EU_USER_ID like ?";
				info.add("%"+name+"%");
			}
			
			sql+="order by EO_CREATE_TIME desc";
			
			pst=conn.prepareStatement(sql);
			
			for (int i = 0; i < info.size(); i++) {
				pst.setObject(i+1, info.get(i));
			}
			
			rs=pst.executeQuery();
			
			while(rs.next()){
				o=new Order();
				o.setEo_id(rs.getString(1));
				o.setEu_user_id(rs.getString(2));
				o.setEo_create_time(rs.getDate(3));
				o.setEo_cost(rs.getDouble(4));
				o.setEo_status(rs.getInt(5));
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return list;
	}

	@Override
	/**
	 * 修改订单价格
	 */
	public int updateOrderByDetail(String id,double price) {
		String sql="update [Order] set  EO_COST-=? where EO_ID=?";
		Object[] p={price,id};
        return execUpdate(sql, p);
	}

	
}
