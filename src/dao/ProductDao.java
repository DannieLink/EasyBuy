package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Product;
import inter.IProduct;

/**
 * ��Ʒʵ����
 * @author pc
 *
 */
public class ProductDao extends BaseDao implements IProduct{
	/**
	 * �����Ʒ
	 */
	public int addProduct(Product pro) {
		int num = 0;
		String sql ="insert into Product(ep_name, ep_description, ep_price," +
				" ep_stock, epc_Id, ep_file_name) values(?,?,?,?,?,?)";
		
		Object[] params={pro.getEp_name(),pro.getEp_description(),
				pro.getEp_price(),pro.getEp_stock(),pro.getEpc_Id(),
				pro.getEp_file_name()};
		num=execUpdate(sql, params);
		return num;
	}
	/**
	 * ���������Ʒ��Ϣ�ķ���
	 */
	public ArrayList<Product> getAllProduct() {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ArrayList<Product> list = new ArrayList<Product>();
		Product pro = null;
		
		try {
			String sql = "select * from Product order by EP_ID desc";
			conn= getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pro=new Product();
				pro.setEp_id(rs.getInt(1));
				pro.setEp_name(rs.getString(2));
				pro.setEp_description(rs.getString(3));
				pro.setEp_price(rs.getDouble(4));
				pro.setEp_stock(rs.getInt(5));
				pro.setEpc_Id(rs.getInt(6));
				pro.setEp_file_name(rs.getString(7));
				
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,ps,conn);
		}
		return list;
	}
	/**
	 * * ������ƷId�����Ʒ��Ϣ
	 * @param ��ƷId
	 * @return ��Ʒ
	 */
	 
	public Product getProductById(int id) {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		Product pro = null;
		
		try {
			String sql = "select * from Product where EP_ID=?";
			conn= getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pro=new Product();
				pro.setEp_id(rs.getInt(1));
				pro.setEp_name(rs.getString(2));
				pro.setEp_description(rs.getString(3));
				pro.setEp_price(rs.getDouble(4));
				
				pro.setEp_stock(rs.getInt(5));
				pro.setEpc_Id(rs.getInt(6));
				pro.setEp_file_name(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,ps,conn);
		}
		return pro;
	}
	/**
	 * ������ƷIdɾ����Ʒ��Ϣ
	 */
	public int selectProductById(int id) {
		int num = 0;
		String sql ="delete from Product where EP_ID=? ";
		Object[] params={id};
		num=execUpdate(sql, params);
		return num;
	}
	/**
	 * ������ƷId�޸���Ʒ��Ϣ
	 */
	public int updateProductById(Product pro) {
		int num = 0;
		String sql ="update Product set ep_name=?,ep_description=?," +
				"ep_price=?,ep_stock=?,  epc_Id=?,  ep_file_name=?" +
				" where EP_ID=?";
		
		Object[] params={pro.getEp_name(),pro.getEp_description(),
				pro.getEp_price(),pro.getEp_stock(),pro.getEpc_Id(),
				pro.getEp_file_name(),pro.getEp_id()};
		
		num=execUpdate(sql, params);
		return num;
	}
	
	
	/**
	 * * ������Ʒ���Id�����Ʒ��Ϣ
	 * @param ��ƷId
	 * @return ��Ʒ
	 */
	public ArrayList<Product> getAllProduct(int epcId) {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ArrayList<Product> list = new ArrayList<Product>();
		Product pro = null;
		try {
			String sql = "select * from Product where EPC_ID=?  " +
					"order by EP_ID desc";
			conn= getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, epcId);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pro=new Product();
				pro.setEp_id(rs.getInt(1));
				pro.setEp_name(rs.getString(2));
				pro.setEp_description(rs.getString(3));
				pro.setEp_price(rs.getDouble(4));
				
				pro.setEp_stock(rs.getInt(5));
				pro.setEpc_Id(rs.getInt(6));
				pro.setEp_file_name(rs.getString(7));
				list.add(pro);
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
	 * �в���epcIdʱ����ҳ��ʾָ����Ʒ�����µ���Ʒ����
	 * �޲���epcIdʱ����ҳ��ʾ������Ʒ����
	 * @param pageNo
	 * @param pageSize
	 * @param epcId
	 * @return
	 */
	public ArrayList<Product> getAllProduct(int pageNo, int pageSize, int epcId) {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ArrayList<Product> list = new ArrayList<Product>();
		Product pro = null;
		try {
			conn= getConn();
			String sql="select top (?) * from Product" +
			"  where EP_ID not in  (select top (?) EP_ID " +
			"from Product where EPC_ID=? order by EP_ID desc)" +
				" and EPC_ID=?  order by EP_ID desc";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pageSize);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, epcId);
			ps.setInt(4, epcId);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				pro=new Product();
				pro.setEp_id(rs.getInt(1));
				pro.setEp_name(rs.getString(2));
				pro.setEp_description(rs.getString(3));
				pro.setEp_price(rs.getDouble(4));
				
				pro.setEp_stock(rs.getInt(5));
				pro.setEpc_Id(rs.getInt(6));
				pro.setEp_file_name(rs.getString(7));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs,ps,conn);
		}
		return list;
	}
	
	
	/**
	 * ����Ʒ��ҳ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<Product> getAllProduct(int pageNo, int pageSize) {
		ResultSet rs=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ArrayList<Product> list = new ArrayList<Product>();
		Product pro = null;
		
		try {
			String sql="select top (?) * from Product" +
			"  where EP_ID not in " +
			"   (select top (?) EP_ID from Product  order by EP_ID desc) " +
			"   order by EP_ID desc";
			
			conn= getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pageSize);
			ps.setInt(2, (pageNo-1)*pageSize);
			rs=ps.executeQuery();
			
			while(rs.next()){
				pro=new Product();
				pro.setEp_id(rs.getInt(1));
				pro.setEp_name(rs.getString(2));
				pro.setEp_description(rs.getString(3));
				pro.setEp_price(rs.getDouble(4));
				
				pro.setEp_stock(rs.getInt(5));
				pro.setEpc_Id(rs.getInt(6));
				pro.setEp_file_name(rs.getString(7));
				list.add(pro);
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
	 * �޲�epcIdʱ����ѯ������Ʒ������
	 * �в���epcIdʱ��������Ʒ����ID��ѯָ����Ʒ����
	 * @param epcId
	 * @return
	 */
	public int getCount(int epcId) {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			String sql="select count(*) from product " ;
			
			if(epcId!=0){
				sql+="  where EPC_ID=?" ;
			}
			
			pst=conn.prepareStatement(sql);
			
			if(epcId!=0){
				pst.setInt(1, epcId);
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

	
	/**
	 * ���¿��
	 */
	public int getProductNewStock(int epid, int num) {
		int number = 0;
		String sql ="update Product  set EP_STOCK=EP_STOCK-?  where EP_ID=?";
		Object[] params={num,epid};
		number=execUpdate(sql, params);
		return number;
	}
}
