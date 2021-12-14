package ru.dsoccer.feign.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer.feign.client.UserClient;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    @GetMapping("/user")
    public String getUsers() {
        return userClient.getUsers();

    }
}


