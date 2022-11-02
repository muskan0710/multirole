package com.example.multirole.config;

import com.example.multirole.repository.UserDao;
import com.example.multirole.repository.impl.UserDaoImpl;
import com.example.multirole.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//
//
//    public SecurityConfig(PasswordEncoder passwordEncoder) {
//
//        this.passwordEncoder = passwordEncoder;
//
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("AGENT", "STORE", "ADMIN")
                .antMatchers("/createUser").hasAnyRole("AGENT_CREATE", "ADMIN_CREATE")
                .antMatchers("/deleteUser").hasAnyRole("AGENT_DELETE", "ADMIN_DELETE")
                .antMatchers("/modifyUser").hasAnyRole("AGENT_MODIFY", "ADMIN_MODIFY")
                .antMatchers("/createStore").hasAnyRole("STORE_CREATE", "ADMIN_CREATE")
                .antMatchers("/deleteStore").hasAnyRole("STORE_DELETE", "ADMIN_DELETE")
                .antMatchers("/modifyStore").hasAnyRole("STORE_MODIFY", "ADMIN_MODIFY")

                .anyRequest().authenticated()
                .and()
                .httpBasic();
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")

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
            .roles("AGENT_MODIFY")
            .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(timmy, john, sarah);

        return userDetailsManager;

    }

}

