package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.KierunekStudiow;
import com.example.system_do_zarzadzania.Entity.PlanStudiow;
import com.example.system_do_zarzadzania.Entity.RokAkademicki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlanStudiowRepository extends JpaRepository<PlanStudiow, Long> {
    List<PlanStudiow> findByRok_Rok(String rok);
    @Query("SELECT p FROM PlanStudiow p WHERE p.rok.rok = :rok AND p.kierunekStudiow = :kierunekStudiow")
    PlanStudiow findOneByRokAndKierunekStudiow(String rok, KierunekStudiow kierunekStudiow);


}

