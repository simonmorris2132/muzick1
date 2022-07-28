package com.careerdevs.muzick1.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.careerdevs.muzick1.payloads.NewsAPI;
import com.careerdevs.muzick1.payloads.Article;

@CrossOrigin
@RestController
@RequestMapping("/api/news")
public class NewsController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${muzick.api.key}")
    public String apiKey;
    
    @GetMapping("/test")
    public ResponseEntity<String> testRoute() {
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getMusicArticle() {
        String url = "https://newsapi.org/v2/everything?apiKey=" + apiKey + "&q=music";

        NewsAPI response = restTemplate.getForObject(url, NewsAPI.class);

        List<Article> articleList = new ArrayList<>();

        for (Article article : response.getArticles()) {
            if (article.getSourceName().equals("Wired")) {
                article.setLikes("Hi");
                /* articleList.add(article); */
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

