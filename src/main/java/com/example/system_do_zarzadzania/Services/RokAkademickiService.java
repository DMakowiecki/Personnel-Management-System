package com.example.system_do_zarzadzania.Services;


import com.example.system_do_zarzadzania.Entity.RokAkademicki;
import com.example.system_do_zarzadzania.Repostitory.RokAkademickiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RokAkademickiService {
    @Autowired
    RokAkademickiRepository rokAkademickiRepository;

    public List<RokAkademicki> findAll() {
        return rokAkademickiRepository.findAll();
    }
   public RokAkademicki znajdzRokAkademickiByRok(String a) {
        return rokAkademickiRepository.findByRok(a);
    }


    public void addRokAkademicki(String nazwaRoku) {
        RokAkademicki nowyRokAkademicki = new RokAkademicki();
        nowyRokAkademicki.setRok(nazwaRoku);
        rokAkademickiRepository.save(nowyRokAkademicki);
    }

    public void usunRokAkademicki(int a) {

        rokAkademickiRepository.deleteById((long) a);
        System.out.println("Usunięto");
    }

    public RokAkademicki findById(int id) {
        return rokAkademickiRepository.findById(id);
    }
}
