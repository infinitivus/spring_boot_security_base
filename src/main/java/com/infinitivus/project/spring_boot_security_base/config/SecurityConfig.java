package com.infinitivus.project.spring_boot_security_base.config;

import com.infinitivus.project.spring_boot_security_base.service.security_service.MyUserDetailsService;
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
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private MyUserDetailsService myUserDetailsService;

        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/userDatas/**").hasRole("ADMIN")
                    .antMatchers("/persons/**").hasAnyRole("ADMIN","MANAGER")
                    .antMatchers("/repairWorks/**").hasAnyRole("ADMIN","MASTER")
                    .antMatchers("/spareParts/**").hasAnyRole("ADMIN","MASTER")
                    .antMatchers("/**").hasRole("ADMIN")
                    .and().httpBasic().realmName("MY APP REALM")
                    .authenticationEntryPoint(authenticationEntryPoint);
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
        }
    }
