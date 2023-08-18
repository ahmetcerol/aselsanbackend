package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "detailed_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class DetailedUser implements Serializable {

    @Id
    @SequenceGenerator(name ="seq_detailedUser", allocationSize = 1)
    @GeneratedValue(generator= "seq_detailedUser", strategy = GenerationType.SEQUENCE)
    private long id;
    private String gender;

    @Column(insertable = true, updatable = false)
    private String kimlikNo;

    @Column(length = 11)
    private String telephoneNumber;
    private boolean Aktif;

    @OneToOne
    @JoinColumn(name ="tcKimlikNo")
    private User user;

}
