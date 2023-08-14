package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @Column(unique = true, length = 11,nullable = false)
    private String tcKimlikNo;
    private String ePosta;
    private String ad;
    private String soyad;
    private Date   birthDate;
    private String password;
    private String nationality;

    @Transient
    private String authentication;
}
