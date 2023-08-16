package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staj_bilgileri")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString
public class StajBilgileri {
    @Id
    @SequenceGenerator(name = "seq_staj_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_staj_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String stajYeri;
    private String stajBölümü;
    private String stajSüresi;
    private int stajYili;
    private String stajTürü;

    @ManyToOne
    @JoinColumn(name = "tc_kimlik_no")
    private User user;
}
