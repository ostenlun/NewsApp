package com.codermages.NewsApp.gateways;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.codermages.NewsApp.models.Article;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class NewsGateway {
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Article fetchNewsArticle(String apiUrl, String category, String language, String accessToken) {
        String url = apiUrl + "?category=" + category + "&language=" + language;
        
        // Call end point with token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-ACCESS-KEY", accessToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Call the external API
        System.out.println("Calling News API: " + url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
        
        JsonNode resultsNode = mapper.readTree(response.getBody());
        JsonNode node = resultsNode.get("results").get(0);
        Article article = mapper.treeToValue(node, Article.class);
        
        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        System.out.println("Article fetched: " + indented);

        return article;
    }
}
