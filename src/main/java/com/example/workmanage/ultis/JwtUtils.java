package com.example.workmanage.ultis;

import com.example.workmanage.Model.Entity.User;
import com.example.workmanage.Repository.UserRepo;
import com.example.workmanage.Service.ServiceImpl.JwtService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

public class JwtUtils {
    public static User getUserFromToken(JwtService jwtService, UserRepo userRepo, String token) {
        String  email = jwtService.extractUsername(token);

        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found" + email));

    }
    public static Date getCreateAt(JwtService jwtService, String token) {

        return jwtService.extractCreateAt(token);
    }
}
