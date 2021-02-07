package ch.fhnw.eaf.rental;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity  // activate Web Security
@EnableGlobalMethodSecurity(securedEnabled = true)  // activate Method Security using Annotation @Secured
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure http for web services
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.formLogin().disable();
        http.logout().disable();
        http.csrf().disable();
        http.httpBasic(withDefaults());

        http.antMatcher("/swagger-ui/**")
            .authorizeRequests(authorize -> 
                    authorize
                        .anyRequest().authenticated()
            );
    }
}