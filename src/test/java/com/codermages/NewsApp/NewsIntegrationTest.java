package com.codermages.NewsApp;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.web.client.RestTemplate;

import com.codermages.NewsApp.gateways.NewsGateway;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsIntegrationTest {
    RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);

    // Test with InjectMocks?

    @Autowired
    private NewsGateway newsGateway;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNews() throws Exception {
        newsGateway.setRestTemplate(restTemplateMock);

        String strArticlesJson = "{ \"results\": [ { \"title\": \"Sample Title\", \"description\": \"Sample Description\" } ] }";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(strArticlesJson, HttpStatus.OK);

        when(restTemplateMock.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class) , eq(String.class)))
            .thenReturn(responseEntity); 

        mockMvc.perform(get("/news"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(model().attributeExists("title"))
            .andExpect(model().attributeExists("description"))
            .andExpect(model().attribute("title", "First article title: " + "Sample Title"))
            .andExpect(model().attribute("description", "Description: " + "Sample Description"));
    }
}
