package entity;
/**
 *
 * ��������
 * @author Administrator
 *
 */
public class OrderDetail {
	/**
	 * ����������
	 */
	private int eod_id;
	/**
	 * �������
	 */
	private String eo_id;
	/**
	 * ��Ʒ���ͱ��
	 */
	private int ep_id;
	/**
	 * ��Ʒ����
	 */
	private int eod_quantity;
	/**
	 * ��Ʒ�۸�
	 */
	private double eod_cost;
	
	public int getEod_id() {
		return eod_id;
	}
	public void setEod_id(int eodId) {
		eod_id = eodId;
	}
	public String getEo_id() {
		return eo_id;
	}
	public void setEo_id(String eoId) {
		eo_id = eoId;
	}
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int epId) {
		ep_id = epId;
	}
	public int getEod_quantity() {
		return eod_quantity;
	}
	public void setEod_quantity(int eodQuantity) {
		eod_quantity = eodQuantity;
	}
	public double getEod_cost() {
		return eod_cost;
	}
	public void setEod_cost(double eodCost) {
		eod_cost = eodCost;
	}

}
