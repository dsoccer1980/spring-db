package ru.dsoccer1980.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

  @GetMapping("my")
  public String index() {
    return "index";
  }
}
