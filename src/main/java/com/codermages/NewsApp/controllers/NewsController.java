package com.codermages.NewsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codermages.NewsApp.models.Article;
import com.codermages.NewsApp.services.NewsService;


@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public String root() {
        return "index";
    }
    
    @GetMapping("/news")
    public String getNews(Model model) {
        System.out.println("Calling fetch news");
        Article article = newsService.fetchNewsArticle();
        model.addAttribute("title", "First article title: " + article.getTitle());
        model.addAttribute("description", "Description: " + article.getDescription());
        return "index";
    }
}
