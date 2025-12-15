package com.codermages.NewsApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.codermages.NewsApp.domain.Article;
import com.codermages.NewsApp.gateways.NewsGateway;

public class NewsServiceTest {
    private NewsService newsService;
    private NewsGateway newsGateway;

    @BeforeEach
    public void setUp() {
        newsGateway = org.mockito.Mockito.mock(NewsGateway.class);
        newsService = new NewsService(newsGateway);
    }

    @Test
    public void testFetchNewsArticle() {
        // Test logic to verify fetching news article
        when(newsGateway.fetchNewsArticle(anyString(), anyString(), anyString()))
            .thenReturn(new Article("Sample Title", "Sample Description"));

        Article article = newsService.fetchNewsArticle();
        Assertions.assertEquals("Sample Title", article.getTitle());
        Assertions.assertEquals("Sample Description", article.getDescription());
    }

}
