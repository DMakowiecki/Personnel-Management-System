package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.Kadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KadraRepository extends JpaRepository<Kadra, Long> {



    Kadra findByImieAndNazwisko(String imieProwadzacego, String nazwiskoProwadzacego);
}