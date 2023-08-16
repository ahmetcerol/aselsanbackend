package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "eğitim_bilgilerim")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EğitimBilgilerim {

    @Id
    @SequenceGenerator(name = "seq_egitim_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_egitim_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String okulAdi;
    private Date startDate;
    private Date finishDate;

    @ManyToOne
    @JoinColumn(name = "tc_kimlik_no")
    private User user;
}
