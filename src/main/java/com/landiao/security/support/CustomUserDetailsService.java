package com.landiao.security.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.landiao.security.entity.SystemRole;
import com.landiao.security.entity.User;
import com.landiao.security.repository.SystemRoleRepository;
import com.landiao.security.repository.UserRepository;

@Component  
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private SystemRoleRepository systemRoleRepository;
  
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);  
        if (user == null) {  
            throw new UsernameNotFoundException("未找到用户名: [" + userName + "]");  
        }
        
        List<SystemRole> userRoles = systemRoleRepository.findByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(user, userRoles);  
        return securityUser;  
    }  
  
} 