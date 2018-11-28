package entity;
/**
 *
 * 订单详情
 * @author Administrator
 *
 */
public class OrderDetail {
	/**
	 * 订单详情编号
	 */
	private int eod_id;
	/**
	 * 订单编号
	 */
	private String eo_id;
	/**
	 * 商品类型编号
	 */
	private int ep_id;
	/**
	 * 商品数量
	 */
	private int eod_quantity;
	/**
	 * 商品价格
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
