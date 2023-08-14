package com.aselsanbackend.AselsanBackend.dto;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String tcKimlikNo;
    private String password;
    private String nationality;
    private String ePosta;
    private String ad;
    private String soyad;
    private Date birthDate;

    @Transient
    private String authentication;

}
