package org.xi.securitytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xi.securitytest.handler.XiAccessDeniedHandler;
import org.xi.securitytest.handler.XiAuthenticationFailureHandler;
import org.xi.securitytest.handler.XiAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    XiAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login.html")
//                .successForwardUrl("/index") 和 successHandler 无法共存
                .successHandler(new XiAuthenticationSuccessHandler("/index.html"))
                .failureHandler(new XiAuthenticationFailureHandler("/error.html"));

        httpSecurity.authorizeRequests()
                .antMatchers("/login.html", "/error.html").permitAll()
//                .regexMatchers(".*").permitAll()
                .antMatchers("/haha.html").hasAuthority("admin")
                .antMatchers("/haha.html").hasRole("admin")
                .antMatchers("/haha.html").hasIpAddress("127.0.0.1")
//                .anyRequest().authenticated();
                .anyRequest().access("@xiAccessService.hasPermission(request, authentication)");
        httpSecurity.csrf().disable();
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
