package com.codermages.NewsApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.codermages.NewsApp.gateways.NewsGateway;
import com.codermages.NewsApp.models.Article;

@SpringBootTest
public class NewsServiceTest {
    @Autowired
    NewsService newsService;

    @MockitoBean
    private NewsGateway newsGateway;

    @Test
    public void testFetchNewsArticle() {


        // Test logic to verify fetching news article
        when(newsGateway.fetchNewsArticle(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new Article("Sample Title", "Sample Description"));

        Article article = newsService.fetchNewsArticle();
        Assertions.assertEquals("Sample Title", article.getTitle());
        Assertions.assertEquals("Sample Description", article.getDescription());
    }

}
