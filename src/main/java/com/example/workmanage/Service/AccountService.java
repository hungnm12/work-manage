package com.example.workmanage.Service;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.ReqDto.User.LoginReqDto;
import com.example.workmanage.Model.ReqDto.User.SignUpReqDto;
import com.example.workmanage.Model.ResDto.GeneralResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface AccountService {
    GeneralResponse signUp(SignUpReqDto signUpReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;

    GeneralResponse login(LoginReqDto loginReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException;
}
