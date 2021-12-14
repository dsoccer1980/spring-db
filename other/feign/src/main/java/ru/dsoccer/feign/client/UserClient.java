package ru.dsoccer.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-client", url="https://jsonplaceholder.typicode.com")
public interface UserClient {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    String getUsers();
}
