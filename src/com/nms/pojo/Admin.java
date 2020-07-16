package com.nms.pojo;

import java.sql.Timestamp;

public class Admin {
	private int id;
	private String useraccount;
	private String userpass;
	private String userphone;
	private String username;
	private String usertype;
	private String useremail;
	private Timestamp outdate;
	private String signature;
	private String per_certification;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public Timestamp getOutdate() {
		return outdate;
	}
	public void setOutdate(Timestamp ts) {
		this.outdate = ts;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPer_certification() {
		return per_certification;
	}
	public void setPer_certification(String per_certification) {
		this.per_certification = per_certification;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", useraccount=" + useraccount + ", userpass=" + userpass + ", userphone="
				+ userphone + ", username=" + username + ", usertype=" + usertype + ", useremail=" + useremail
				+ ", outdate=" + outdate + ", signature=" + signature + ", per_certification=" + per_certification
				+ "]";
	}
	
	
}
