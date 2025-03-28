package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.Przedmiot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PrzedmiotyRepository extends JpaRepository<Przedmiot, Long> {
    Przedmiot findPrzedmiotById(Long a);

    Przedmiot findPrzedmiotByNazwa(String nazwa);
}
