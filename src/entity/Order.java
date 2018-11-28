package entity;

import java.sql.Date;

/**
 * 订单表
 * @author Administrator
 *
 */
public class Order {
	/**
	 * 订单编号
	 */
	private  String eo_id;
	/**
	 * 用户编号（用户昵称）
	 */
	private  String eu_user_id;
	/**
	 * 订单创建时间
	 */
	private  Date eo_create_time;
	/**
	 * 订单金额
	 */
	private double eo_cost;
	/**
	 * 订单状态
	 * （1：未发货）
	 * （2：已发货）
	 * （3：交易成功）
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
