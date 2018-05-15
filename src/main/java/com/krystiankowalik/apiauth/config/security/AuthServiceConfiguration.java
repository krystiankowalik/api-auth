package com.krystiankowalik.apiauth.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableAutoConfiguration
class AuthServiceConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder userPasswordEncoder;

    private DataSource dataSource;

    @Value("${security.clientId}")
    private String CLIENT_ID;

    @Value("${security.secret}")
    private String SECRET;

    @Autowired
    AuthServiceConfiguration(AuthenticationManager authenticationManager, PasswordEncoder userPasswordEncoder, @Qualifier("dataSource") DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.userPasswordEncoder = userPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients
                .inMemory()
                .withClient("html5")
                .secret(userPasswordEncoder.encode("password"))
                .authorizedGrantTypes("password")
                .scopes("openid");*/
        clients
                //.jdbc(dataSource)
                .inMemory()
                //.passwordEncoder(userPasswordEncoder)
                .withClient(CLIENT_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("openid")
                .scopes("read", "write", "trust")
                //.resourceIds("oauth2-resource")
                .secret(userPasswordEncoder.encode(SECRET))
                .accessTokenValiditySeconds(6000)
                .refreshTokenValiditySeconds(50000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.authenticationManager(authenticationManager);
        endpoints.authenticationManager(authenticationManager)
                //.tokenStore(tokenStore())
                .approvalStoreDisabled();

    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                .passwordEncoder(userPasswordEncoder);
        //.allowFormAuthenticationForClients();
    }
}