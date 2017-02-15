package com.wondersgroup.tpa.conf;

import com.wondersgroup.tpa.model.SEmployee;
import com.wondersgroup.tpa.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TPAAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IEmployeeService employeeService;
    public static Md5PasswordEncoder ENCODER = new Md5PasswordEncoder();

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        SEmployee employee = (SEmployee) employeeService.loadUserByUsername(username);
        if(employee == null){
            throw new BadCredentialsException("Username not found.");
        }

        //加密过程在这里体现
        if (!ENCODER.encodePassword(password,username).equals(employee.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = employee.getAuthorities();
        return new UsernamePasswordAuthenticationToken(employee, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}