package com.reta.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.reta.bean.Article;
import com.reta.bean.ArticleExample;
import com.reta.bean.Category;
import com.reta.bean.CategoryExample;
import com.reta.dao.ArticleMapper;
import com.reta.dao.CategoryMapper;

@Service
public class ArticleBiz {

	@Autowired
	private ArticleMapper am;
	
//	@Autowired
//	private CategoryMapper cm;
	
	/**
	 * 查询最新发布的文章
	 * page页数
	 */
	public List<Article> queryNewArticle(int page){
		ArticleExample example = new ArticleExample();
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page, 5);
		//此处应使用selectByExampleWithBLOBs，而不能使用selectByExample，因为数据太大selectByExample取不到内容
		return am.selectByExampleWithBLOBs(example);
	}

	/**
	 * 根据分类id分页查询文章
	 * @param page
	 * @return
	 */
	public List<Article> queryByCategory(int id, int page) {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryidEqualTo(id);
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page, 5);
		//此处应使用selectByExampleWithBLOBs，而不能使用selectByExample，因为数据太大selectByExample取不到内容
		return am.selectByExampleWithBLOBs(example);
	}
}
