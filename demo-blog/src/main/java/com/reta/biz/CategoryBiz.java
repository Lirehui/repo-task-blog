package com.reta.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reta.bean.Category;
import com.reta.dao.CategoryMapper;

@Service
public class CategoryBiz {

	@Autowired
	private CategoryMapper cm;

	public List<Category> queryAll() {
		return cm.selectByExample(null);
	}
	
}
