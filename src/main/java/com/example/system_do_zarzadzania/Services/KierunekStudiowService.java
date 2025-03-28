package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.KierunekStudiow;
import com.example.system_do_zarzadzania.Repostitory.KierunekStudiowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KierunekStudiowService {

    @Autowired
    private KierunekStudiowRepository kierunekStudiowRepository;
    public List<KierunekStudiow> findAll(){
        return kierunekStudiowRepository.findAll();
    }
    public Optional<KierunekStudiow> findById(long id){
        return kierunekStudiowRepository.findById(id);
    }



}
