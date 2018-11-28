package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import inter.IUser;

public class UserDao extends BaseDao implements IUser{

	@Override
	public int regist(User user) {
		int result=0;
		String sql="insert into [User](EU_USER_ID,EU_USER_NAME,EU_PASSWORD," +
				"EU_SEX," +
				"EU_BIRTHDAY,EU_IDENTITY_CODE,EU_EMAIL,EU_MOBILE," +
				"EU_ADDRESS,EU_STATUS,EU_MONEY) " +
				"values(?,?,?,?,?,?,?,?,?,?,?)";
		
		Object[] p={user.getEu_user_id(),user.getEu_user_name(),
				user.getEu_password(),
				user.getEu_sex(),user.getEu_birthday(),
				user.getEu_identity_code(),
				user.getEu_email(),user.getEu_mobile(),
				user.getEu_address(),user.getEu_status(),
				user.getEu_money()};
		
		result=execUpdate(sql, p);
		return result;
	}

	@Override
	public User login(String userId, String password) {
		User u=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from [User] where EU_USER_ID=? and EU_PASSWORD=?";
		conn=getConn();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				u=new User();
				u.setEu_user_id(rs.getString(1));
				u.setEu_user_name(rs.getString(2));
				u.setEu_password(rs.getString(3));
				u.setEu_sex(rs.getString(4));
				u.setEu_birthday(rs.getString(5));
				
				u.setEu_identity_code(rs.getString(6));
				u.setEu_email(rs.getString(7));
				u.setEu_mobile(rs.getString(8));
				u.setEu_address(rs.getString(9));
				u.setEu_status(rs.getInt(10));
				u.setEu_money(rs.getDouble(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return u;
	}
	
	@Override
	public int delUser(int id) {
		int result=0;
		String sql="delete from [User] where EU_USER_ID=?";
		Object[] p={id};
		result=execUpdate(sql, p);
		return result;
	}

	@Override
	public int updateUser(User user) {
		int result=0;
		String sql="update [User] set EU_USER_ID=?,EU_USER_NAME=?," +
				"EU_PASSWORD=?,EU_SEX=?,EU_BIRTHDAY=?," +
				"EU_IDENTITY_CODE=?,EU_EMAIL=?,EU_MOBILE=?," +
				"EU_ADDRESS=? where EU_USER_ID=?";
		
		Object[] p={user.getEu_user_id(),user.getEu_user_name(),
				user.getEu_password(),user.getEu_sex(),user.getEu_birthday(),
				user.getEu_identity_code(),user.getEu_email(),
				user.getEu_mobile(),user.getEu_address(),
				user.getEu_user_id()};
		
		result=execUpdate(sql, p);
		return result;
	}

	@Override
	public User SearchUserById(String userId) {
		User u=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from [User] where EU_USER_ID=?";
		conn=getConn();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				u=new User();
				u.setEu_user_id(rs.getString(1));
				u.setEu_user_name(rs.getString(2));
				u.setEu_password(rs.getString(3));
				u.setEu_sex(rs.getString(4));
				u.setEu_birthday(rs.getString(5));
				
				u.setEu_identity_code(rs.getString(6));
				u.setEu_email(rs.getString(7));
				u.setEu_mobile(rs.getString(8));
				u.setEu_address(rs.getString(9));
				u.setEu_status(rs.getInt(10));
				u.setEu_money(rs.getDouble(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return u;
	}

	@Override
	/**
	 * 若是管理员，获取所有用户（分页显示）
	 */
	public List<User> getUserLists(int pageNo,int pageSize,String userId) {
		List<User> ulists=new ArrayList<User>();
		User u=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String uid="";
		try {
			conn=getConn();
			String sql="select top (?) * from [User]" +
			"  where EU_USER_ID not in " +
			"   (select top (?) EU_USER_ID from [User])";
			
			if(!userId.equals("admin")){
				sql+="	and EU_USER_ID=?";
				uid=userId;
			}
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pageSize);
			pstmt.setInt(2, (pageNo -1)*pageSize);
			
			if(uid!=""){
				pstmt.setString(3, uid);
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				u=new User();
				u.setEu_user_id(rs.getString(1));
				u.setEu_user_name(rs.getString(2));
				u.setEu_password(rs.getString(3));
				u.setEu_sex(rs.getString(4));
				u.setEu_birthday(rs.getString(5));
				u.setEu_identity_code(rs.getString(6));
				
				u.setEu_email(rs.getString(7));
				u.setEu_mobile(rs.getString(8));
				u.setEu_address(rs.getString(9));
				u.setEu_status(rs.getInt(10));
				u.setEu_money(rs.getDouble(11));
				
				ulists.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return ulists;
	}
	
	@Override
	public int delUser(String userId) {
		int result=0;
		String sql="delete from [User] where EU_USER_ID=?";
		Object[] p={userId};
		result=execUpdate(sql, p);
		return result;
	}
	
	@Override
	public int getUCount(User u) {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=getConn();
			String sql="select count(*) from [User]" ;
			
			if(u.getEu_status()==1){
				sql+=" where EU_USER_NAME=?";
			}
			
			pst=conn.prepareStatement(sql);
			
			if(u.getEu_status()==1){
				pst.setString(1, u.getEu_user_name());
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
	public int ChongMoney(double money,String euid) {
		int result=0;
		String sql="update [User] set EU_MONEY+=? where EU_USER_ID=?";
		Object[] p={money,euid};
		result=execUpdate(sql, p);
		return result;
	}

	@Override
	public int CutMoney(double money, String euid) {
		int result=0;
		String sql="update [User] set EU_MONEY-=? where EU_USER_ID=?";
		Object[] p={money,euid};
		result=execUpdate(sql, p);
		return result;
	}
	
}
