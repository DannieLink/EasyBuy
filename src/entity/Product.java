package entity;
/**
 * ��Ʒʵ����
 * @author pc
 *
 */
public class Product {
	private int ep_id;					//��Ʒ���
	private String ep_name;				//��Ʒ����
	private String ep_description;		//��Ʒ����
	private double ep_price;			//��Ʒ�۸�
	private int ep_stock;				//��Ʒ���
	private int epc_Id;					//��Ʒ�����
	private String ep_file_name;		//��ƷͼƬ����
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int epId) {
		ep_id = epId;
	}
	public String getEp_name() {
		return ep_name;
	}
	public void setEp_name(String epName) {
		ep_name = epName;
	}
	public String getEp_description() {
		return ep_description;
	}
	public void setEp_description(String epDescription) {
		ep_description = epDescription;
	}
	public double getEp_price() {
		return ep_price;
	}
	public void setEp_price(double epPrice) {
		ep_price = epPrice;
	}
	public int getEp_stock() {
		return ep_stock;
	}
	public void setEp_stock(int epStock) {
		ep_stock = epStock;
	}
	public int getEpc_Id() {
		return epc_Id;
	}
	public void setEpc_Id(int epcId) {
		epc_Id = epcId;
	}
	public String getEp_file_name() {
		return ep_file_name;
	}
	public void setEp_file_name(String epFileName) {
		ep_file_name = epFileName;
	}

}
