package com.wondersgroup.tpa.conf;

import com.wondersgroup.tpa.service.resource.MethodSecurityMetadataSourceService;
import com.wondersgroup.tpa.service.resource.SecurityAccessDecisionManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//    @Autowired
//    private MethodSecurityMetadataSourceImpl methodSecurityMetadataSource;
//    @Autowired
//    private SecurityAccessDecisionManager accessDecisionManager;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication();
//    }
//
//    @Override
//    public MethodInterceptor methodSecurityInterceptor() throws Exception {
//        return super.methodSecurityInterceptor();
//    }
//
//    @Override
//    protected AccessDecisionManager accessDecisionManager() {
//        return accessDecisionManager;
//    }
//
//    @Override
//    public MethodSecurityMetadataSource methodSecurityMetadataSource() {
//        return methodSecurityMetadataSource;
//    }
    @Autowired
    private MethodSecurityMetadataSourceService methodSecurityMetadataSource;
    @Autowired
    private SecurityAccessDecisionManager accessDecisionManager;

    @Override
    public MethodInterceptor methodSecurityInterceptor() throws Exception {
        return customerMethodInterceptor();
    }

    @Bean
    public MethodInterceptor customerMethodInterceptor(){
        MethodSecurityInterceptor interceptor = new MethodSecurityInterceptor();
        interceptor.setAccessDecisionManager(accessDecisionManager);
        interceptor.setSecurityMetadataSource(methodSecurityMetadataSource);
        return interceptor;
    }
}