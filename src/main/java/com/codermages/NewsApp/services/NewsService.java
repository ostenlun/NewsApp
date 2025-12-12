package com.codermages.NewsApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codermages.NewsApp.gateways.NewsGateway;
import com.codermages.NewsApp.models.Article;

@Service
public class NewsService {
    @Autowired
    NewsGateway newsGateway;

    public Article fetchNewsArticle() {
        // Logic to fetch news from an external API
         String category = "Science, Technology";
        String language = "en";
        String accessToken = "pub_0078cb1ffa0f4b00b7f7bfe990fc3dd8";
        
        Article article = newsGateway.fetchNewsArticle(category, language, accessToken);
        return article;
    }
}
