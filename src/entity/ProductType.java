package entity;
/**
 * ��Ʒ���ʵ����
 * @author pc
 *
 */
public class ProductType {
	private int epc_id;				//��Ʒ�����
	private String epc_name;		//��Ʒ��������
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
