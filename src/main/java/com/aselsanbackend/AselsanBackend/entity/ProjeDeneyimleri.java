package com.aselsanbackend.AselsanBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proje_deneyimleri")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class ProjeDeneyimleri {
    @Id
    @SequenceGenerator(name = "seq_proje_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_proje_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String projeAdi;
    private String projeDeadlines;
    private String projeKurum;
    private String projeDetay;

    @ManyToOne
    @JoinColumn(name = "tc_kimlik_no")
    private User user;
}
