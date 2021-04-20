package ru.dsoccer1980.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.dsoccer1980.service.ThrowingExceptionsService;

@Controller
public class ExceptionHandlerController {

  @Autowired
  private ThrowingExceptionsService simpleService;

  @GetMapping("/ex/1")
  public String exception1() {
    simpleService.throwUnsupportedOperationException();
    return "Hi";
  }

  @GetMapping("/ex/2")
  public String exception2() {
    simpleService.throwIllegalArgumentException();
    return "Hi";
  }

  @GetMapping("/ex/3")
  public String exception3() {
    simpleService.throwIllegalStateException();
    return "Hi";
  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
  @ExceptionHandler({UnsupportedOperationException.class})
  public void handleExceptionAndResponseWithStatus() {
    System.out.println("handle");
  }

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseBody
  public String handleExceptionAndReturnErrorText() {
    return "error happened";
  }

  @ExceptionHandler({IllegalStateException.class})
  public String handleExceptionAndReturnPage() {
    return "error";
  }


}
