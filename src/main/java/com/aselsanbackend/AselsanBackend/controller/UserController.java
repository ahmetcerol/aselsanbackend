package com.aselsanbackend.AselsanBackend.controller;

import com.aselsanbackend.AselsanBackend.dto.InfoDto;
import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.*;

import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.security.TcKimlikNoVerification;
import com.aselsanbackend.AselsanBackend.service.*;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private String generatedAuthenticationCode;
    private PasswordHasher passwordHasher;
    private final UserService userService;
    private final DetailedUserService detailedUserService;
    private final İlgiAlanlarıService i̇lgiAlanlarıService;
    private final StajBilgileriService stajBilgileriService;
    private final ProjeDeneyimleriService projeDeneyimleriService;
    private final EğitimBilgilerimService eğitimBilgilerimService;
    private final KariyerHedeflerimService kariyerHedeflerimService;
    private final TcKimlikNoVerification tcKimlikNoVerification;


    @GetMapping("/isUserActive/{tcKimlikNo}")
    public boolean isUserActive(@PathVariable String tcKimlikNo) {
        return detailedUserService.isuserActive(tcKimlikNo);
    }

    @GetMapping("/{tcKimlikNo}")
    public String doğrulaTCKimlik(@PathVariable String tcKimlikNo) {
        boolean isValid = tcKimlikNoVerification.isValidTcKimlik(tcKimlikNo);
        if (isValid) {
            return "T.C. Kimlik Numarası doğrulandı";
        } else {
            return "Geçersiz T.C Kimlik Numarası";
        }
    }


    @GetMapping("/tcKimlikNo/{tcKimlikNo}")
    public ResponseEntity<Map<String, String>> getKullaniciInfoByTcKimlikNo(@PathVariable String tcKimlikNo) {
        User kullanici = userService.findByTcKimlikNo(tcKimlikNo);
        if (kullanici == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> kullaniciInfo = new HashMap<>();
        kullaniciInfo.put("ad", kullanici.getAd());
        kullaniciInfo.put("soyad", kullanici.getSoyad());
        kullaniciInfo.put("eposta", kullanici.getEPosta());

        return ResponseEntity.ok(kullaniciInfo);
    }

    @GetMapping("/listeleme/{tcKimlikNo}")
    public ResponseEntity<Map<String, String>> listelemek(@PathVariable String tcKimlikNo) {
        User kullanici = userService.findByTcKimlikNo(tcKimlikNo);
        if (kullanici == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> kullaniciInfo = new HashMap<>();
        kullaniciInfo.put("ad", kullanici.getAd());
        kullaniciInfo.put("soyad", kullanici.getSoyad());
        kullaniciInfo.put("eposta", kullanici.getEPosta());
        kullaniciInfo.put("tcKimlikNo", kullanici.getTcKimlikNo());
        kullaniciInfo.put("stajBilgileri", userService.getStajBilgileri(kullanici).toString());
        kullaniciInfo.put("egitimBilgilerim", userService.getEğitimBilgilerim(kullanici).toString());
        kullaniciInfo.put("projeDeneyimleri", userService.getProjeDeneyimleri(kullanici).toString());


        return ResponseEntity.ok(kullaniciInfo);
    }


    @PostMapping("{tcKimlikNo}/kariyerHedeflerim")
    public ResponseEntity<KariyerHedeflerim> kariyerHedefiKaydet(@PathVariable String tcKimlikNo,
                                                                 @RequestBody KariyerHedeflerim kariyerHedef) {

        User existingUser = userService.findByTcKimlikNo(tcKimlikNo);

        if (existingUser == null) {
            return ResponseEntity.badRequest().build();
        } else {
            kariyerHedef.setTcKimlikNo(tcKimlikNo);
            KariyerHedeflerim savedHedef = kariyerHedeflerimService.save(kariyerHedef);
            return ResponseEntity.ok(savedHedef);
        }

    }


    @PostMapping("{tcKimlikNo}/egitimBilgilerim")
    public ResponseEntity<UserDto> addEgitimBilgisi(@PathVariable String tcKimlikNo,
                                                    @RequestBody List<EğitimBilgilerim> eğitimBilgilerims) {
        UserDto response = eğitimBilgilerimService.save(tcKimlikNo, eğitimBilgilerims);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{tcKimlikNo}/projeDeneyimleri")
    public ResponseEntity<UserDto> addProjeDeneyimi(@PathVariable String tcKimlikNo,
                                                    @RequestBody List<ProjeDeneyimleri> projeDeneyimleri) {
        UserDto response = projeDeneyimleriService.save(tcKimlikNo, projeDeneyimleri);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{tcKimlikNo}/stajBilgileri")
    public ResponseEntity<UserDto> addStajBilgisi(@PathVariable String tcKimlikNo,
                                                  @RequestBody List<StajBilgileri> stajBilgileri) {
        UserDto response = stajBilgileriService.save(tcKimlikNo, stajBilgileri);
        return ResponseEntity.ok(response);
    }

    @PostMapping("api/kisi")
    public ResponseEntity<UserDto> ekle(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }


    @PostMapping("/{tcKimlikNo}/adresler")
    public ResponseEntity<UserDto> addAdresToKisi(
            @PathVariable String tcKimlikNo,
            @RequestBody List<String> yeniAdresler) {
        UserDto response = i̇lgiAlanlarıService.save(tcKimlikNo, yeniAdresler);
        return ResponseEntity.ok(response);
    }

    @GetMapping("api/listele")
    public ResponseEntity<List<UserDto>> tumunuListele() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("api/getData")
    public ResponseEntity<List<InfoDto>> gerekliOlanlarıListele() {
        return ResponseEntity.ok(userService.getAllNecessary());
    }


    @DeleteMapping("/person/{tcKimlikNumarasi}")
    public String deletePersonByTcKimlikNumarasi(@PathVariable String tcKimlikNumarasi) {
        return userService.deletePersonByTcKimlikNumarasi(tcKimlikNumarasi);
    }

    @GetMapping("api/generateRandomString")
    public String generateRandomString() {
        String characters = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        generatedAuthenticationCode = sb.toString();
        return generatedAuthenticationCode;
    }

    @PostMapping("/forgotPassword")
    public String getUserByTcKimlikNo(@RequestBody UserDto tcKimlikNo) {
        User user = userService.findByTcKimlikNo(tcKimlikNo.getTcKimlikNo());

        if (user != null && tcKimlikNo.getEPosta().equals(user.getEPosta())) {
            return "Şifrenizi değiştirebilirsiniz";
        } else {
            return "Lütfen girdiğiniz bilgileri kontrol edin !!!";
        }
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto loginRequest) {
        User user = userService.findByTcKimlikNo(loginRequest.getTcKimlikNo());
        String hashedPassword = passwordHasher.hashPassword(loginRequest.getTcKimlikNo() + loginRequest.getPassword());
        if (user != null && user.getPassword().equals(hashedPassword) && generatedAuthenticationCode.equals(loginRequest.getAuthentication())) {
            return "Giriş başarılı!";
        } else if (!generatedAuthenticationCode.equals(loginRequest.getAuthentication())) {
            return "Doğrulama kodunu lütfen doğru giriniz !!!";
        } else {
            return "Giriş başarısız! Lütfen bilgilerinizi kontrol edin.";
        }
    }


    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePasswordByTcKimlikNo(@RequestBody UserDto updatePasswordDto) {
        String tcKimlikNo = updatePasswordDto.getTcKimlikNo();
        String newPassword = updatePasswordDto.getPassword();

        boolean updated = userService.updatePasswordByTcKimlikNo(tcKimlikNo, newPassword);

        if (updated) {
            return ResponseEntity.ok("Şifre başarıyla güncellendi !!!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lütfen bilgilerinizi kontrol ediniz");
        }
    }


    @PostMapping("/user/detailed/{tcKimlikNo}")
    public ResponseEntity<String> saveDetailedUserInfo(
            @RequestBody DetailedUser detailedUser,
            @PathVariable String tcKimlikNo) {

        User user = userService.findByTcKimlikNo(tcKimlikNo);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        DetailedUser existingDetailedUser = detailedUserService.getByUserTcKimlikNo(tcKimlikNo);
        if (existingDetailedUser != null) {
            return ResponseEntity.badRequest().body("Bu TC kimlik numarası ile zaten kullanıcı bilgileri kaydedilmiş.");
        }


        detailedUser.setKimlikNo(tcKimlikNo);
        DetailedUser savedDetailedUser = detailedUserService.saveDetailedUser(detailedUser);
        if (savedDetailedUser != null) {
            return ResponseEntity.ok("Detaylı kullanıcı bilgileri kaydedildi.");
        } else {
            return ResponseEntity.badRequest().body("Detaylı kullanıcı bilgileri kaydedilemedi.");
        }
    }

}
