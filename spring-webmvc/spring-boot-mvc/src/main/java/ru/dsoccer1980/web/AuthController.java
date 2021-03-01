package ru.dsoccer1980.web;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer1980.model.Result;
import ru.dsoccer1980.model.User;
import ru.dsoccer1980.service.JwtTokenProvider;

@RestController
@RequestMapping("/js-normal-api")
public class AuthController {

  private final JwtTokenProvider jwtTokenProvider;

  public AuthController(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @GetMapping("/auth/jwt")
  public String getJwt() {
    return jwtTokenProvider.createJwt();
  }

  @PostMapping("/auth/login.php")
  public ResponseEntity<Result> login(@ModelAttribute User user, HttpServletResponse response) {
    Cookie cookie = new Cookie("refreshToken", "123456789");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(30);
    cookie.setDomain("aktors.ee");
    cookie.setPath("/js-normal-api/auth/");
    response.addCookie(cookie);
    System.out.println(user);
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new Result(true, jwtTokenProvider.createJwt(), null));
  }

  @GetMapping("/auth/check.php")
  public ResponseEntity<?> check(@RequestHeader("Authorization") String authorization,
      HttpServletResponse response) throws Exception {
    if (!authorization.startsWith("Bearer ")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    String token = authorization.substring(7);
    if (jwtTokenProvider.validateToken(token)) {
      return ResponseEntity.ok(new Result(true, null, null));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }


  @PutMapping("/auth/refresh.php")
  public ResponseEntity<Result> refresh(HttpServletRequest request, @CookieValue(value = "refreshToken", defaultValue = "") String refreshToken) {
    System.out.println(Arrays.toString(request.getCookies()));
    if ("".equals(refreshToken)) {
      return ResponseEntity.ok(new Result(false, null, "{invalid refresh token}"));
    }
    return ResponseEntity.ok(new Result(true, jwtTokenProvider.createJwt(), null));
  }

  @GetMapping("/test")
  public String test(@CookieValue(value = "refreshToken", defaultValue = "no") String refreshToken, HttpServletResponse response, HttpServletRequest request) {
    if (refreshToken.equals("no")) {
      Cookie cookie = new Cookie("refreshToken", "123456789");
      cookie.setHttpOnly(true);
      cookie.setMaxAge(10);
      cookie.setDomain("aktors.ee");
      cookie.setPath("/js-normal-api/auth/");
      response.addCookie(cookie);
    } else {
      Cookie cookies = request.getCookies()[0];
      System.out.println(cookies.getName());
      System.out.println(cookies.getValue());
      System.out.println(cookies.getPath());
      System.out.println(cookies.getMaxAge());
      System.out.println(cookies.getDomain());
    }
    return refreshToken;
  }


}
