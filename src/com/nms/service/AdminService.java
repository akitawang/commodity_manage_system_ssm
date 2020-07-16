package com.nms.service;

import java.util.List;

import com.nms.pojo.Admin;

 

public interface AdminService {
	List<Admin> list();
	public Admin getuser_login(Admin admin); 
	public int register_account(Admin admin); 
	public Admin getuser_findpass(Admin admin);
	public int update(Admin admin); 
	public int isChangePass(int id, String validkey);
	public int reset_pass(Admin admin); 
}
