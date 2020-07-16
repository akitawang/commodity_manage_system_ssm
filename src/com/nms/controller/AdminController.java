package com.nms.controller;

 

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.nms.pojo.Admin;
import com.nms.service.AdminService;
import com.nms.util.MD5Util;
import com.nms.util.SendMail;



@Controller
@RequestMapping("")
public class AdminController {
	@Autowired
	AdminService adminservice;
	
	
	@RequestMapping("logAdmin")
    public ModelAndView login_check(Admin admin,HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Admin adminn = adminservice.getuser_login(admin);
        
        HttpSession session=request.getSession();
        session.setAttribute("user",adminn);
        // 放入转发参数
        mav.addObject("user", adminn);
        // 放入jsp路径
        mav.setViewName("login.jsp");
        return mav;
    }
	
	@RequestMapping("regAdmin")
    public void register_account(Admin admin,HttpServletResponse response) throws IOException  {
        int result = adminservice.register_account(admin);
		response.setContentType("text/javascript");
		response.getWriter().print(result);
    }
	
	@RequestMapping("outAdmin")
    public ModelAndView outAdmin(Admin admin,HttpServletRequest request) throws IOException  {
		ModelAndView mav = new ModelAndView();
        request.getSession().invalidate();
        mav.setViewName("login.jsp");
        return mav;
    }
	
	@RequestMapping("findpassAdmin")
    public void findpass_check(Admin admin,HttpServletResponse response) throws AddressException, MessagingException, IOException{
 
        Admin adminn = adminservice.getuser_findpass(admin);
        int j = -1;
		//若账号邮箱不匹配。则返回null，ajax判断，显示失败
		if(adminn == null) {
			response.setContentType("text/javascript");
			response.getWriter().print(j);
		}
		//若账号邮箱匹配，则发送邮件
		else {
			long currentTime = System.currentTimeMillis() + 900000;
			Date time = new Date(currentTime);
			Timestamp ts = new Timestamp(time.getTime());
			Random random = new Random();
			String key = adminn.getId() + "|" + ts + "|" + random.nextInt();
			String signature = MD5Util.MD5(key);
			adminn.setOutdate(ts);
			adminn.setSignature(signature);
			int judge_update = adminservice.update(adminn);
			if(judge_update > 0)
			{
				SendMail sendmail = new SendMail();
				String url = "http://localhost:8080/commodity_manage_system_ssm/isChangeAdmin"+"?uid=" + adminn.getId() + "&validkey=" + signature;
				sendmail.send(adminn.getUseremail(), url, adminn);
				j = 1;
				response.setContentType("text/javascript");
				response.getWriter().print(j);
			}
			else {
				response.setContentType("text/javascript");
				response.getWriter().print(j);
			}
        
		}
	}
	
	@RequestMapping("isChangeAdmin")
    public ModelAndView isChangeAdmin(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("uid"));
		String validkey = request.getParameter("validkey");
		int judge = adminservice.isChangePass(id,validkey);
		
		if(judge == 1 ) {
			mav.setViewName("reset_systemfault.jsp");
	        return mav;
		}
		else if(judge == 3 || judge == 2)  {
			mav.setViewName("reset_timeout.jsp");
	        return mav;
		}
		else {
			mav.addObject("id", id);
			mav.setViewName("resetpass.jsp");
	        return mav;
		}
    }
	
	@RequestMapping("resetpassAdmin")
    public void resetpassAdmin(Admin admin,HttpServletResponse response) throws IOException{
        int result = adminservice.reset_pass(admin);
        response.setContentType("text/javascript");
		response.getWriter().print(result);
    }
}
