package com.landiao.security.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.landiao.security.entity.User;
import com.landiao.security.repository.UserRepository;

@Component  
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserRepository userRepository;
  
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName);  
        if (user == null) {  
            throw new UsernameNotFoundException("未找到用户名: [" + userName + "]");  
        }  
        SecurityUser securityUser = new SecurityUser(user);  
        return securityUser;  
    }  
  
} 