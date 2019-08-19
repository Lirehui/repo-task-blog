package com.reta.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reta.bean.User;
import com.reta.bean.UserExample;
import com.reta.dao.UserMapper;

@Service
public class UserBiz {
	@Resource
	private UserMapper um;
	
	public User login(String name,String password) throws BizException{
		/**
		 * 验证代码省略
		 */
		UserExample ue=new UserExample();
		ue.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> list=um.selectByExample(ue);
		if(list.size()==0){
			throw new BizException("用户名或密码错误！");
		}
		return list.get(0);
	}
}
