package com.codermages.NewsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codermages.NewsApp.domain.Article;
import com.codermages.NewsApp.domain.MockStorageRequest;
import com.codermages.NewsApp.services.NewsService;
import com.codermages.NewsApp.services.StorageService;

import jakarta.annotation.Nonnull;



@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private StorageService storageService;

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

    // Store news info
    @PostMapping("/store")
    public String storeNewsInfo(@Nonnull @RequestBody MockStorageRequest request, Model model) {
        System.out.println("Calling store news info");
        ResponseEntity<?> response = storageService.storeData(request.getData());
        model.addAttribute("status", response.getStatusCode());
        return "index";
    }

    // Get news info
    @GetMapping("/get")
    public String getNewsInfo(@Nonnull @PathVariable String userId, Model model) {
        System.out.println("Calling store news info");
        ResponseEntity<?> response = storageService.getData(userId);
        model.addAttribute("status", response.getStatusCode());
        model.addAttribute("data", response.getBody());
        return "index";
    }

}
