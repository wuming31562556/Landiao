package com.landiao.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.landiao.security.entity.SystemResource;
import com.landiao.security.entity.SystemRole;
import com.landiao.security.repository.SystemResourceRepository;
import com.landiao.security.repository.SystemRoleRepository;

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private SystemResourceRepository systemResourceRepository;
	
	@Autowired
	private SystemRoleRepository systemRoleRepository;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url
		FilterInvocation filterInvocation = (FilterInvocation) object;
		if (resourceMap == null) {
			loadResourceDefine();
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@PostConstruct
	private void loadResourceDefine() {
		
		//find all system role
		List<SystemRole> sysRoles = systemRoleRepository.findAll();
		
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<SystemResource> systemResources = null;
		String url = "";
		if(sysRoles != null && sysRoles.size() > 0) {
			for (SystemRole systemRole : sysRoles) {
				ConfigAttribute ca = new SecurityConfig(systemRole.getName());
				
				//find all can access url by role name
				systemResources = systemResourceRepository.findAllByRoleId(systemRole.getId());
				if(systemResources != null && systemResources.size() >0) {
					for(SystemResource systemResource : systemResources) {
						url = systemResource.getUrl();
						/*
						 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，
						 * 将权限增加到权限集合中。 sparta
						 */
						if (resourceMap.containsKey(url)) {

							Collection<ConfigAttribute> value = resourceMap.get(url);
							value.add(ca);
							resourceMap.put(url, value);
						} else {
							Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
							atts.add(ca);
							resourceMap.put(url, atts);
						}

					}
				}
				
			}
		}
		
	}

}
