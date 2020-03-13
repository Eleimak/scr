package tam.edu.scr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static tam.edu.scr.security.ApplicationUserPermission.*;
import static tam.edu.scr.security.ApplicationUserRole.*;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/css","/js", "img").permitAll()
                //.antMatchers("/api/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority(PERSON_READ.name())
                //.antMatchers(HttpMethod.GET,"/api/person/get/**").hasRole(DOCTOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User
                            .builder()
                            .username("user")
                            .password(passwordEncoder.encode("user"))
                            .roles(USER.name())
                            .authorities(USER.getGrantedAuthorities())
                            .build();
        UserDetails doctor = User
                            .builder()
                            .username("doctor")
                            .password(passwordEncoder.encode("doctor"))
                            .roles(DOCTOR.name())
                            .authorities("PERSON_READ")
                            .build();
        UserDetails admin = User
                            .builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(ADMIN.name())
                            .authorities("PERSON_READ")
                            .build();
        return  new InMemoryUserDetailsManager(user, doctor, admin);
    }
}
