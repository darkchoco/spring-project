package com.springboot.api.dto;

import lombok.Getter;
import lombok.Setter;

// 예제 5.8
@Setter
@Getter
public class MemberDto {

    private String name;
    private String email;
    private String organization;

    @Override
    public String toString() {
        return "MemberDTO {" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", organization='" + organization + '\'' +
            '}';
    }
}
