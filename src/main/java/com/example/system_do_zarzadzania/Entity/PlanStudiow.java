package com.example.system_do_zarzadzania.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plan_studiow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanStudiow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planu_studiow")
    private int idPlanuStudiow;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_roku")
    private RokAkademicki rok;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_kierunku")
    private KierunekStudiow kierunekStudiow;

}