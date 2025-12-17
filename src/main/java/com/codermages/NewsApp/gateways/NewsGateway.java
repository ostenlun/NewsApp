package com.codermages.NewsApp.gateways;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.codermages.NewsApp.domain.Article;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class NewsGateway {
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    final String NEWS_API_URL = "https://newsdata.io/api/1/latest";
    final String ACCESS_TOKEN = "pub_0078cb1ffa0f4b00b7f7bfe990fc3dd8";

    public NewsGateway() {}

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Article fetchNewsArticle(String category, String language) throws Exception {
        String url = NEWS_API_URL + "?category=" + category + "&language=" + language;
        
        // Call end point with token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-ACCESS-KEY", ACCESS_TOKEN);

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

    public List<Article> fetchNewsArticles(String category, String language, int count) throws Exception {
        String url = NEWS_API_URL + "?category=" + category + "&language=" + language + "&size=" + count;
        
        // Call end point with token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-ACCESS-KEY", ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Call the external API
        System.out.println("Calling News API: " + url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
        
        JsonNode rootNode = mapper.readTree(response.getBody());

        ObjectNode resultsNode = (ObjectNode) rootNode.path("results");

        if (resultsNode.isEmpty()) {
            ((ObjectNode) rootNode).put("Length", "0");
        }

        Article[] articles = mapper.treeToValue(resultsNode.get("results"), Article[].class);
        List<Article> articlesList = List.of(articles);

        return articlesList;
    }
}
