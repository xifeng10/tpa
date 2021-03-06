package com.wondersgroup.tpa.service.resource;

import java.lang.reflect.Method;
import java.util.*;

import com.wondersgroup.tpa.model.SMethod;
import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.tpa.service.IMethodService;
import com.wondersgroup.tpa.service.IRoleService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.AbstractMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;

import org.springframework.stereotype.Service;

/**
 * 方法级别的权限控制，元数据加载。
 *
 * @author yinlei
 *         2012-4-4 下午5:54:26
 */
@Service
public class MethodSecurityMetadataSourceService implements MethodSecurityMetadataSource {
    public static final String SHARP = "#";
    @Autowired
    private IMethodService methodService;
    @Autowired
    private IRoleService roleService;

    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

    public final Collection<ConfigAttribute> getAttributes(Object object) {
        if (object instanceof MethodInvocation) {
            MethodInvocation mi = (MethodInvocation) object;
            Object target = mi.getThis();
            Class<?> targetClass = null;

            if (target != null) {
                targetClass = target instanceof Class<?> ? (Class<?>) target
                        : AopProxyUtils.ultimateTargetClass(target);
            }
            Collection<ConfigAttribute> attrs = getAttributes(mi.getMethod(), targetClass);
            if (attrs != null && !attrs.isEmpty()) {
                return attrs;
            }
            if (target != null && !(target instanceof Class<?>)) {
                attrs = getAttributes(mi.getMethod(), target.getClass());
            }
            return attrs;
        }

        throw new IllegalArgumentException("Object must be a non-null MethodInvocation");
    }

    public final boolean supports(Class<?> clazz) {
        return (MethodInvocation.class.isAssignableFrom(clazz));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
        if(resourceMap.isEmpty()){
            loadMethodAuthConfig();
        }
        String methodFullPath = targetClass.getName() + SHARP + method.getName();
        return resourceMap.get(methodFullPath);
    }

    public void loadMethodAuthConfig() {
        List<SMethod> methods = methodService.findAll();
        List<SRole> roles = null;
        Collection<ConfigAttribute> attributes = null;
        ConfigAttribute attribute = null;
        for (SMethod m : methods) {
            attributes = new ArrayList<ConfigAttribute>();
            roles = roleService.queryRolesByMethodId(m.getId());
            for (SRole role : roles) {
                attribute = new SecurityConfig(role.getName());
                attributes.add(attribute);
            }
            resourceMap.put(m.getPackageName()+"."+m.getClassName() + SHARP + m.getMethodName(), attributes);
        }
    }
}