package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.Kadra;
import com.example.system_do_zarzadzania.Entity.KadraPrzedmioty;
import com.example.system_do_zarzadzania.Repostitory.KadraPrzedmiotyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KadraPrzedmiotyService {
    @Autowired
    private KadraPrzedmiotyRepository kadraPrzedmiotyRepository;

    public List<KadraPrzedmioty> findAll() {
        return kadraPrzedmiotyRepository.findAll();
    }
    public KadraPrzedmioty addKadraPrzedmioty(KadraPrzedmioty kadraPrzedmioty){
       return kadraPrzedmiotyRepository.save(kadraPrzedmioty);

    }
    public KadraPrzedmioty znajdzKadrePrzedmiotu(int przedmiotTypId) {
        return kadraPrzedmiotyRepository.findByPrzedmiotTypId(przedmiotTypId);
    }
    public void dodajKadrePrzedmioty(KadraPrzedmioty kadraPrzedmioty) {
        kadraPrzedmiotyRepository.save(kadraPrzedmioty);
    }

    public void save(KadraPrzedmioty kadraPrzedmioty) {
        kadraPrzedmiotyRepository.save(kadraPrzedmioty);
    }

    public List<KadraPrzedmioty> findByKadra(Kadra kadra) {
        return kadraPrzedmiotyRepository.findAllByKadra(kadra);
    }
}
