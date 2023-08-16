package com.aselsanbackend.AselsanBackend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "ilgi_alanları")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString
public class İlgiAlanları implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_ilgi_alanları", allocationSize = 1)
    @GeneratedValue(generator = "seq_ilgi_alanları", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="ilgi_alanı")
    private String ilgiAlanı;


    @ManyToOne
    @JoinColumn(name = "user_tc_kimlik_no")
    private User user;
}
