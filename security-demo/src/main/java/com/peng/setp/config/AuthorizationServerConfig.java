package com.peng.setp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public TokenStore tokenStore(){
        // redis 存储方式,如果是数据库方式，就是要定义一个datasource
        return new MyRedisTokenStore(redisConnectionFactory);

    }
    /*
     * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
    }

    /*
     * AuthorizationServerSecurityConfigurer 用来配置令牌端点(Token Endpoint)的安全约束
     * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    /*

    ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
*   1.授权码模式（authorization code）
    2.简化模式（implicit）
    3.密码模式（resource owner password credentials）
    4.客户端模式（client credentials）
* */
    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception{
        String finalPassword = "{bcrypt}" + new BCryptPasswordEncoder().encode("736499");
        clientDetailsServiceConfigurer
                .inMemory()
                .withClient("service-demo")
                .resourceIds("service-demo")
                .authorizedGrantTypes("client-credentials","refresh_token")
                .scopes("all","read","write","aa")
                .authorities("client_credentials")
                .secret(finalPassword)
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000)
                .and()
                .withClient("webapp-demo")
                .resourceIds("webapp-demo")
                .authorizedGrantTypes("password","refreash_token")
                .scopes("server")
                .authorities("password")
                .secret(finalPassword)
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }


}
