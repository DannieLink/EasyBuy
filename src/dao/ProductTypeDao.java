package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ProductType;
import inter.IProductType;

/**
 * ��Ʒ���ʵ����
 * @author pc
 *
 */
public class ProductTypeDao extends BaseDao implements IProductType{
	/**
	 * ��ѯ������Ʒ���
	 */
	public ArrayList<ProductType> getAllProductType() {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ProductType pt=null;
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		
		try {
			String sql = "select * from ProductType";
			conn= getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pt=new ProductType();
				pt.setEpc_id(rs.getInt(1));
				pt.setEpc_name(rs.getString(2));
				list.add(pt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,ps,conn);
		}
		return list;
	}

	@Override
	public int addProductType(ProductType pt) {
		int num = 0;
		String sql = "insert into ProductType(EPC_NAME) values(?)";
		Object[] params={pt.getEpc_name()};
		num = execUpdate(sql, params);
		return num;
	}

	@Override
	public int delProductTypeById(int id) {
		int num = 0;
		String sql = "delete from ProductType where EPC_ID=?";
		Object[] params={id};
		num = execUpdate(sql, params);
		return num;
	}

	@Override
	public int updateProductType(ProductType pt) {
		int num = 0;
		String sql = "update ProductType set EPC_NAME=? where EPC_ID=?";
		Object[] params={pt.getEpc_name(),pt.getEpc_id()};
		num = execUpdate(sql, params);
		return num;
	}
	
	/**
	 * ����Ʒ����ҳ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<ProductType> getAllProductType(int pageNo, int pageSize) {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		ProductType pt = null;
		try {
			String sql="select top (?) * from ProductType" +
			"  where EPC_ID not in " +
			"   (select top (?) EPC_ID from ProductType) ";
			
			conn= getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pageSize);
			ps.setInt(2, (pageNo-1)*pageSize);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pt=new ProductType();
				pt.setEpc_id(rs.getInt(1));
				pt.setEpc_name(rs.getString(2));
				list.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,ps,conn);
		}
		return list;
		
	}
	@Override
	/**
	 * �����Ʒ����������
	 */
	public int getCount() {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			String sql="select count(*) from ProductType" ;
			pst=conn.prepareStatement(sql);
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
	
	/**
	 * ��ѯ��Ʒ���
	 * @param epcid
	 * @param epcname
	 * @return ��Ʒ���
	 */
	 
	public ProductType getType(int epcid, String epcname) {
		ProductType pt=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			String sql="select * from ProductType " +
					"where EPC_ID <>? and EPC_NAME=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, epcid);
			ps.setString(2, epcname);
			rs=ps.executeQuery();
			
			if(rs.next()){
				pt=new ProductType();
				pt.setEpc_id(rs.getInt(1));
				pt.setEpc_name(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, ps, conn);
		}
		return pt;
	}
	
	/**
	 * ������Ʒ���������Ʒ���
	 * @param epcid
	 * @param epcname
	 * @return ��Ʒ���
	 */
	 
	public ProductType getType(String epcname) {
		ProductType pt=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=getConn();
			String sql="select * from ProductType where EPC_NAME=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, epcname);
			rs=ps.executeQuery();
			
			if(rs.next()){
				pt=new ProductType();
				pt.setEpc_id(rs.getInt(1));
				pt.setEpc_name(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, ps, conn);
		}
		return pt;
	}

	
	/**
	 * ������Ʒ����ȡ��Ʒ������
	 * @param epcid
	 * @return ��Ʒ������
	 */
	public ProductType getType(int epcid) {
		ProductType pt=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=getConn();
			String sql="select * from ProductType where EPC_ID=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, epcid);
			rs=ps.executeQuery();
			
			if(rs.next()){
				pt=new ProductType();
				pt.setEpc_id(rs.getInt(1));
				pt.setEpc_name(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, ps, conn);
		}
		return pt;
	}
}
