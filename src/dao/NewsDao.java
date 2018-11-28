package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.News;
import inter.INews;

public class NewsDao extends BaseDao implements INews {
	@Override
	public int DeleteNews(int enId) {
		int num=0;
		String sql="delete from News where EN_ID=?";
		Object[] p={enId};
		num=execUpdate(sql, p);
		return num;
	}

	@Override
	public int InsertNews(News n) {
		int num=0;
		String sql="insert into News(EN_TITLE, EN_CONTENT) values(?,?)";
		Object[] p={n.getEn_title(),n.getEn_content()};
		num=execUpdate(sql, p);
		return num;
	}

	@Override
	public int UpdateNews(News n) {
		int num=0;
		String sql="update News set EN_TITLE=?, EN_CONTENT=? where EN_ID=?";
		Object[] p={n.getEn_title(),n.getEn_content(),n.getEn_id()};
		num=execUpdate(sql, p);
		return num;
	}
	@Override
	public List<News> getAllNewsBypage(int pageNo,int pageSize) {
		List<News> news=new ArrayList<News>();
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement st=null;
		conn=getConn();
		String sql="select top(?) * from News " +
				"  where EN_ID not in(select top(?) EN_ID from News  order by EN_ID desc)" +
				"  order by EN_ID desc";
		try {
			st=conn.prepareStatement(sql);
			st.setObject(1, pageSize);
			st.setObject(2, (pageNo-1)*pageSize);
			rs=st.executeQuery();
			
			while(rs.next()){
				News n=new News();
				n.setEn_id(rs.getInt(1));
				n.setEn_title(rs.getString(2));
				n.setEn_content(rs.getString(3));
				n.setEn_create_time(rs.getDate(4));
				news.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, st, conn);
		}
		return news;
	}
	
	
	@Override
	public List<News> getAllNews() {
		List<News> news=new ArrayList<News>();
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement st=null;
		
		conn=getConn();
		String sql="select * from News";
		
		try {
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				News n=new News();
				n.setEn_id(rs.getInt(1));
				n.setEn_title(rs.getString(2));
				n.setEn_content(rs.getString(3));
				n.setEn_create_time(rs.getDate(4));
				news.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, st, conn);
		}
		return news;
	}
	
	@Override
	public News getOneNewsByID(int enId) {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement st=null;
		News n=new News();
		conn=getConn();
		String sql="select * from News where EN_ID=?";
		try {
			st=conn.prepareStatement(sql);
			st.setObject(1, enId);
			rs=st.executeQuery();
			while(rs.next()){
				n.setEn_id(rs.getInt(1));
				n.setEn_title(rs.getString(2));
				n.setEn_content(rs.getString(3));
				n.setEn_create_time(rs.getDate(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, st, conn);
		}
		return n;
	}

	
	@Override
	public int getCount() {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement st=null;
		int count=0;
		conn=getConn();
		String sql="select count(*) from News";
		try {
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, st, conn);
		}
		return count;
	}
}
