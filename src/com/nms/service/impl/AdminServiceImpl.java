package com.nms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nms.mapper.AdminMapper;
import com.nms.pojo.Admin;
import com.nms.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminMapper adminmapper;
	
	@Override
	public List<Admin> list() {
		return adminmapper.list();
	}

	@Override
	public Admin getuser_login(Admin admin) {
		return adminmapper.getuser_login(admin);
	}

	@Override
	public int register_account(Admin admin) {
		return adminmapper.add(admin);
	}

	@Override
	public Admin getuser_findpass(Admin admin) {
		return adminmapper.getuser_findpass(admin);
	}

	@Override
	public int update(Admin admin) {
		return adminmapper.update(admin);
	}

	@Override
	public int isChangePass(int id, String validkey) {
		int judge = -1;
		Admin result = adminmapper.get(id);
		if(result == null) {
			judge = 1;
			return judge;
		}
		else if(!validkey.equals(result.getSignature())){
			judge = 2;
			return judge;
		}
		else {
			long current = System.currentTimeMillis();
			long time = result.getOutdate().getTime();
			if(current> time){
				judge = 3;
				return judge;
			}
			else{
				judge = 4;
				return judge;
			}
		}
		
	}

	@Override
	public int reset_pass(Admin admin) {
		return adminmapper.reset_pass(admin);
	}
}
