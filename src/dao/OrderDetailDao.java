package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.OrderDetail;
import entity.OrderDetailKZ;
import inter.IOrderDetail;
/**
 * 订单详情实现类
 * @author pc
 *
 */
public class OrderDetailDao extends BaseDao implements IOrderDetail{

	@Override
	/**
	 * 添加订单详情
	 */
	public int addOrderDetail(OrderDetail od) {
		String sql="insert into OrderDetail(EO_ID, EP_ID, EOD_QUANTITY, EOD_COST) " +
		"  values(?,?,?,?)";
		Object[] p={od.getEo_id(),od.getEp_id(),od.getEod_quantity(),od.getEod_cost()};
		return execUpdate(sql, p);
	}

	@Override
	/**
	 * 获得所有的订单详情
	 */
	public ArrayList<OrderDetail> getAllInfo(String eo_id) {
		ArrayList<OrderDetail> list=new ArrayList<OrderDetail>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		OrderDetail od=null;
		
		try {
			conn=getConn();
			String sql="select * from OrderDetail";
			if(eo_id!=""){
				sql+=" where EO_ID=?";
			}
			
			pst=conn.prepareStatement(sql);
			
			if(eo_id!=""){
				pst.setString(1, eo_id);
			}
			
			rs=pst.executeQuery();
			
			while(rs.next()){
				od=new OrderDetail();
				od.setEod_id(rs.getInt(1));
				od.setEo_id(rs.getString(2));
				od.setEp_id(rs.getInt(3));
				od.setEod_quantity(rs.getInt(4));
				od.setEod_cost(rs.getDouble(5));
				list.add(od);
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
	 * (分页显示)根据订单ID获得相应的订单明细
	 */
	public ArrayList<OrderDetailKZ> getInfoByEo_Id(int pageNo,int pageSize,String eoId) {
		ArrayList<OrderDetailKZ> list=new ArrayList<OrderDetailKZ>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		OrderDetailKZ od=null;
		try {
			conn=getConn();
			String sql="select  top(?) EOD_ID,od.EO_ID, od.EP_ID, EOD_QUANTITY," +
					" EOD_COST, EP_NAME,EU_USER_ID,EP_FILE_NAME " +
					"from OrderDetail od " +
					"  inner join [Order] o on od.EO_ID=o.EO_ID " +
					"  inner join Product p on p.EP_ID=od.EP_ID"+
					" where od.EOD_ID not in (select top(?) EOD_ID from " +
					"OrderDetail where EO_ID=?) and od.EO_ID=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, pageSize);
			pst.setInt(2, (pageNo-1)*pageSize);
			pst.setString(3, eoId);
			pst.setString(4, eoId);
			rs=pst.executeQuery();
			
			while(rs.next()){
				od=new OrderDetailKZ();
				od.setEod_id(rs.getInt(1));
				od.setEo_id(rs.getString(2));
				od.setEp_id(rs.getInt(3));
				od.setEod_quantity(rs.getInt(4));
				od.setEod_cost(rs.getDouble(5));
				od.setEp_name(rs.getString(6));
				od.setEu_user_id(rs.getString(7));
				od.setEp_file_name(rs.getString(8));
				list.add(od);
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
	 * 获得所有订单的总数量
	 * 有参数代表查询指定的订单号的订单详情数量
	 * 无参数查询所有订单详情的数量
	 * 根据订单号或者用户id查询订单明细数量
	 * 两者选其一，不会同时出现都有的情况
	 */
	public int getCount(String eo_id,String uid) {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			conn=getConn();
			String sql="select count(*) from OrderDetail " ;
			if(eo_id!=null){
				sql+=" where  EO_ID=?";
			}
			
			if(uid!=null){
				sql+=" inner join  [Order] on OrderDetail. EO_ID=[Order].EO_ID " +
						" where EU_USER_ID=?";
			}
			
			pst=conn.prepareStatement(sql);
			
			if(eo_id!=null){
				pst.setString(1, eo_id);
			}
			
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
	 * 删除订单详情
	 */
	public int delOrderDetail(int id) {
		String sql="delete from OrderDetail where  EOD_ID=?";
		Object[] p={id};
		return execUpdate(sql, p);
	}

	@Override
	/**
	 * 显示所有的订单详情（明细）
	 */
	public ArrayList<OrderDetailKZ> getAllInfo(int pageNo, int pageSize) {
		ArrayList<OrderDetailKZ> list=new ArrayList<OrderDetailKZ>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		OrderDetailKZ od=null;
		try {
			conn=getConn();
			String sql="select  top(?) EOD_ID,od.EO_ID, od.EP_ID, EOD_QUANTITY," +
					" EOD_COST, EP_NAME,EU_USER_ID,EP_FILE_NAME " +
					"from OrderDetail od " +
					"  inner join [Order] o on od.EO_ID=o.EO_ID " +
					"  inner join Product p on p.EP_ID=od.EP_ID"+
					" where od.EOD_ID not in (select top(?) EOD_ID " +
					"from OrderDetail)";
			
			pst=conn.prepareStatement(sql);
			pst.setInt(1, pageSize);
			pst.setInt(2, (pageNo-1)*pageSize);
			rs=pst.executeQuery();
			
			while(rs.next()){
				od=new OrderDetailKZ();
				od.setEod_id(rs.getInt(1));
				od.setEo_id(rs.getString(2));
				od.setEp_id(rs.getInt(3));
				od.setEod_quantity(rs.getInt(4));
				od.setEod_cost(rs.getDouble(5));
				od.setEp_name(rs.getString(6));
				od.setEu_user_id(rs.getString(7));
				od.setEp_file_name(rs.getString(8));
				list.add(od);
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
	 * 根据用户id获取相应的订单明细
	 */
	public ArrayList<OrderDetailKZ> getInfoByUser_id(int pageNo, int pageSize,
			String uid) {
		ArrayList<OrderDetailKZ> list=new ArrayList<OrderDetailKZ>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		OrderDetailKZ od=null;
		try {
			conn=getConn();
			String sql="select  top(?) EOD_ID,od.EO_ID, od.EP_ID, EOD_QUANTITY, " +
					"EOD_COST, EP_NAME,EU_USER_ID,EP_FILE_NAME " +
					"from OrderDetail od " +
					"  inner join [Order] o on od.EO_ID=o.EO_ID " +
					"  inner join Product p on p.EP_ID=od.EP_ID"+
					" where od.EOD_ID not in " +
						"( select top(?) EOD_ID from OrderDetail " +
						"  inner join [Order] on  OrderDetail.EO_ID=[Order].EO_ID " +
						"  where EU_USER_ID=?) " +
					"  and EU_USER_ID=?";
			
			pst=conn.prepareStatement(sql);
			pst.setInt(1, pageSize);
			pst.setInt(2, (pageNo-1)*pageSize);
			pst.setString(3, uid);
			pst.setString(4, uid);
			rs=pst.executeQuery();
			
			while(rs.next()){
				od=new OrderDetailKZ();
				od.setEod_id(rs.getInt(1));
				od.setEo_id(rs.getString(2));
				od.setEp_id(rs.getInt(3));
				od.setEod_quantity(rs.getInt(4));
				od.setEod_cost(rs.getDouble(5));
				od.setEp_name(rs.getString(6));
				od.setEu_user_id(rs.getString(7));
				od.setEp_file_name(rs.getString(8));
				list.add(od);
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
	 * 获得单个订单详情对象
	 */
	public OrderDetail getById(int id) {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		OrderDetail od=null;
		
		try {
			conn=getConn();
			String sql="select * from OrderDetail where EOD_ID=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			rs=pst.executeQuery();
			
			if(rs.next()){
				od=new OrderDetail();
				od.setEod_id(rs.getInt(1));
				od.setEo_id(rs.getString(2));
				od.setEp_id(rs.getInt(3));
				od.setEod_quantity(rs.getInt(4));
				od.setEod_cost(rs.getDouble(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return od;
	}

}
