package com.example.workmanage.Model.ReqDto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReqDto {
    private String email;
    private String password;
    private String username;
}
