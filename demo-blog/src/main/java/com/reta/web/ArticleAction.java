package com.reta.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.github.pagehelper.PageHelper;
import com.reta.bean.Article;
import com.reta.bean.Category;
import com.reta.bean.User;
import com.reta.biz.ArticleBiz;
import com.reta.biz.CategoryBiz;


@Controller
public class ArticleAction {
	@Resource
	private ArticleBiz abiz;
	@Resource
	private CategoryBiz cbiz;

	// header里的分类。执行所有代码前执行，并且将返回值放入Model
	@ModelAttribute("cList")
	public List<Category> init() {
		return cbiz.queryAll();
	}

	// 查询首页
	@GetMapping("index") 
	public String index(@RequestParam(defaultValue = "1") int page, Model model) {
		// 最新文章
		model.addAttribute("aList", abiz.queryNewArticle(page));
		return "index";
	}

	// 分类文章
	@GetMapping("category")
	public String category(@RequestParam(defaultValue = "1") int page, int id, Model model) {
		System.out.println("category======id:" + id);
		model.addAttribute("aList", abiz.queryByCategory(id, page));
		return "category";
	}

	// 显示文章
	@GetMapping("article")
	public String article(@RequestParam(defaultValue="1") int page, int id, Model model) {
		Article a = abiz.read(id);
		//
		PageHelper.startPage(page, 5);
		a.getComments();
		//查相关文章
		List<Article> relaList=abiz.queryRela(a.getCategoryid());
		model.addAttribute("relaList", relaList);
		// 不设定属性名称，则默认为小写开头的类名
		model.addAttribute(a);
		return "article";
	}
	

	@GetMapping("toedit")
	public String rrtoedit(Model model) {
		model.addAttribute("article", new Article());
		return "articleEdit";
	}

	@PostMapping("saveArticle")
	public String save(Article article, @SessionAttribute("loginedUser") User user, Model model) {
		article.setAuthor(user.getName());
		article.setCreatetime(new Date());
		abiz.save(article);
		return article(1, article.getId(), model);
	}
}