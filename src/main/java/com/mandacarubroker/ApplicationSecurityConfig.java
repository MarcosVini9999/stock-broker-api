package com.mandacarubroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }



        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(
                            (authz) -> authz.requestMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
                                    .requestMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
                                    .requestMatchers("/users/addNew").permitAll()
                                    .requestMatchers("/security/user/Edit/**","/security/users/delete/**","/img/**" ).hasAuthority("ADMIN")
                                    .anyRequest().authenticated())
                    .formLogin(login -> login.loginPage("/login").permitAll().defaultSuccessUrl("/index"))


                    .logout(logout -> logout.invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .logoutSuccessUrl("/login").permitAll())
                    .exceptionHandling(exceptionHandler -> exceptionHandler.accessDeniedPage("/accessDenied"));

            return http.build();
        }

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

            provider.setUserDetailsService(userDetailsService);

            provider.setPasswordEncoder(bCryptPasswordEncoder());
            return provider;
        }




}
