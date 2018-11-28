package entity;
/**
 * 用户实体类
 * @author pc
 *
 */
public class User {
	private String eu_user_id;		   //用户名
	private String eu_user_name;	   //真实姓名
	private String eu_password;		   //密码
	private String eu_sex;			   //性别
	private String eu_birthday;        //出生日期
	private String eu_identity_code;   //身份证
	private String eu_email;           //电子邮件
	private String eu_mobile;          //手机
	private String eu_address;         //收货地址
	private int eu_status;             //1：普通用户  2：管理员
	private double eu_money;           //充值金额
	
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String euUserId) {
		eu_user_id = euUserId;
	}
	public String getEu_user_name() {
		return eu_user_name;
	}
	public void setEu_user_name(String euUserName) {
		eu_user_name = euUserName;
	}
	public String getEu_password() {
		return eu_password;
	}
	public void setEu_password(String euPassword) {
		eu_password = euPassword;
	}
	public String getEu_sex() {
		return eu_sex;
	}
	public void setEu_sex(String euSex) {
		eu_sex = euSex;
	}
	public String getEu_birthday() {
		return eu_birthday;
	}
	public void setEu_birthday(String euBirthday) {
		eu_birthday = euBirthday;
	}
	public String getEu_identity_code() {
		return eu_identity_code;
	}
	public void setEu_identity_code(String euIdentityCode) {
		eu_identity_code = euIdentityCode;
	}
	public String getEu_email() {
		return eu_email;
	}
	public void setEu_email(String euEmail) {
		eu_email = euEmail;
	}
	public String getEu_mobile() {
		return eu_mobile;
	}
	public void setEu_mobile(String euMobile) {
		eu_mobile = euMobile;
	}
	public String getEu_address() {
		return eu_address;
	}
	public void setEu_address(String euAddress) {
		eu_address = euAddress;
	}
	public int getEu_status() {
		return eu_status;
	}
	public void setEu_status(int euStatus) {
		eu_status = euStatus;
	}
	public double getEu_money() {
		return eu_money;
	}
	public void setEu_money(double euMoney) {
		eu_money = euMoney;
	}
	
	
	
}
