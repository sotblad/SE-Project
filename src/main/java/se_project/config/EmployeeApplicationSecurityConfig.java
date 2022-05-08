package se_project.config;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class EmployeeApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,password,enabled "
                + "from instructors "
                + "where username = ?")
              .authoritiesByUsernameQuery("select username,authority "
                + "from authorities "
                + "where username = ?");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() 
		  .antMatchers("/login*", "/register*", "/postRegister*")
		  .permitAll()
		.and()
		  .authorizeRequests()
		  .anyRequest()
		  .authenticated()
		.and() 
		  .formLogin()
			.loginPage("/login")
		    .defaultSuccessUrl("/", true)
		.and()
		  .logout()
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .deleteCookies("JSESSIONID");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}	
}





