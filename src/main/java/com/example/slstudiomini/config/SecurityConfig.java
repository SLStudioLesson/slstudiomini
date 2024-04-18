package com.example.slstudiomini.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("!dev")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(login -> login
                // ログイン成功時に遷移するURL
                .defaultSuccessUrl("/")
                // ログイン処理を行うURL(POST)
                // .loginProcessingUrl("/login")
                // ログインページを表示するURL(GET)
                // .loginPage("/login")
                // ログインできなかった時のURL
                // .failureUrl("/login?error")
                // これを付けたページはログイン無しでもアクセス出来る
                // .permitAll()
            ).logout(logout -> logout
                .logoutSuccessUrl("/")
            ).authorizeHttpRequests(authz -> authz
                // resourceフォルダの直下(cssやimg)は認証が無くてもアクセスできる
                .requestMatchers(PathRequest.toStaticResources()
                    .atCommonLocations()).permitAll()
                // URL「/」にはログイン無しでもアクセスできる
                // .requestMatchers("/").permitAll()
                .requestMatchers("/api/**").permitAll()
                // /study 以降のURLにはロールが「USER」のみアクセス出来る
                .requestMatchers("/study/**").hasRole("USER")
                // /admin 以降のURLにはロールが「ADMIN」のみアクセス出来る
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            );
        return http.build();
    }

    // これを書いておくとパスワードがハッシュ化されます
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}