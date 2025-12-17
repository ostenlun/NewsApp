package com.codermages.NewsApp.gateways;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.codermages.NewsApp.domain.Article;

public class NewsGatewayTest {
    RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
    private final NewsGateway newsGateway = new NewsGateway();

    @Test
    public void testFetchNewsArticle() throws Exception {
        String strArticlesJson = "{ \"results\": [ { \"title\": \"Sample Title\", \"description\": \"Sample Description\" } ] }";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(strArticlesJson, HttpStatus.OK);

        when(restTemplateMock.exchange(anyString(), eq(HttpMethod.GET), Mockito.any(), eq(String.class))
            ).thenReturn(responseEntity); 

        newsGateway.setRestTemplate(restTemplateMock);
        Article article = newsGateway.fetchNewsArticle("category", "language");

        Assertions.assertEquals("Sample Title", article.getTitle());
        Assertions.assertEquals("Sample Description", article.getDescription());
    }

}
