package com.example.backend.config;

import com.example.backend.common.ErrorType.LoginErrorCode;
import com.example.backend.common.RCode;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.PrintWriter;

/**
 * @author ocean
 * @date 2021/9/29
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .usernameParameter("userAccount")
                .passwordParameter("password")
                .successHandler((req, resp, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    RCode<Object> rCode = new RCode<>(principal);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(rCode));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    RCode<Object> rCode;
                    if (e instanceof LockedException) {
                        rCode = new RCode<>(LoginErrorCode.ACCOUNT_LOCKED,null);
                        out.write(new ObjectMapper().writeValueAsString(rCode));
                    } else if (e instanceof CredentialsExpiredException) {
                        rCode = new RCode<>(LoginErrorCode.CREDENTIALS_EXPIRED,null);
                        out.write(new ObjectMapper().writeValueAsString(rCode));
                    } else if (e instanceof AccountExpiredException) {
                        rCode = new RCode<>(LoginErrorCode.ACCOUNT_EXPIRED,null);
                        out.write(new ObjectMapper().writeValueAsString(rCode));
                    } else if (e instanceof DisabledException) {
                        rCode = new RCode<>(LoginErrorCode.ACCOUNT_DISABLED,null);
                        out.write(new ObjectMapper().writeValueAsString(rCode));
                    } else if (e instanceof BadCredentialsException) {
                        rCode = new RCode<>(LoginErrorCode.BAD_CREDENTIALS,null);
                        out.write(new ObjectMapper().writeValueAsString(rCode));
                    }
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .key("Ocean")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    RCode<String> rCode = new RCode<>("注销成功");
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(rCode));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                    RCode<Object> rCode = new RCode<>(LoginErrorCode.NO_LOGIN,null);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(rCode));
                    out.flush();
                    out.close();
                })
                .and()
                .cors()
                .and()
                .csrf().disable().sessionManagement().maximumSessions(1);
    }
}
