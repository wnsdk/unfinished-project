package com.daildra.daildra.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "user")
public class User implements UserDetails {

    @Id
    private String userUid;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userNickname;

    @Column(updatable = false)
    private LocalDate signUpDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // JSON 역직렬화 가능, 직렬화 불가능 (비밀번호를 넣을 순 있지만, 조회는 안 됨)
    @Column(nullable = false)
    private String userPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> userRoles = new ArrayList<>();

//    계정이 가지고 있는 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

//    계정의 비밀번호를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.userPassword;
    }

//    계정의 고유 아이디를 리턴
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userUid;
    }

//    계정이 만료됐는지 리턴 (true는 만료되지 않았음을 의미)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    계정이 잠겨있는지 리턴 (true는 잠기지 않았음을 의미)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//    비밀번호가 만료됐는지 리턴 (true는 만료되지 않았음을 의미)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    계정이 활성화돼 있는지 리턴 (true는 활성화 상태를 의미)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
