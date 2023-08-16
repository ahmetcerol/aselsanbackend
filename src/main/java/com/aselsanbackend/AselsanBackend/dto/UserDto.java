package com.aselsanbackend.AselsanBackend.dto;

import com.aselsanbackend.AselsanBackend.entity.*;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    private List<String> ilgiAlanları;
    private List<StajBilgileri> stajBilgileriList;
    private List<ProjeDeneyimleri> projeDeneyimleriList;
    private List<EğitimBilgilerim> eğitimBilgilerimList;


}
