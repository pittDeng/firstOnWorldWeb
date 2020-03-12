package com.dsh.dao;

import com.dsh.entity.Article;

public interface ArticleMapper {
    Article getArticle(int id);
    void addArticle(Article article);
}
