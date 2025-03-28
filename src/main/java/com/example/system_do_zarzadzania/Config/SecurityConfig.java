//package com.example.system_do_zarzadzania.Config;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery(
//                "SELECT username, password, 1 as enabled FROM users where username = ?"
//        );
//
//        return userDetailsManager;
//    }
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configurer ->
//                        configurer
//                                .requestMatchers("/register").permitAll()
//                                .requestMatchers("/confirm/*").permitAll()
//                                .anyRequest().hasAnyRole("USER", "ADMIN")
//
//                )
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.accessDeniedPage("/access-denied"))
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/showMyLoginPage")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .successHandler((request, response, authentication) -> {
//                                    String username = authentication.getName();
//                                    HttpSession session = request.getSession();
//                                    session.setAttribute("username", username);
//                                    response.sendRedirect("/home");
//                                })
//                                .permitAll()
//
//                )
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .invalidSessionUrl("/showMyLoginPage")
//                                .maximumSessions(1)
//                                .maxSessionsPreventsLogin(false)
//                                .expiredUrl("/showMyLoginPage")
//                );
//        return http.build();
//    }
//}
