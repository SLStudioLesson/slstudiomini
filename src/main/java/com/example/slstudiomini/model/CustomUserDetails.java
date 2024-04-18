package com.example.slstudiomini.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// UserDetailsを継承しているので
// Spring Securityの認証クラスとして認識される
public class CustomUserDetails implements UserDetails {
    // 自作のUserクラスを持つように拡張する
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // カスタム User エンティティのパスワードを返す
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // カスタム User エンティティのユーザー名を返す
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // アカウントが期限切れでないかを示す
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // アカウントがロックされていないかを示す
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 資格情報（パスワード）が期限切れでないかを示す
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled(); // カスタム User エンティティでユーザーが有効かどうかを返す
    }

    // カスタム User エンティティにアクセスするための追加メソッド
    public User getUser() {
        return user;
    }
}