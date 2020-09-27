package com.eval.coronakit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eval.coronakit.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguaration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
	//	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		

	}

	@SuppressWarnings("deprecation")
	protected void configure(AuthenticationManagerBuilder auth,HttpSecurity http) throws Exception {

		
		  http.authorizeRequests() .antMatchers("/admin/**").hasAuthority("ADMIN")
		  .antMatchers("/user/**").hasAuthority("USER");
		 	UserBuilder builder =  User.withDefaultPasswordEncoder();
		 	auth.inMemoryAuthentication()
		 		.withUser(builder.username("Admin").password("admin").roles("ADMIN"))
		 		.withUser(builder.username("First").password("abc").roles("USER"))
		 		.withUser(builder.username("Second").password("abc").roles("USER"));

		http.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("unm").passwordParameter("pwd");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
		
		http.exceptionHandling().accessDeniedPage("/pages/accessDeniedPage.jsp");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
