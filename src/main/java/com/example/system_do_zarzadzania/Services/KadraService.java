package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.Kadra;
import com.example.system_do_zarzadzania.Repostitory.KadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KadraService {
    @Autowired
    private KadraRepository kadraRepository;

    public  List<Kadra> findAll() {
        return kadraRepository.findAll();
    }
    public Kadra addLecturer(Kadra lecturer){
        return kadraRepository.save(lecturer);
    }
    public Optional<Kadra> findByid(Long id){
        return kadraRepository.findById(id);
    }
    public void deleteLecturer(long id){
        kadraRepository.deleteById(id);
    }

    public void updateLecturer(Kadra kadra){

        kadraRepository.save(kadra);
    }

    public Kadra findByImieAndNazwisko(String imieProwadzacego, String nazwiskoProwadzacego) {
        return kadraRepository.findByImieAndNazwisko(imieProwadzacego,nazwiskoProwadzacego);
    }

    public Optional<Kadra> findById(long l) {
        return  kadraRepository.findById(l);
    }
}
