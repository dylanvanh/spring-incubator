package entelect.training.incubator.spring.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Disclaimer! In a production system you will never store your credentials in either clear text or in the code.
     * It is done here so that development is both easy to understand and change.
     * The commented code below shows you how to connect to a DB. You will also want to use some kind of password encoding/hashing.
     */

//        @Autowired
//        private DataSource securityDataSource;

//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication().dataSource(securityDataSource);
//        }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // !!! Disclaimer: NEVER DISABLE CSRF IN PRODUCTION !!!
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                .antMatchers("/swagger*/**").permitAll()
                .antMatchers(HttpMethod.GET, "/customers/*/").permitAll()
                .antMatchers(HttpMethod.GET, "/customers").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/customers").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/customers/*/").hasAnyRole("USER", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .httpBasic();
    }

}
