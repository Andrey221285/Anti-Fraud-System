package antifraud.security;

import antifraud.web.controller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService) // user store 1
            .passwordEncoder(getEncoder());

//        auth
//                .inMemoryAuthentication() // user store 2
//                .withUser("Admin").password("hardcoded").roles("USER")
//                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint) // Handles auth error
                .and()
                .csrf().disable().headers().frameOptions().disable() // for Postman, the H2 console
                .and()
                .authorizeRequests() // manage access
                .mvcMatchers(HttpMethod.POST, "/api/auth/user").permitAll()
                .mvcMatchers(HttpMethod.DELETE, "/api/auth/user/**").hasRole(UserService.ROLES.ADMINISTRATOR.name())
                .mvcMatchers(HttpMethod.PUT, "/api/auth/role").hasRole(UserService.ROLES.ADMINISTRATOR.name())
                .mvcMatchers(HttpMethod.PUT, "/api/auth/access").hasRole(UserService.ROLES.ADMINISTRATOR.name())
                .mvcMatchers(HttpMethod.GET, "/api/auth/list").hasAnyRole(UserService.ROLES.ADMINISTRATOR.name(),UserService.ROLES.SUPPORT.name() )
                .mvcMatchers(HttpMethod.POST, "/api/antifraud/transaction").hasRole(UserService.ROLES.MERCHANT.name() )
                .antMatchers("/actuator/shutdown").permitAll() // needs to run test

                // other matchers
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                ;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(13);
    }

}
