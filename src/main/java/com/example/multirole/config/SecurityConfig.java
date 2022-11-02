package com.example.multirole.config;

import com.example.multirole.repository.UserDao;
import com.example.multirole.repository.impl.UserDaoImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/test/createUser").hasAnyRole("AGENT_CREATE", "ADMIN_CREATE")
                .antMatchers("/test/deleteUser").hasAnyRole("AGENT_DELETE", "ADMIN_DELETE")
                .antMatchers("/test/modifyUser").hasAnyRole("AGENT_MODIFY", "ADMIN_MODIFY")
                .antMatchers("/test/createStore").hasAnyRole("STORE_CREATE", "ADMIN_CREATE")
                .antMatchers("/test/deleteStore").hasAnyRole("STORE_DELETE", "ADMIN_DELETE")
                .antMatchers("/test/modifyStore").hasAnyRole("STORE_MODIFY", "ADMIN_MODIFY")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails timmy = User.builder()
                .username("timmy")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN_CREATE")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password(passwordEncoder().encode("password"))
                .roles("AGENT_CREATE")
                .build();

        UserDetails sarah = User.builder()
                .username("sarah")
                .password(passwordEncoder().encode("password"))
                .roles("STORE_CREATE")
                .build();

        UserDetails adam = User.builder()
                .username("adam")
                .password(passwordEncoder().encode("password"))
                .authorities("ROLE_AGENT_MODIFY")
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(timmy, john, sarah, adam);
        return userDetailsManager;
    }

}

