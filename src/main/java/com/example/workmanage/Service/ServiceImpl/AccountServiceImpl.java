package com.example.workmanage.Service.ServiceImpl;

import com.example.workmanage.Exception.ResponseException;
import com.example.workmanage.Model.Entity.User;
import com.example.workmanage.Model.ReqDto.User.LoginReqDto;
import com.example.workmanage.Model.ReqDto.User.SignUpReqDto;
import com.example.workmanage.Model.ResDto.GeneralResponse;
import com.example.workmanage.Repository.TaskRepo;
import com.example.workmanage.Repository.UserRepo;
import com.example.workmanage.Service.AccountService;
import com.example.workmanage.ultis.CheckUltis;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    @Override
    public GeneralResponse signUp(SignUpReqDto signUpReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {
        if (!CheckUltis.isValidEmail(signUpReqDto.getEmail())){
            return  new GeneralResponse();
        }
        if (userRepo.existsUserByEmail(signUpReqDto.getEmail())){

        }
        else  if (signUpReqDto.getEmail().isEmpty() || signUpReqDto.getPassword().isEmpty()
                || (signUpReqDto.getEmail().isEmpty() && signUpReqDto.getPassword().isEmpty())) {
            return new GeneralResponse();
        }
        if (!CheckUltis.isValidPassword(signUpReqDto.getPassword()))
        {
            return new GeneralResponse();
        }
        User user = User.builder().email(signUpReqDto.getEmail())
                .password(passwordEncoder.encode(signUpReqDto.getPassword()))
                .createdTime(new Date(System.currentTimeMillis()))
                .build();

        userRepo.save(user);
        return new GeneralResponse();
    }

    @Override
    public GeneralResponse login(LoginReqDto loginReqDto) throws ResponseException, ExecutionException, InterruptedException, TimeoutException {

        if (!CheckUltis.isValidEmail(loginReqDto.getEmail())){
            return new GeneralResponse();
        }

        if (!CheckUltis.isValidPassword(loginReqDto.getPassword())) {
            return new GeneralResponse();
        }
        else if (loginReqDto.getEmail().isEmpty() && loginReqDto.getPassword().isEmpty() || loginReqDto.getPassword().isEmpty() || loginReqDto.getEmail().isEmpty()) {
            return new GeneralResponse();
        }
        

        return new GeneralResponse();
    }


}
