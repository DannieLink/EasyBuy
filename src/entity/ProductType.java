package entity;
/**
 * 商品类别实体类
 * @author pc
 *
 */
public class ProductType {
	private int epc_id;				//商品类别编号
	private String epc_name;		//商品类型名称
	public int getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(int epcId) {
		epc_id = epcId;
	}
	public String getEpc_name() {
		return epc_name;
	}
	public void setEpc_name(String epcName) {
		epc_name = epcName;
	}

}
