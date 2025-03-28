package com.example.system_do_zarzadzania.Services;



import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import com.example.system_do_zarzadzania.Entity.SzczegolyPlanuStudiow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.system_do_zarzadzania.Repostitory.*;

import java.util.List;

@Service
public class SzczegolyPlanuStudiowService {

    @Autowired
    SzczegolyPlanuStudiowRepository szczegolyPlanuStudiowRepository;

    public List<SzczegolyPlanuStudiow> znajdzPlanyStudiowDlaRoku(int id) {
        return szczegolyPlanuStudiowRepository.findAllByIdPlanuStudiowOrderByNumerSemestruAsc(id);
    } public List<SzczegolyPlanuStudiow> findAllByRok_akademicki(String rok) {
        return szczegolyPlanuStudiowRepository.findAllByRok_akademicki(rok);
    }

    public void save(SzczegolyPlanuStudiow szczegolyPlanu) {
        szczegolyPlanuStudiowRepository.save(szczegolyPlanu);
    }
}


