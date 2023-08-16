package com.aselsanbackend.AselsanBackend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kariyer_hedeflerim")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class KariyerHedeflerim {

    @Id
    @SequenceGenerator(name = "seq_kariyer_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_kariyer_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String kariyerHedefim;

    private String tcKimlikNo;
}
