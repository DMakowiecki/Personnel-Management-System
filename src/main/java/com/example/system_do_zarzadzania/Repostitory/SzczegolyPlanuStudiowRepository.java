package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import com.example.system_do_zarzadzania.Entity.SzczegolyPlanuStudiow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SzczegolyPlanuStudiowRepository extends JpaRepository<SzczegolyPlanuStudiow, Long> {
    List<SzczegolyPlanuStudiow> findAllByIdPlanuStudiowOrderByNumerSemestruAsc(int id);
    @Query("SELECT s FROM SzczegolyPlanuStudiow s WHERE s.rok_akademicki = :rok_akademicki")
    List<SzczegolyPlanuStudiow> findAllByRok_akademicki(String rok_akademicki);


}