package com.startup.superbug.controller;

import com.startup.superbug.data_access.service.ArticleService;
import com.startup.superbug.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("articles")
    public ResponseEntity getAll() {
        try {
            List<Article> articles = articleService.getAll().get();
            if (articles.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(articles);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("articles/{id}")
    public ResponseEntity get(@PathVariable int id) {
        try {
            Optional<Article> article = articleService.getById(id).get();
            if (!article.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(article.get());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("articles")
    public ResponseEntity post(@RequestBody Article article) {
        try {
            Article newArticle = articleService.save(article).get();
            if (newArticle == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(newArticle);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
