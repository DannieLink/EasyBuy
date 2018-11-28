package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Comment;
import entity.User;
import inter.IComment;

/**
 * ¡Ù—‘ µœ÷¿‡
 * @author Administrator
 *
 */
public class CommentDao extends BaseDao implements IComment{

	@Override
	public int DeleteComment(int ecId) {
		int num=0;
		String sql="delete from Comment where EC_ID=?";
		Object[] p={ecId};
		num=execUpdate(sql, p);
		return num;
	}

	@Override
	public int InsertComment(Comment c) {
		int num=0;
		String sql="insert into Comment(EC_CONTENT, EC_NICK_NAME) values(?,?)";
		Object[] p={c.getEc_content(),c.getEc_nick_name()};
		num=execUpdate(sql, p);
		return num;
	}

	@Override
	public int UpdateComment(Comment c) {
		int num=0;
		String sql="update Comment set EC_REPLY=?, EC_REPLY_TIME=getDate() where EC_ID=?";
		Object[] p={c.getEc_reply(),c.getEc_id()};
		num=execUpdate(sql, p);
		return num;
	}

	@Override
	public List<Comment> getAllComment(int pageNo,int pageSize,User u) {
			List<Comment> Comments=new ArrayList<Comment>();
			Connection conn=null;
			ResultSet rs=null;
			PreparedStatement st=null;
			
			try {
			conn=getConn();
			String sql="select top(?) * from Comment " +
					" where EC_ID not in" +
					" (select top(?) EC_ID from Comment order by EC_CREATE_TIME desc)";
			
			if(u!=null&&u.getEu_status()==1)
			{
				sql+=" and EC_NICK_NAME=?";
			}
			
			 sql+="  order by EC_CREATE_TIME desc";
			 
			st=conn.prepareStatement(sql);
			st.setObject(1, pageSize);
			st.setObject(2, (pageNo-1)*pageSize);
			
			if(u!=null&&u.getEu_status()==1){
				st.setString(3,u.getEu_user_name());
			}
			
				rs=st.executeQuery();
				while(rs.next()){
					Comment c=new Comment();
					c.setEc_id(rs.getInt(1));
					c.setEc_content(rs.getString(2));
					c.setEc_create_time(rs.getDate(3));
					c.setEc_reply(rs.getString(4));
					c.setEc_reply_time(rs.getString(5));
					c.setEc_nick_name(rs.getString(6));
					Comments.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeAll(rs, st, conn);
			}
			return Comments;
		}

	

	@Override
	public Comment getOneCommentByID(int ecId) {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement st=null;
		Comment c=new Comment();
		conn=getConn();
		
		String sql="select * from Comment where EC_ID=?";
		try {
			st=conn.prepareStatement(sql);
			st.setObject(1, ecId);
			rs=st.executeQuery();
			
			while(rs.next()){
				c.setEc_id(rs.getInt(1));
				c.setEc_content(rs.getString(2));
				c.setEc_create_time(rs.getDate(3));
				c.setEc_reply(rs.getString(4));
				c.setEc_reply_time(rs.getString(5));
				c.setEc_nick_name(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, st, conn);
		}
		return c;
	}
	
	@Override
	public int getCount(User u) {
		int count=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			conn=getConn();
			String sql="select count(*) from Comment";
			
			if(u!=null&&u.getEu_status()==1){
				sql+=" where EC_NICK_NAME=?";
			}
			pst=conn.prepareStatement(sql);
			
			if(u!=null&&u.getEu_status()==1){
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
}
