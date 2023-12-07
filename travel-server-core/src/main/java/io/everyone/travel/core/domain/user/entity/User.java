package io.everyone.travel.core.domain.user.entity;

import io.everyone.travel.core.config.BaseEntity;
import io.everyone.travel.core.domain.user.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    @Column(name = "auth_id", nullable = false)
    String authId;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "email", nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    AuthProvider provider;

    @Column(name = "last_login_at")
    LocalDateTime lastLoginAt;

    protected User() { }

    @Builder
    public User(String authId, String nickname, String email, AuthProvider provider, LocalDateTime lastLoginAt) {
        this.authId = authId;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
        this.lastLoginAt = lastLoginAt;
    }


}
