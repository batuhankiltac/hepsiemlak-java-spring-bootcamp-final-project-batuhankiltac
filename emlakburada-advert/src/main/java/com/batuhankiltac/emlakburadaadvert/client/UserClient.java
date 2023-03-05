package com.batuhankiltac.emlakburadaadvert.client;

import com.batuhankiltac.emlakburadaadvert.model.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-feign", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping(value = "/users/{id}")
    UserResponse getById(@PathVariable Long id);
}