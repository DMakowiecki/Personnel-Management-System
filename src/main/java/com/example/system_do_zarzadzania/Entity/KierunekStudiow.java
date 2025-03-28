package com.example.system_do_zarzadzania.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kierunki_studiow")
@Getter
@Setter
@NoArgsConstructor
public class KierunekStudiow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kierunku")
    private int idKierunku;

    @Column(name = "nazwa_kierunku")
    private String nazwaKierunku;
}
