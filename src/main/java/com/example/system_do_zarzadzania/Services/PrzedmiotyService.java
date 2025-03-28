package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Repostitory.PrzedmiotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrzedmiotyService {
    @Autowired
    private PrzedmiotyRepository przedmiotyRepository;

    public List<Przedmiot> findAll() {
        return przedmiotyRepository.findAll();
    }
    public Przedmiot addPrzedmiot(Przedmiot przedmiot){
        return przedmiotyRepository.save(przedmiot);
    }


    public Przedmiot znajdzprzedmiotpoId(Przedmiot przedmiot) {
        return przedmiotyRepository.findPrzedmiotById((long) przedmiot.getId());
    }
    public void deletePrzedmiot(long id){
        przedmiotyRepository.deleteById(id);
    }
    public void dodajPrzedmiot(Przedmiot przedmiot) {
        przedmiotyRepository.save(przedmiot);
    }


    public void save(Przedmiot przedmiot) {
        przedmiotyRepository.save(przedmiot);
    }
    public Przedmiot findByNazwa(String nazwa){
        return przedmiotyRepository.findPrzedmiotByNazwa(nazwa);
    }

    public Przedmiot findById(Long nazwaPrzedmiotu) {
        return przedmiotyRepository.findPrzedmiotById(nazwaPrzedmiotu);
    }
}
