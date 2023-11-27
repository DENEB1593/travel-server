package io.everyone.travel.domain;

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
@SQLDelete(sql = "update travel set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;

    String authId;

    String nickname;

    String email;

    String provider;

    LocalDateTime lastLoginAt;

    protected User() { }

    @Builder
    public User(String authId, String nickname, String email, String provider, LocalDateTime lastLoginAt) {
        this.authId = authId;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
        this.lastLoginAt = lastLoginAt;
    }


}
