package com.demo.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign service
 *
 * @author shenhongjun
 * @since 2020/4/21
 */
@FeignClient(value = "bmp-product-service")
public interface FeignService {
    @PostMapping(value = "/bmp/v1/product/test")
    ResponseEntity insertTest(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
