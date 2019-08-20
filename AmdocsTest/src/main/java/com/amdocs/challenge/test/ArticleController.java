package com.amdocs.challenge.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping(value = "/articles",produces = "application/json; charset=utf-8")
	public 	ResponseEntity<List<Article>> getArticle() {
		List<Article> returnMap = articleService.getAll();
		if(returnMap.size() > 0){
			return new ResponseEntity<List<Article>>(articleService.getAll(), HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Article>>(new ArrayList<Article>(), HttpStatus.NOT_FOUND);
			
		}
	}

	@GetMapping(value = "/articles/{id}",produces = "application/json; charset=utf-8")
	public ResponseEntity<Article>  getArticleById(@PathVariable("id") int id) {
		Article article = articleService.findById(id);
		if(article != null){
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		}else{
			return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
		}
	}
}
