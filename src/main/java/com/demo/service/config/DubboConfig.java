package com.demo.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:dubbo-consumer.xml"})
public class DubboConfig {
}
