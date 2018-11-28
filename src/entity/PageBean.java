package entity;
import java.util.*;

/**
 * 分页信息实体（保存分页信息）
 * @author a
 *
 */
public class PageBean<T> {
	public PageBean(){;
	
	}
	
	/**
	 * 总共多少条记录
	 */
	private  int totalCount;
	/**
	 * 总共可以分多少页
	 */
	private int totalPages;

	/**
	 * 当前页号 默认第一页
	 */
	private int currentNo=1;
    /**
     * 每页大小 默认10
     */
    private int pageSize=10;
    /**
     * 获取总记录数
     * @return
     */
	public int getTotalCount() {
		return totalCount;
	}
	  /**
     * 设置总记录数
     * @return
     */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPages() {
		return totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	
	/**
	 * 获取当前页号 默认第一页
	 * @return
	 */
	public int getCurrentNo() {
		return currentNo;
	}
	/**
	 * 设置当前页号
	 * @param currentNo
	 */
	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}
	/**
	 * 获取每页大小 默认10
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置每页大小
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 分页数据
	 */
    List<T> list=new ArrayList();
    /**
     * 获取分页数据
     * @return
     */
	public List getList() {
		return list;
	}
	/**
	 * 设置分页数据
	 * @param list
	 */
	public void setList(List list) {
		this.list = list;
	}
	
    
}
