package com.aselsanbackend.AselsanBackend.controller;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private String generatedAuthenticationCode;
    private PasswordHasher passwordHasher;
    private final UserService userService;

    @PostMapping("api/kisi")
    public ResponseEntity<UserDto> ekle(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("api/listele")
    public ResponseEntity<List<UserDto>> tumunuListele(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("api/generateRandomString")
    public String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        generatedAuthenticationCode = sb.toString();
        return generatedAuthenticationCode;
    }

    @GetMapping("/{tcKimlikNo}")
    public String getUserByTcKimlikNo(@PathVariable String tcKimlikNo) {
        User user = userService.findByTcKimlikNo(tcKimlikNo);

        if (user != null) {
            String response = user.getAd()+user.getSoyad();
            return response;
        } else {
            return "Başarısız";
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto loginRequest) {
        User user = userService.findByTcKimlikNo(loginRequest.getTcKimlikNo());
        String hashedPassword = PasswordHasher.hashPassword(loginRequest.getTcKimlikNo() + loginRequest.getPassword());
        if (user != null && user.getPassword().equals(hashedPassword) && generatedAuthenticationCode.equals(loginRequest.getAuthentication())) {
            return "Giriş başarılı!";
        }else if (!generatedAuthenticationCode.equals(loginRequest.getAuthentication())) { return "Doğrulama kodunu lütfen doğru giriniz !!!" ;}
        else {
            return "Giriş başarısız! Lütfen bilgilerinizi kontrol edin.";
        }
    }
}
