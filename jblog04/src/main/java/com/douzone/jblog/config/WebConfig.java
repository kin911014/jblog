package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.douzone.jblog.config.web.MvcConfig;

@Configuration
@EnableWebMvc
@ComponentScan({"com.douzone.jblog.controller, com.douzone.jblog.exception"})
@Import({MvcConfig.class})
public class WebConfig {

}
