package telran.java2022.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class AuthorizationConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.csrf().disable();
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers("/account/register/**").permitAll()
        .antMatchers("/forum/post/*").access("hasRole('USER')")
        .antMatchers("/forum/post/*/like/**").access("hasRole('USER')")
        .anyRequest().authenticated();
    return http.build();
  }

}
