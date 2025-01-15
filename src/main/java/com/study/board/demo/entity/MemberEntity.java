package com.study.board.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "t_member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;
    @Column(name="loginId", nullable = false)
    private String loginId;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="nickName", nullable = false)
    private String nickName;
    @Column(name="signUpDate", nullable = false)
    private Date signUpDate;

    public String validCheckMember(){
        if(this.loginId == null || this.loginId.equals("")){
            return "아이디를 입력해주세요.";
        }else if(this.name == null || this.name.equals("")){
            return "이름를 입력해주세요.";
        }else if(this.password == null || this.password.equals("")){
            return "비밀번호를 입력해주세요.";
        }else if(this.email == null || this.email.equals("")){
            return "이메일을 입력해주세요.";
        }else if(this.nickName == null || this.nickName.equals("")){
            return "닉네임을 입력해주세요.";
        }

        return "";
    }
}
