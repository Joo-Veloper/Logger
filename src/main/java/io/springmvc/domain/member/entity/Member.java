package io.springmvc.domain.member.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId;// 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password; // 비밀번호
}
