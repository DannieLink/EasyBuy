package entity;
/**
 * 订单详情类的扩展类
 * @author Administrator
 *
 */
public class OrderDetailKZ extends OrderDetail{
	/**
	 * 用户编号（用户昵称）
	 */
	private  String eu_user_id;
	/**
	 * 商品名称
	 */
	private String ep_name;
	
	/**
	 * 商品图片名称
	 */
	private String ep_file_name;		
	
	public String getEp_file_name() {
		return ep_file_name;
	}
	public void setEp_file_name(String epFileName) {
		ep_file_name = epFileName;
	}
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String euUserId) {
		eu_user_id = euUserId;
	}
	public String getEp_name() {
		return ep_name;
	}
	public void setEp_name(String epName) {
		ep_name = epName;
	}				

}
