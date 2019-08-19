package com.reta.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reta.bean.Category;
import com.reta.dao.CategoryMapper;

@Service
public class CategoryBiz {
	@Resource
	private CategoryMapper cm;
	
	public List<Category> queryAll(){
		return cm.selectByExample(null);
	}
}
