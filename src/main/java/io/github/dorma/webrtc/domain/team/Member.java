package io.github.dorma.webrtc.domain.team;

import io.github.dorma.webrtc.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Member {

    // 회원 아이디
    @Id
    private String memberId;

    // 회원 패스워드
    @Column(nullable = false)
    private String memberPwd;

    // 회원 이름
    @Column(nullable = false)
    private String memberName;

    // 회원 학번
    @Column(nullable = false)
    private String memberStudentId;

    // 회원 연락처
    @Column(nullable = false)
    private String memberPhone;

    // 학과
    @Column(nullable = false)
    private String memberMajor;

    // 단과대학
    @Column(nullable = false)
    private String memberCollege;

    // 자기소개
    private String memberIntroduce;

    // 포인트
    @Column(nullable = false)
    @ColumnDefault("0")
    private int memberPoint;

    // 0 : 일반유저, 1: 관리자
    @Enumerated(EnumType.STRING)
    private Role memberPermission;

    public void encryptPassword(PasswordEncoder passwordEncoder){
        memberPwd = passwordEncoder.encode(memberPwd);
    }
}