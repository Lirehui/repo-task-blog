package com.reta.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.reta.bean.Article;
import com.reta.bean.Category;
import com.reta.biz.ArticleBiz;
import com.reta.biz.CategoryBiz;

@Controller
public class ArticleAction {

	@Resource
	private ArticleBiz abiz;
	
	@Resource
	private CategoryBiz cbiz;
	
	@ModelAttribute("cList")
	public List<Category> init(){
		return cbiz.queryAll();
	}
	
	//查询首页
	@GetMapping("index")
	public String index(@RequestParam(defaultValue="1") int page, Model model) {
		model.addAttribute("aList", abiz.queryNewArticle(page));
		return "index";
	}
	
	//分类文章
	@GetMapping("category")
	public String category(int id, @RequestParam(defaultValue="1") int page, Model model) {
		model.addAttribute("aList", abiz.queryByCategory(id, page));
		return "category";
	}

	//显示文章
	@GetMapping("article")
	public String article(int id, Model model) {
		Article a= abiz.read(id);
		//查相关文章
		List<Article> relaList = abiz.queryRela(a.getCategoryid());
		model.addAttribute("relaList", relaList);
		//不设定属性名称，则使用小写开头的类名
		model.addAttribute(a);
		return "article";
	}
	
}
