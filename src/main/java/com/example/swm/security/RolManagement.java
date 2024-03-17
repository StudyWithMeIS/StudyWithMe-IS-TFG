package com.example.swm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class RolManagement {
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT nif_admin AS username, password_admin AS password, true AS enabled FROM administradores WHERE nif_admin=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT nif_admin AS username, 'ROLE_ADMIN' AS authority FROM administradores WHERE nif_admin=? UNION " +
                "SELECT nif_profesor AS username, 'ROLE_PROFESOR' AS authority FROM profesores WHERE nif_profesor=? UNION " +
                "SELECT nif_alumno AS username, 'ROLE_ALUMNO' AS authority FROM alumnos WHERE nif_alumno=?");

        List<Usuario> usuearios = obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            String username = usuario.getNif();
            String password = usuario.getPassword();
            List<String> roles = obtenerRolesDeUsuario(usuario);

            // Crear el usuario con sus roles correspondientes
            userDetailsManager.createUser(new User(username, password, AuthorityUtils.createAuthorityList(roles.toArray(new String[0]))));
        }

        return userDetailsManager;
    }

    private List<Usuario> obtenerUsuarios() {
        // Aquí se debería hacer la consulta a la base de datos para obtener los usuarios
        return null;
    }

    private List<String> obtenerRolesDeUsuario(Usuario usuario) {
        // Aquí se debería hacer la consulta a la base de datos para obtener los roles de un usuario
        return null;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
               .requestMatchers("/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**").permitAll()
               .requestMatchers("/", "/login", "/signup").permitAll()
               .requestMatchers("/profesor/**").hasAuthority("ROLE_PROFESOR")
               .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
               .requestMatchers("/alumno/**").hasAuthority("ROLE_ALUMNO")
               .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
            .exceptionHandling((exception) -> exception.accessDeniedPage("/denegado"));

        return http.build();
    }
}
