package org.personal.details.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ChannelSecurityConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("unused")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        return http.requiresChannel(SecurityConfig::ensureRequestRequiresSecurity)
                .authorizeRequests(SecurityConfig::authenticateRequests)
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    private static ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticateRequests
            (final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorize) {
        return authorize.anyRequest().authenticated();
    }

    private static ChannelSecurityConfigurer<HttpSecurity>.ChannelRequestMatcherRegistry ensureRequestRequiresSecurity
            (final ChannelSecurityConfigurer<HttpSecurity>.ChannelRequestMatcherRegistry channel) {
        return channel.anyRequest().requiresSecure();
    }

}
