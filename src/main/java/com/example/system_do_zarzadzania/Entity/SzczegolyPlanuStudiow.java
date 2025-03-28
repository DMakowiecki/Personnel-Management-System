package com.example.system_do_zarzadzania.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "szczegoly_planu_studiow")
public class SzczegolyPlanuStudiow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_szczegolu")
    private int idSzczegolu;

    @Column(name = "id_planu_studiow")
    private Integer idPlanuStudiow;

    @Column(name = "numer_semestru")
    private Integer numerSemestru;
    @Column(name = "rok_akademicki")
    private String rok_akademicki;

    @ManyToOne
    @JoinColumn(name = "id_przedmiotu_typ")
    private PrzedmiotTyp idPrzedmiotuTyp;


}
