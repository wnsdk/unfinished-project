package com.daildra.daildra.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogInResultDto extends SignUpResultDto {

    private String token;

    @Builder
    public LogInResultDto(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
