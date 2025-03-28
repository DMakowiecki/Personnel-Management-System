package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import com.example.system_do_zarzadzania.Entity.RokAkademicki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RokAkademickiRepository extends JpaRepository<RokAkademicki, Long> {


    void deleteById(Long id);
    RokAkademicki findById(int a);
    RokAkademicki findByRok(String a);
}
