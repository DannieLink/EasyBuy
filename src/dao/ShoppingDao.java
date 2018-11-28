package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import entity.Shopping;
import inter.IShopping;

/**
 * 购物车实现类
 * @author pc
 *
 */
public class ShoppingDao extends BaseDao implements IShopping {

	
	@Override
	public int delShopGoods(int epid,String euid) {
		int result=0;
		String sql="delete from Shopping where EP_ID=? and EU_USER_ID=?";
		Object[] p={epid,euid};
		result=execUpdate(sql, p);
		return result;
	}

	@Override
	public List<Product> getShopList(String euid) {
		List<Product> shoplists=new ArrayList<Product>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from Product p where p.EP_ID in" +
				"(select s.EP_ID " +
				"from Shopping s where s.EU_USER_ID=?)";
		
		conn=getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, euid);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Product pro=new Product();
				pro.setEp_id(rs.getInt("EP_ID"));
				pro.setEp_name(rs.getString("EP_NAME"));
				pro.setEp_price(rs.getDouble("EP_PRICE"));
				
				pro.setEp_stock(rs.getInt("EP_STOCK"));
				pro.setEp_file_name(rs.getString("EP_FILE_NAME"));
				shoplists.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return shoplists;
	}

	@Override
	public int addShopGoods(int epid,String euid) {
		int result=0;
		String sql="insert into Shopping(EP_ID,EU_USER_ID) values(?,?)";
		Object[] p={epid,euid};
		result=execUpdate(sql, p);
		return result;
		
	}

	@Override
	public int getShopCount(int epid, String euid) {
		int count=0;
		String sql="select count(*) from Shopping where EP_ID=? " +
				"and EU_USER_ID=?";
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=getConn();
		
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, epid);
			pst.setString(2, euid);
			rs=pst.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return count;
	}

	@Override
	public int getTotalCount(String euid) {
		int count=0;
		//去掉重复的商品查询
		String sql="select count(distinct EP_ID) from Shopping " +
				"where EU_USER_ID=?";
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		conn=getConn();
		
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, euid);
			rs=pst.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		return count;
	}

	

}
