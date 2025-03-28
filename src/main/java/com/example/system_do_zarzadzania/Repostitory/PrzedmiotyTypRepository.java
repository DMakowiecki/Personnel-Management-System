package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrzedmiotyTypRepository extends JpaRepository<PrzedmiotTyp, Long> {
    public List<PrzedmiotTyp> findByPrzedmiot(Przedmiot przedmiot);


    Optional<PrzedmiotTyp> findPrzedmiotTypById(int id);


    PrzedmiotTyp findAllById(Long przedmiotTypId);
    PrzedmiotTyp findByPrzedmiotAndAndTyp(Przedmiot przedmiot, String typ);
}
