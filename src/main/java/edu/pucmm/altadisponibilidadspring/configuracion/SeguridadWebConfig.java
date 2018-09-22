package edu.pucmm.altadisponibilidadspring.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SeguridadWebConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Clase para encriptar contraseña
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        //
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptPasswordEncoder)
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("admin"))
                .roles("ADMIN");


    }

    /**
     * Indicando que la forma de autentificar será mediante el header de:
     * Authorization:Basic <Clave>
     * Se consigue concatenando el usuario y password a base64.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().permitAll()
                .and()
                .httpBasic(); //indicando que será autentificacion basica, enviado por el header.
    }
}
