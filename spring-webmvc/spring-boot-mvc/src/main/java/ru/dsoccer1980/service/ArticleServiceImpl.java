package ru.dsoccer1980.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
  private List<Article> articles = new ArrayList<Article>();
  {
    articles.add(new Article(0, "first"));
    articles.add(new Article(1, "second"));
    articles.add(new Article(2, "third"));

  }


  public List<Article> getAll() {
    return articles;
  }

  public Article getOne(int id) {
    return articles.get(id);
  }

  public boolean delete(int id) {
    try {
      articles.remove(id);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return true;
  }

  public int create(Article article) {
    article.setId(articles.size()-1);
    articles.add(article);
    return article.getId();
  }

  public int update(Article article) {
    Article article1 = articles.get(article.getId());
    article1.setTitle(article.getTitle());
    return article.getId();
  }

}
