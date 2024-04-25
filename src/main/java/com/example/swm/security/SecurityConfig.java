package com.example.swm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private DataSource dataSource;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT nif_admin AS username, password_admin AS password, true AS enabled FROM administradores WHERE nif_admin = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT nif_admin AS username, 'ROLE_ADMINISTRADOR' AS authority FROM administradores WHERE nif_admin=?" );
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT nif_profesor AS username, password_profesor AS password, true AS enabled FROM profesores WHERE nif_profesor = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT nif_profesor AS username, 'ROLE_PROFESOR' AS authority FROM profesores WHERE nif_profesor=?" );
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT nif_alumno AS username, password_alumno AS password, true AS enabled FROM alumnos WHERE nif_alumno = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT nif_alumno AS username, 'ROLE_ALUMNO' AS authority FROM alumnos WHERE nif_alumno=?"
        );
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeRequests(auth -> auth
               .requestMatchers("templates/**", "static/**", "/styles/**", "/scripts/**", "/images/**", "/pages/**", "/vendor/**", "/join", "knowUs").permitAll()
               .requestMatchers("/", "/login", "/signup").permitAll()
               .requestMatchers("/profesor/**").hasAuthority("ROLE_PROFESOR")
               .requestMatchers("/administrador/**").hasAuthority("ROLE_ADMINISTRADOR")
               .requestMatchers("/alumno/**").hasAuthority("ROLE_ALUMNO")
               .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll())
            .exceptionHandling((exception) -> exception.accessDeniedPage("/denegado"));

        return http.build();
    }
}
