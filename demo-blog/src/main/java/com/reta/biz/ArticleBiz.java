package com.reta.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.reta.bean.Article;
import com.reta.bean.ArticleExample;
import com.reta.dao.ArticleMapper;

@Service
public class ArticleBiz {

	@Autowired
	private ArticleMapper am;
	
	/**
	 * 查询最新发布的文章
	 * page页数
	 */
	public List<Article> queryNewArticle(int page){
		ArticleExample example = new ArticleExample();
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(page, 5);
		return am.selectByExample(example);
	}
}
