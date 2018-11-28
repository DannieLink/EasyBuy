package entity;
/**
 * 购物实体类
 * @author pc
 *
 */
public class Shopping {
	private int es_id;  //购物ID
	private int ep_id;	//商品ID
	private String eu_user_id;  //用户ID
	private String p_name;  //商品名称
	private double p_price;	//商品价格
	private int quantity;	//商品数量
	
	public int getEs_id() {
		return es_id;
	}
	public void setEs_id(int esId) {
		es_id = esId;
	}
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int epId) {
		ep_id = epId;
	}
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String euUserId) {
		eu_user_id = euUserId;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String pName) {
		p_name = pName;
	}
	public double getP_price() {
		return p_price;
	}
	public void setP_price(double pPrice) {
		p_price = pPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
