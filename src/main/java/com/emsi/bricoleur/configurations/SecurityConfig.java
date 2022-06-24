package com.emsi.bricoleur.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
		auth.jdbcAuthentication().passwordEncoder(encoder)
		     .dataSource(dataSource)
			.usersByUsernameQuery("select email as principal, password as credentials, active from utilisateur where email=?")
			.authoritiesByUsernameQuery("select email as principal, role from utilisateur where email=?")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin().loginPage("/connect");
		http.formLogin();
		http.authorizeRequests().antMatchers("/missions/create","/missions/edit/**","/missions/delete/*").authenticated();
		http.authorizeRequests().antMatchers("/","/missions/**","/webjars/**","/inscription").permitAll();
		http.authorizeRequests().antMatchers("/missions/create","/missions/edit/**","/missions/delete/*").hasAnyAuthority("client","admin");
		http.logout().logoutSuccessUrl("/");
		http.exceptionHandling().accessDeniedPage("/unauthorized");
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
