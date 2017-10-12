package com.landiao.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.landiao.security.support.CustomUserDetailsService;
import com.landiao.security.support.LoginSuccessHandler;
import com.landiao.security.support.MySecurityFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired  
    private MySecurityFilter mySecurityFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override  
    public AuthenticationManager authenticationManagerBean() throws Exception {  
		return super.authenticationManagerBean();  
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)
		.authorizeRequests()
		.antMatchers("/").permitAll()
		//.anyRequest().authenticated() // 其他所有资源都需要认证，登陆后访问
		//.antMatchers("/index").hasAuthority("ADMIN") // 登陆后之后拥有“ADMIN”权限才可以访问/index方法，否则系统会出现“403”权限不足的提示
		.and()
		.formLogin()
		.loginPage("/")// 指定登录页是”/”
		.loginProcessingUrl("/login")
		.usernameParameter("username")
        .passwordParameter("password")
		.successHandler(loginSuccessHandler())
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/") // 退出登录后的默认网址是”/”
		.invalidateHttpSession(true)
		.and()
		.rememberMe()// 登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
		.tokenValiditySeconds(1209600);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/static/**", "/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/fonts/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 指定密码加密所使用的加密器为passwordEncoder()
		// 需要将密码加密后写入数据库
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		auth.eraseCredentials(false);
	}

	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
}
