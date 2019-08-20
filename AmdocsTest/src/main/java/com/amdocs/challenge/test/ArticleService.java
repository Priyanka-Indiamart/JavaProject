package com.amdocs.challenge.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {

	public static Map<Integer, Article> articleMap = new HashMap<Integer, Article>();
	
	public 	List<Article> getAll(){
		return new ArrayList<Article>(articleMap.values()); 
	}

	public Article findById(int id) {
		return articleMap.get(id);
	}
	
	public void add(Article article) {
		articleMap.put(article.getId(), article);		
	}
	
	public void clear() {
		if(articleMap.size() >0){
			articleMap.clear();		
		}
		
	}
}
