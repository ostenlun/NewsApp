package com.codermages.NewsApp.services;

import org.springframework.stereotype.Service;

import com.codermages.NewsApp.domain.Article;
import com.codermages.NewsApp.gateways.NewsGateway;
import java.util.List;

@Service
public class NewsService {
    private final NewsGateway newsGateway;

    public NewsService(NewsGateway newsGateway) {
        this.newsGateway = newsGateway;
    }

    public Article fetchNewsArticle() {
        // Logic to fetch news from an external API
        
        String category = "Science, Technology";
        String language = "en";
        
        Article article = newsGateway.fetchNewsArticle(category, language);
        return article;
    }

    public List<Article> fetchNewsArticles() {
        String category = "Science, Technology";
        String language = "en";
        int count = 5;
        List<Article> articles = newsGateway.fetchNewsArticles(category, language, count);

        articles.stream().forEach(article ->
            System.out.println("Fetched article: " + article.getTitle() + " published on " + article.getPubDate()));

        articles.stream()
            .sorted((article1, article2) -> article2.getPubDate().compareTo(article1.getPubDate()))
            .forEach(article ->
            {
                System.out.println("Sorted article: " + article.getTitle() + " published on " + article.getPubDate());
            });

        return articles;
    }
}
