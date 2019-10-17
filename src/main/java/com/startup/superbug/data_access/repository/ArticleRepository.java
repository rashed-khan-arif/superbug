package com.startup.superbug.data_access.repository;

import com.startup.superbug.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
