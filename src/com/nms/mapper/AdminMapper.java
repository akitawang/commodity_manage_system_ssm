package com.nms.mapper;

import java.util.List;

import com.nms.pojo.Admin;

 

public interface AdminMapper {
	
	public int add(Admin admin); 
    
    public void delete(int id); 
        
    public Admin get(int id); 
      
    public int update(Admin admin);  
        
    public List<Admin> list();
     
    public int count(); 
    
    public Admin getuser_login(Admin admin); 
    
    public Admin getuser_findpass(Admin admin); 
    
    public int reset_pass(Admin admin); 
}
