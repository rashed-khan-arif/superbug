package com.startup.superbug.data_access.service;

import com.startup.superbug.data_access.repository.ArticleRepository;
import com.startup.superbug.entity.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Async("asyncExecutor")
    public CompletableFuture<List<Article>> getAll() {
        return CompletableFuture.completedFuture(articleRepository.findAll());
    }

    @Async("asyncExecutor")
    public CompletableFuture<Optional<Article>> getById(int id) {
        return CompletableFuture.completedFuture(articleRepository.findById(id));
    }

    @Async("asyncExecutor")
    public CompletableFuture<Article> save(Article article) {
        return CompletableFuture.completedFuture(articleRepository.save(article));
    }
}
