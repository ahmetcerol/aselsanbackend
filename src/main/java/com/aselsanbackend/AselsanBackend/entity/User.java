package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @Column(unique = true, length = 11, nullable = false)
    private String tcKimlikNo;
    private String ePosta;
    private String ad;
    private String soyad;
    private Date birthDate;
    private String password;
    private String nationality;

    @Transient
    private String authentication;

    @OneToMany
    @JoinColumn(name = "user_tc_kimlik_no")
    private List<İlgiAlanları> adresleri;

    @OneToMany
    @JoinColumn(name = "tc_kimlik_no")
    private List<StajBilgileri> stajBilgileri;

    @OneToMany
    @JoinColumn(name = "tc_kimlik_no")
    private List<ProjeDeneyimleri> projeDeneyimleri;

    @OneToMany
    @JoinColumn(name = "tc_kimlik_no")
    private List<EğitimBilgilerim> eğitimBilgilerimList;



}
