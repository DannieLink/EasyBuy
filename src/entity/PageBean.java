package entity;
import java.util.*;

/**
 * ��ҳ��Ϣʵ�壨�����ҳ��Ϣ��
 * @author a
 *
 */
public class PageBean<T> {
	public PageBean(){;
	
	}
	
	/**
	 * �ܹ���������¼
	 */
	private  int totalCount;
	/**
	 * �ܹ����Էֶ���ҳ
	 */
	private int totalPages;

	/**
	 * ��ǰҳ�� Ĭ�ϵ�һҳ
	 */
	private int currentNo=1;
    /**
     * ÿҳ��С Ĭ��10
     */
    private int pageSize=10;
    /**
     * ��ȡ�ܼ�¼��
     * @return
     */
	public int getTotalCount() {
		return totalCount;
	}
	  /**
     * �����ܼ�¼��
     * @return
     */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getTotalPages() {
		return totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	
	/**
	 * ��ȡ��ǰҳ�� Ĭ�ϵ�һҳ
	 * @return
	 */
	public int getCurrentNo() {
		return currentNo;
	}
	/**
	 * ���õ�ǰҳ��
	 * @param currentNo
	 */
	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}
	/**
	 * ��ȡÿҳ��С Ĭ��10
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * ����ÿҳ��С
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * ��ҳ����
	 */
    List<T> list=new ArrayList();
    /**
     * ��ȡ��ҳ����
     * @return
     */
	public List getList() {
		return list;
	}
	/**
	 * ���÷�ҳ����
	 * @param list
	 */
	public void setList(List list) {
		this.list = list;
	}
	
    
}
