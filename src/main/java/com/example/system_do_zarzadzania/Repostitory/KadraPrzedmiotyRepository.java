package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.Kadra;
import com.example.system_do_zarzadzania.Entity.KadraPrzedmioty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KadraPrzedmiotyRepository extends JpaRepository<KadraPrzedmioty, Long> {
    KadraPrzedmioty findByPrzedmiotTypId(int a);



    List<KadraPrzedmioty> findAllByKadra(Kadra kadra);
}