package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import com.example.system_do_zarzadzania.Repostitory.PrzedmiotyTypRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrzedmiotyTypService {
    @Autowired
    private PrzedmiotyTypRepository przedmiotyTypRepository;

    public List<PrzedmiotTyp> findAll() {
        return przedmiotyTypRepository.findAll();
    }
    public PrzedmiotTyp addPrzedmiotTyp(PrzedmiotTyp typ){
        return przedmiotyTypRepository.save(typ);
    }



    public PrzedmiotTyp znajdzPrzedmiotyTyp(Long przedmiotTypId) {
        return przedmiotyTypRepository.findAllById(przedmiotTypId);
    }


    public PrzedmiotTyp findPrzedmiotTypByPrzedmiot(Przedmiot przedmiot) {
        Optional<PrzedmiotTyp> optionalPrzedmiotTyp = przedmiotyTypRepository.findPrzedmiotTypById(przedmiot.getId());
        return optionalPrzedmiotTyp.orElse(null);
    }


    public List<PrzedmiotTyp> findByprzedmiot(Przedmiot przedmiot){
        return przedmiotyTypRepository.findByPrzedmiot(przedmiot);
    }

    public void save(PrzedmiotTyp przedmiotTyp) {
        przedmiotyTypRepository.save(przedmiotTyp);
    }

    public PrzedmiotTyp findById(Long przedmiotTypId) {
        return findById(przedmiotTypId);
    }
    public PrzedmiotTyp findByPrzedmiotAndAndTyp(Przedmiot przedmiot, String typ) {
        return przedmiotyTypRepository.findByPrzedmiotAndAndTyp(przedmiot,typ);
    }

    public Object pobierzWszystkieTypyPrzedmiotow() {
        return  przedmiotyTypRepository.findAll();
    }

    public void deletePrzedmiot(long    id) {
        przedmiotyTypRepository.deleteById(id);
    }
}
