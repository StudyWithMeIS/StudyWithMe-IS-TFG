package com.example.swm.security;


//DEMOMENTO PEGA OSTIA PERO ESO SE ARREGLA CUANDO SE CONECTE A LA BASE DE DATOS
@Configuration
@EnableWebSecurity
public class RolManagement extends WebSecurityConfigurerAdapter  {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email_admin AS username, password_admin AS password, true FROM administradores WHERE email_admin = ?")
                .authoritiesByUsernameQuery("SELECT email_admin AS username, 'ROLE_ADMIN' AS authority FROM administradores WHERE email_admin = ?")
                .passwordEncoder(passwordEncoder());

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email_profesor AS username, password_profesor AS password, true FROM profesores WHERE email_profesor = ?")
                .authoritiesByUsernameQuery("SELECT email_profesor AS username, 'ROLE_PROFESOR' AS authority FROM profesores WHERE email_profesor = ?")
                .passwordEncoder(passwordEncoder());

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email_alumno AS username, password_alumno AS password, true FROM alumnos WHERE email_alumno = ?")
                .authoritiesByUsernameQuery("SELECT email_alumno AS username, 'ROLE_ALUMNO' AS authority FROM alumnos WHERE email_alumno = ?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/profesor/**").hasRole("PROFESOR")
                .antMatchers("/alumno/**").hasRole("ALUMNO")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
