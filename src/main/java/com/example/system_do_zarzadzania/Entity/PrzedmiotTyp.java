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
@Table(name = "przedmioty_typ")
public class PrzedmiotTyp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @Enumerated(EnumType.STRING)
    private Typ typ;

    @Column(name = "liczba_godzin")
    private int liczbaGodzin;


}
