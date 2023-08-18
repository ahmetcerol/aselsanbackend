package com.aselsanbackend.AselsanBackend.dto;

import com.aselsanbackend.AselsanBackend.entity.EğitimBilgilerim;
import com.aselsanbackend.AselsanBackend.entity.ProjeDeneyimleri;
import com.aselsanbackend.AselsanBackend.entity.StajBilgileri;
import lombok.Data;

import java.util.List;

@Data
public class InfoDto {

    private String ePosta;
    private String ad;
    private String soyad;

    private List<String> ilgiAlanları;
    private List<StajBilgileri> stajBilgileriList;
    private List<ProjeDeneyimleri> projeDeneyimleriList;
    private List<EğitimBilgilerim> eğitimBilgilerimList;
}
