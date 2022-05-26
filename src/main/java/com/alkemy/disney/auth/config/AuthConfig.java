package com.alkemy.disney.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@ComponentScan("com.alkemy.disney")
public class AuthConfig implements WebMvcConfigurer {


}
