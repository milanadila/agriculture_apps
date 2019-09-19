package com.restapi.agriculture.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN
            = "select user_name, password, active as enabled "
            + "from Users where user_name = ?";

    private static final String SQL_PERMISSION
            = "select u.user_name, r.role_name as authority "
            + "from Users u join User_Role ur on u.Id = ur.id_user "
            + "join Roles r on ur.id_roles = r.Id "
            + "where u.user_name = ?";

    @Autowired private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }


    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/css/**","/images/**","/vendor/**","/fonts/**","/js/**","/h2/**","/swagger/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/index").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers("/webProduct/addProduct").hasRole("ADMIN")
                .antMatchers("/webProduct/viewProduct/updateProduct/{id}").hasRole("ADMIN")
                .antMatchers("/webProduct/delete/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home/index", true)
                .and()
                .logout();
    }
}