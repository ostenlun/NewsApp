package com.codermages.NewsApp.controllers;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.codermages.NewsApp.domain.Article;
import com.codermages.NewsApp.services.NewsService;
import com.codermages.NewsApp.services.StorageService;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {
    @MockitoBean
    private NewsService newsService;

    @MockitoBean
    private StorageService storageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetNews() throws Exception {
        Article article = new Article("Sample title", "Sample description");
        when(newsService.fetchNewsArticle()).thenReturn(article);

        mockMvc.perform(get("/news"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attributeExists("title"))
               .andExpect(model().attributeExists("description"))
               .andExpect(model().attribute("title", "First article title: " + article.getTitle()))
               .andExpect(model().attribute("description", "Description: " + article.getDescription()));
    }
}
