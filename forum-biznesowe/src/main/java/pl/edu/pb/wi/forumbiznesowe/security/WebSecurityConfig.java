package pl.edu.pb.wi.forumbiznesowe.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.edu.pb.wi.forumbiznesowe.security.jwt.AuthEntryPointJwt;
import pl.edu.pb.wi.forumbiznesowe.security.jwt.AuthTokenFilter;
import pl.edu.pb.wi.forumbiznesowe.security.services.UserDetailsServiceImpl;

import static pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

                .antMatchers("/auth/**").permitAll()

                .antMatchers(HttpMethod.GET, "/categories", "/posts", "/replies/post/**").
                hasAnyAuthority(ROLE_USER.getValue(), ROLE_VIP.getValue(), ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers("/replies/post", "/posts/suggest").
                hasAnyAuthority(ROLE_USER.getValue(), ROLE_VIP.getValue(), ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers(HttpMethod.POST, "/posts").
                hasAnyAuthority(ROLE_VIP.getValue(), ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers(HttpMethod.POST, "/categories", "/replies/")
                .hasAnyAuthority(ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
