package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ������
 * @author Administrator
 *
 */
public class BaseDao {
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433;databasename=EasyBuyDB";
	String user="sa";
	String pwd="ok";
	
	/**
	 * ������ݿ�����
	 * @return
	 */
	public Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ���Դ
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public void closeAll(ResultSet rs,Statement st,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(st!=null){
				st.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ִ����ɾ��
	 * @return
	 */
	public int execUpdate(String sql,Object[] p){
		int num=0;
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=getConn();
			pst=conn.prepareStatement(sql);
			if(p!=null){
				for (int i = 0; i < p.length; i++) {
					pst.setObject(i+1, p[i]);
				}
			}
			num=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(null, pst, conn);
		}
		return num;
	}

}
