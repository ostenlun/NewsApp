package com.codermages.NewsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codermages.NewsApp.models.Article;
import com.codermages.NewsApp.services.NewsService;


@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

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

    // @GetMapping("/index")
    // public String index() {
    //     System.out.println("Calling index");
    //     return "index.html";
    // }

    @GetMapping("/error")
    public String errorPage() {
        return "<html lang=\"en\">\n" +
"\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" \n" +
"          content=\"width=device-width, \n" +
"                   initial-scale=1.0\">\n" +
"    <title>\n" +
"        Error occurred\n" +
"    </title>\n" +
"    <link rel=\"stylesheet\" \n" +
"          href=\"style.css\">\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"    <div class=\"error-container\">\n" +
"        <h1> Error </h1>\n" +
"        <p>\n" +
"            Oops! The page you're\n" +
"            looking for is not here.\n" +
"        </p>\n" +
"    </div>\n" +
"</body>\n" +
"\n" +
"</html>";
    }
    

}
