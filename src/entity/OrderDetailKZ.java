package entity;
/**
 * �������������չ��
 * @author Administrator
 *
 */
public class OrderDetailKZ extends OrderDetail{
	/**
	 * �û���ţ��û��ǳƣ�
	 */
	private  String eu_user_id;
	/**
	 * ��Ʒ����
	 */
	private String ep_name;
	
	/**
	 * ��ƷͼƬ����
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
