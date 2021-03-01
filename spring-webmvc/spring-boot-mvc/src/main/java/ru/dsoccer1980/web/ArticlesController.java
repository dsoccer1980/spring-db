package ru.dsoccer1980.web;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer1980.model.Article;
import ru.dsoccer1980.service.ArticleService;

@RestController
@RequestMapping("/js-hw-api/articles/")
public class ArticlesController {

  private final ArticleService articleService;

  public ArticlesController(ArticleService articleService) {
    this.articleService = articleService;
  }


  @GetMapping("/")
  public List<Article> products(@RequestHeader Map<String, String> headers, @RequestHeader("authorization") String auth) {
    System.out.println(auth);
    System.out.println(headers);
    return articleService.getAll();
  }

  @GetMapping("/{id}")
  public Article product(@RequestHeader Map<String, String> headers, @PathVariable int id) {
    return articleService.getOne(id);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@RequestHeader Map<String, String> headers, @PathVariable int id) {
    return articleService.delete(id);
  }

  @PostMapping
  public String create(@RequestHeader Map<String, String> headers, @ModelAttribute Article article) {
    int id = articleService.create(article);
    if (id != -1) {
      return "{\"res\": \"true\", \"id\": \"" + id + "\"}";
    } else {
      return "{\"res\": \"false\", \"errors\": \" problem create \"}";
    }
  }

  @PutMapping
  public String update(@RequestHeader Map<String, String> headers, @RequestBody Article article) {
    int id = articleService.update(article);
    if (id != -1) {
      return "{\"res\": \"true\", \"id\": \"" + id + "\"}";
    } else {
      return "{\"res\": \"false\", \"errors\": \" problem create \"}";
    }
  }
}

/*

  GET articles.php -> все статьи в виде массива

    GET articles.php?id=int -> одна статья в виде объекта || 404

    DELETE articles.php?id=int  -> true || false, если статья не найдена

    POST articles.php -> {res: true, id: int} || {res: false, errors: [strings...]} ошибка валидации
    body-formData(title, content)

    PUT articles.php -> {res: true} || {res: false, errors: [strings...]} ошибка валидации
    body-json(id, title, content)*/