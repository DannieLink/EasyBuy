package entity;

import java.sql.Date;

/**
 * ������
 * @author Administrator
 *
 */
public class Order {
	/**
	 * �������
	 */
	private  String eo_id;
	/**
	 * �û���ţ��û��ǳƣ�
	 */
	private  String eu_user_id;
	/**
	 * ��������ʱ��
	 */
	private  Date eo_create_time;
	/**
	 * �������
	 */
	private double eo_cost;
	/**
	 * ����״̬
	 * ��1��δ������
	 * ��2���ѷ�����
	 * ��3�����׳ɹ���
	 */
	private  int eo_status;
	
	public String getEo_id() {
		return eo_id;
	}
	public void setEo_id(String eoId) {
		eo_id = eoId;
	}
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String euUserId) {
		eu_user_id = euUserId;
	}
	public Date getEo_create_time() {
		return eo_create_time;
	}
	public void setEo_create_time(Date eoCreateTime) {
		eo_create_time = eoCreateTime;
	}
	public double getEo_cost() {
		return eo_cost;
	}
	public void setEo_cost(double eoCost) {
		eo_cost = eoCost;
	}
	public int getEo_status() {
		return eo_status;
	}
	public void setEo_status(int eoStatus) {
		eo_status = eoStatus;
	}

}
