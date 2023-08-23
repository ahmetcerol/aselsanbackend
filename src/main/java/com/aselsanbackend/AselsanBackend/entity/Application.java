package com.aselsanbackend.AselsanBackend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Application {

    @Id
    @SequenceGenerator(name = "seq_application", allocationSize = 1)
    @GeneratedValue(generator="seq_application" ,strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = true)
    private boolean isApproved;
    private String tcKimlikNo;

}
