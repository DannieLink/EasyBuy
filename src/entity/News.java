package entity;

import java.sql.Date;

/**
 * ������
 * @author Administrator
 *
 */
public class News {
	private int en_id; //����ID
	private String en_title;//���ű���
	private String en_content;//��������
	private Date en_create_time;//����ʱ��
	public int getEn_id() {
		return en_id;
	}
	public void setEn_id(int enId) {
		en_id = enId;
	}
	public String getEn_title() {
		return en_title;
	}
	public void setEn_title(String enTitle) {
		en_title = enTitle;
	}
	public String getEn_content() {
		return en_content;
	}
	public void setEn_content(String enContent) {
		en_content = enContent;
	}
	public Date getEn_create_time() {
		return en_create_time;
	}
	public void setEn_create_time(Date enCreateTime) {
		en_create_time = enCreateTime;
	}


}
