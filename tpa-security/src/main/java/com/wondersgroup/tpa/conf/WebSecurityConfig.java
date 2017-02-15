package com.wondersgroup.tpa.conf;

import com.wondersgroup.tpa.service.IEmployeeService;
import com.wondersgroup.tpa.service.resource.FilterInvocationSecurityMetadataSourceService;
import com.wondersgroup.tpa.service.resource.SecurityAccessDecisionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TPAAuthenticationProvider authenticationProvider;
    @Autowired
    private FilterInvocationSecurityMetadataSourceService securityMetadataSourceService;
    @Autowired
    private SecurityAccessDecisionManager accessDecisionManager;
//    @Autowired
//    private IEmployeeService employeeService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customFilterSecurityInterceptor(), SwitchUserFilter.class);
        http.formLogin();
        http
                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);

        // 自定义UserDetailsService
//        auth.userDetailsService(employeeService).passwordEncoder(
//                new Md5PasswordEncoder());
    }

    @Bean
    public FilterSecurityInterceptor customFilterSecurityInterceptor(){
        FilterSecurityInterceptor fsi = new FilterSecurityInterceptor();
        fsi.setSecurityMetadataSource(securityMetadataSourceService);
        fsi.setAccessDecisionManager(accessDecisionManager);
        return fsi;
    }
}