package com.study.springstudy.springmvc.chap05.dto.request;

import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SignUpDto {

    @NotBlank
    @Size(min = 4, max = 14)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 포함해야 합니다.")
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 4, max = 14)
    private String name;

    @NotBlank
    @Email
    private String email;

    public Member toEntity() {

        return Member.builder()
                .account(this.account)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .build();

//        return new Member(
//                this.account,
//                this.password,
//                this.name,
//                this.email,
//                null,
//                null
//        );
    }

}
