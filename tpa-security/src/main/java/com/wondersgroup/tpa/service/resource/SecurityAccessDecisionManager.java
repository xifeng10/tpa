package com.wondersgroup.tpa.service.resource;

import java.util.*;

import org.springframework.security.access.*;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.*;
import org.springframework.stereotype.Service;

/**
 * 自定义决策管理器，用于判断用户请求的资源是否和用户角色匹配
 * SecurityAccessDecisionManager实现AccessDecisionManager接口，拥有spring security 访问控制
 * 
 * @author Charles 2011-09-14
 * 
 */
@Service
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 此构造方法有spring容器调用
	 */
	public SecurityAccessDecisionManager() {
	}

	/**
	 * 传递参数的访问控制决策。如果访问被拒绝作为身份验证不持有所需的权限或ACL权限，则抛出AccessDeniedException 异常
	 * 
	 * @param authentication
	 *            该方法的调用者。Authentication接口表示验证身份的主体令牌，由此可以获取 已经验证用户的用户及角色信息
	 * @param obj
	 *            称之为担保对象。可以是一个要求保护的方法或者URL
	 * @param attributes
	 *            与保护对象相关联的配置属性.ConfigAttribute接口是用来存储安全系统相关的配置属性。
	 *            由FilterInvocationSecurityMetadataSource实现类中获取
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void decide(Authentication authentication, Object obj,
			Collection<ConfigAttribute> attributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
//		if (attributes == null) {
//			return;
//		}
		Iterator<ConfigAttribute> configIterator = attributes.iterator();
		// 获取当前登录用户具有的角色信息
		Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) authentication
				.getAuthorities();
		// 对请求的资源所需权限进行遍历。
		while (configIterator.hasNext()) {
			ConfigAttribute attribute = configIterator.next();
			String needRole = ((SecurityConfig) attribute).getAttribute();
			// 对当前登录用户的角色信息进行遍历
			for (GrantedAuthority authority : grantedAuthorities) {
				// 获取当前登录用户的角色名次并与其请求资源所需角色的名称进行比对。如果匹配返回
				if (needRole.equals(authority.getAuthority())) {
					return;
				}
			}
		}
		/*
		 * 如果当前登录用户的角色名次与其请求资源所需角色的名称不匹配，抛出AccessDeniedException异常， 最终有spring
		 * security处理此异常，并根据安全配置文件的配置返回相关错误提示页面
		 */
		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> cls) {
		return true;
	}

}
