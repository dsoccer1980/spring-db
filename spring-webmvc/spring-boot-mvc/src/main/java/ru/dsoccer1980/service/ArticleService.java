package ru.dsoccer1980.service;

import java.util.List;
import ru.dsoccer1980.model.Article;

public interface ArticleService {

  List<Article> getAll();

  Article getOne(int id);

  boolean delete(int id);

  int create(Article article);

  int update(Article article);
}
