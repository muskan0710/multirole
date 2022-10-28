package com.example.multirole.config;

import com.example.multirole.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("AGENT","STORE","ADMIN")
                .antMatchers("/createUser").hasAnyAuthority("AGENT_CREATE", "ADMIN_CREATE")
                .antMatchers("/deleteUser").hasAnyAuthority("AGENT_DELETE", "ADMIN_DELETE")
                .antMatchers("/modifyUser").hasAnyAuthority("AGENT_MODIFY", "ADMIN_MODIFY")
                .antMatchers("/createStore").hasAnyAuthority("STORE_CREATE", "ADMIN_CREATE")
                .antMatchers("/deleteStore").hasAnyAuthority("STORE_DELETE", "ADMIN_DELETE")
                .antMatchers("/modifyStore").hasAnyAuthority("STORE_MODIFY", "ADMIN_MODIFY")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}
