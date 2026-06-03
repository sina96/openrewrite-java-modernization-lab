package com.example.openrewrite.spring5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.openrewrite.spring5")
public class AppConfig {
}
