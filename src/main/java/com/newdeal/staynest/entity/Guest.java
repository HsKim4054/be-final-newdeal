package com.newdeal.staynest.entity;

import com.newdeal.staynest.dto.GuestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="guest_id")
    private Long id;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum role;

    @Column(name = "join_dt",nullable = false)
    private LocalDateTime joinDt;

    @Column(name = "expire_dt",nullable = false)
    private LocalDateTime expireDt;

    @Column
    private String image;

    @PrePersist
    protected void onCreate() {
        this.joinDt = LocalDateTime.now();
        //일단 7일로 설정함. 이메일 인증 안하면 자동 탈퇴하는 방식으로 구현할 예정.
        this.expireDt = LocalDateTime.now().plusDays(7);
    }

    public GuestDto toDto() {
        return GuestDto.builder()
                .guestId(this.id)
                .guestName(this.guestName)
                .email(this.email)
                .password(this.password)
                .phone(this.phone)
                .role(this.role)
                .joinDt(this.joinDt)
                .expireDt(this.expireDt)
                .image(this.image)
                .build();
    }

}
