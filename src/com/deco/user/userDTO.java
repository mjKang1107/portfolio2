package com.deco.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class userDTO {
	
	private int user_num;	
	private String email;
	private String pw;
	private String pre_pw;
	private String name;
	private String nickname;
	private String addr;
	private String phone;
	private String major;
	private String inter;
	private String create_at;
	private String last_login;
	private String change_pw;
	private int private_user;
	private int point;
	private int admin_auth;
	private String email_auth;
	
	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPre_pw() {
		return pre_pw;
	}

	public void setPre_pw(String pre_pw) {
		this.pre_pw = pre_pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getChange_pw() {
		return change_pw;
	}

	public void setChange_pw(String change_pw) {
		this.change_pw = change_pw;
	}

	public int getPrivate_user() {
		return private_user;
	}

	public void setPrivate_user(int private_user) {
		this.private_user = private_user;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getAdmin_auth() {
		return admin_auth;
	}

	public void setAdmin_auth(int admin_auth) {
		this.admin_auth = admin_auth;
	}
	
	public String getEmail_auth() {
		return email_auth;
	}

	public void setEmail_auth(String email_auth) {
		this.email_auth = email_auth;
	}

	public void setReq(HttpServletRequest req){
		email = req.getParameter("email");
		pw = req.getParameter("pw");
		name = req.getParameter("name");
		nickname = req.getParameter("nickname");
		addr = req.getParameter("addr");
		phone = req.getParameter("phone");

		String[] getMajor = req.getParameterValues("majorContainer");
		String[] getInter = req.getParameterValues("interContainer");
		
		if(getMajor != null){
			major = "";
			for(int i = 0; i < getMajor.length; i++){
				major += getMajor[i]+(i != getMajor.length-1 ? "," : "");
			}
		}
		
		if(getInter != null){
			inter = "";
			for(int i = 0; i < getInter.length; i++){
				inter += getInter[i]+(i != getInter.length-1 ? "," : "");
			}
		}
	}
	
	public void setRs(ResultSet rs) throws SQLException{
		user_num = rs.getInt(1);	
		email = rs.getString(2);
		pw = rs.getString(3);
		pre_pw=rs.getString(4);
		name= rs.getString(5);
		nickname= rs.getString(6);
		addr= rs.getString(7);
		phone= rs.getString(8);
		major= rs.getString(9);
		inter= rs.getString(10);
		create_at= rs.getString(11);
		last_login= rs.getString(12);
		change_pw= rs.getString(13);
		private_user= rs.getInt(14);
		point= rs.getInt(15);
		admin_auth= rs.getInt(16);
		email_auth= rs.getString(17);
	}

	@Override
	public String toString() {
		return "userDTO [user_num=" + user_num + ", addr=" + addr + ", name=" + name + ", nickname=" + nickname
				+ ", major=" + major + ", inter=" + inter + ", pw=" + pw + ", create_at=" + create_at + ", last_login="
				+ last_login + ", email=" + email + ", phone=" + phone + ", private_user=" + private_user + ", point="
				+ point + ", change_pw=" + change_pw + ", pre_pw=" + pre_pw + ", admin_auth=" + admin_auth + "]";
	}    
}
