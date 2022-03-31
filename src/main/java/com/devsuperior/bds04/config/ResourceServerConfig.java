package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final JwtTokenStore jwtTokenStore;
    private final Environment env;

    @Autowired
    public ResourceServerConfig(JwtTokenStore jwtTokenStore,
                                Environment env) {
        super();
        this.jwtTokenStore = jwtTokenStore;
        this.env = env;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getDefaultProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http
            .authorizeRequests()
            .antMatchers("/oauth/token","/h2-console/**").permitAll()
            .antMatchers(HttpMethod.GET,"/cities/**","/events/**").permitAll()
            .antMatchers(HttpMethod.POST,"/events/**").hasAnyRole("CLIENT","ADMIN")
            .antMatchers(HttpMethod.POST,"/cities/**").hasRole("ADMIN")
            .anyRequest().authenticated();

    }
}
