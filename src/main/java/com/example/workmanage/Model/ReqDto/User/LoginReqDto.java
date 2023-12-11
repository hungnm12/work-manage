package com.example.workmanage.Model.ReqDto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {

    private String email;
    private String password;
}
