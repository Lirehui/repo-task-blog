package com.reta.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reta.bean.Article;
import com.reta.bean.Comment;
import com.reta.dao.ArticleMapper;
import com.reta.dao.CommentMapper;

@Service
public class CommentBiz {

	@Resource
	private CommentMapper cm;
	@Resource
	private ArticleMapper am;
	
	// 评论
	@Transactional	//事务
	public Comment comment(Comment comm) {
		//评论表中插入一条记录
		cm.insertSelective(comm);
		//将对应文章的评论数加一
		Article article=am.selectByPrimaryKey(comm.getArticleid());
		article.setCommcnt(article.getCommcnt()==null? 1:(article.getCommcnt()+1));
		am.updateByPrimaryKey(article);
		
		return comm;//数据库中id自增长，在执行insert后将产生的id映射了回来。
	}
}
