package com.alkemy.disney.auth.config;

import com.alkemy.disney.auth.entity.UserDat;
import com.alkemy.disney.auth.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepo;

    public AuthConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().and()
                .headers().frameOptions().disable();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/home","/login","/register").permitAll()
                //.antMatchers("/","/home","/login","/register").hasRole("ROLELOG")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    //@Bean
    //public UserDetailsService user (DataSource dataSource){
    //    return new JdbcUserDetailsManager(dataSource);
    //}

    //@Bean
    //public AuthTokenFilter authenticationJwtTokenFilter() {
    //    return new AuthTokenFilter();
    //}



}
