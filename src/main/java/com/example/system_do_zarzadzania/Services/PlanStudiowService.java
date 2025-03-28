package com.example.system_do_zarzadzania.Services;

import com.example.system_do_zarzadzania.Entity.KierunekStudiow;
import com.example.system_do_zarzadzania.Entity.PlanStudiow;
import com.example.system_do_zarzadzania.Entity.RokAkademicki;
import com.example.system_do_zarzadzania.Repostitory.PlanStudiowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanStudiowService {
    @Autowired
    PlanStudiowRepository planStudiowRepository;
    @Autowired
    RokAkademickiService rokAkademickiService;

    public List<PlanStudiow> znajdzPlanyStudiowDlaRoku(String rok) {
        return planStudiowRepository.findByRok_Rok(rok);
    }

    public PlanStudiow znajdzPlanStudiowDanegoRoku(String rok,KierunekStudiow kierunekStudiow){
        return  planStudiowRepository.findOneByRokAndKierunekStudiow(rok,kierunekStudiow);
    }

    public void dodajKierunekStudiow(PlanStudiow planStudiow) {
         planStudiowRepository.save(planStudiow);
    }
}