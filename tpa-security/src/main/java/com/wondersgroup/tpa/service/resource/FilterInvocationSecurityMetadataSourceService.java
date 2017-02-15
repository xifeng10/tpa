package com.wondersgroup.tpa.service.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.wondersgroup.tpa.mapper.SResourceMapper;
import com.wondersgroup.tpa.mapper.SRoleMapper;
import com.wondersgroup.tpa.model.SResource;
import com.wondersgroup.tpa.model.SRole;
import com.wondersgroup.tpa.model.SRoleResource;
import com.wondersgroup.tpa.service.IResourceService;
import com.wondersgroup.tpa.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * 此类用于角色对应的资源数据的管理 最核心的地方，就是提供某个资源对应的权限定义，<br/>
 * 即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 *
 * @author Charles 2011-09-14
 */
@Service
public class FilterInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    /**
     * 非权限管理菜单，只是作为一个占位符
     */
    public static final String DEFAULT_URL = "#";
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private List<SResource> resources;
    public static final String ALL_METHOD = "(GET|HEAD|POST|PUT|PATCH|DELETE|OPTIONS|TRACE)";
    public static final String _METHOD = HiddenHttpMethodFilter.DEFAULT_METHOD_PARAM;

    /**
     * 加载相关的资源权限的定义，将定义的权限构建ConfigAttribute对象,存入
     * Collection<T>泛型集合中，并将该集合作为value。将角色拥有的资源的url作为key,由此
     * 构建一个map集合将资源对应的角色信息对应存放。
     */
//	@PostConstruct
    public void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        resources = resourceService.findAll();
        List<SRole> roles = null;
        Collection<ConfigAttribute> attributes = null;
        ConfigAttribute attribute = null;
        for (SResource resource : resources) {
            if (resource.getEnable()) {
                attributes = new ArrayList<ConfigAttribute>();
                roles = roleService.queryRolesByResourceId(resource.getId());
                for (SRole role : roles) {

                    attribute = new SecurityConfig(role.getName());
                    attributes.add(attribute);
                }
                resourceMap.put(getResourceMapKey(resource), attributes);
            }
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        String url = ((FilterInvocation) object).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        HttpServletRequest request = ((FilterInvocation) object)
                .getHttpRequest();
        String method = request.getParameter(_METHOD);
        if (StringUtils.isEmpty(method)) {
            method = request.getMethod().toUpperCase();
        } else {
            method = method.toUpperCase();
        }
        url += "?" + _METHOD + "=" + method;
        Collection<ConfigAttribute> attributes = null;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        for (Entry<String, Collection<ConfigAttribute>> entry : resourceMap
                .entrySet()) {
            if (Pattern.compile(entry.getKey()).matcher(url).find()) {
                attributes = entry.getValue();
                if (entry.getKey().indexOf(ALL_METHOD) != -1) {
                    return attributes;
                }
            }
        }
        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public List<SResource> getAllResources() {
        return resources;
    }

    public Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return resourceMap;
    }

    public static String getResourceMapKey(SResource resource) {
        String method = StringUtils.isEmpty(resource.getMethod()) ? ALL_METHOD
                : resource.getMethod();
        return DEFAULT_URL.equals(resource.getSecurityUrl()) ? resource.getId() + "_"
                + DEFAULT_URL : resource.getSecurityUrl() + "\\?" + _METHOD
                + "=" + method;
    }
}
