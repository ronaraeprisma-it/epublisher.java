package nl.prismait.epublisher.java.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        // Disable CSRF (Cross-Site Request Forgery) protection for simplicity
	        http.csrf().disable();

	        // Permit all requests without any authentication or authorization
	        http.authorizeRequests().anyRequest().permitAll();
	    }
}
