package pl.edu.pb.wi.forumbiznesowe.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

                .antMatchers("/auth/*",
                        "/categories/all", "/categories/find",
                        "/posts/all", "/posts", "/posts/category",
                        "/replies", "/replies/post",
                        "/users/username").permitAll()

                .antMatchers("/posts/add", "/posts/update", "/posts/delete",
                        "/replies/*", "/replies",
                        "/users/username", "/users")
                .hasAnyAuthority(ROLE_USER.getValue(), ROLE_VIP.getValue(), ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers("/reports/add",
                        "/posts/observed")
                .hasAnyAuthority(ROLE_VIP.getValue(), ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers("/categories/add", "/categories/edit", "/categories/delete",
                        "/posts/accept",
                        "/reports", "/reports/all")
                .hasAnyAuthority(ROLE_MODERATOR.getValue(), ROLE_ADMIN.getValue())

                .antMatchers("/users/*", "/users")
                .hasAuthority(ROLE_ADMIN.getValue())

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
