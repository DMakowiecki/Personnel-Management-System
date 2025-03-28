package com.example.system_do_zarzadzania.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "kadra_przedmioty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KadraPrzedmioty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_prowadzacego")
    private Kadra kadra;

    @ManyToOne
    @JoinColumn(name="id_przedmiotu")
    private PrzedmiotTyp przedmiotTyp;

}
