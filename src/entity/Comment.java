package entity;

import java.sql.Date;

/**
 * ������
 * @author Administrator
 *
 */
public class Comment {
	private int ec_id;//����ID
	private String ec_content;//��������
	private Date ec_create_time;//����ʱ��
	private String ec_reply;//�ظ�
	private String ec_reply_time;//�ظ�ʱ��
	private String ec_nick_name;//�������ǳ�
	public int getEc_id() {
		return ec_id;
	}
	public void setEc_id(int ecId) {
		ec_id = ecId;
	}
	public String getEc_content() {
		return ec_content;
	}
	public void setEc_content(String ecContent) {
		ec_content = ecContent;
	}
	public Date getEc_create_time() {
		return ec_create_time;
	}
	public void setEc_create_time(Date ecCreateTime) {
		ec_create_time = ecCreateTime;
	}
	public String getEc_reply() {
		return ec_reply;
	}
	public void setEc_reply(String ecReply) {
		ec_reply = ecReply;
	}
	public String getEc_reply_time() {
		return ec_reply_time;
	}
	public void setEc_reply_time(String ecReplyTime) {
		ec_reply_time = ecReplyTime;
	}
	public String getEc_nick_name() {
		return ec_nick_name;
	}
	public void setEc_nick_name(String ecNickName) {
		ec_nick_name = ecNickName;
	}


}
