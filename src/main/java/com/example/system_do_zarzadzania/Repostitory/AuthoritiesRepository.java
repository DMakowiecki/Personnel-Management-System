package com.example.system_do_zarzadzania.Repostitory;

import com.example.system_do_zarzadzania.Entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    Authorities save(Authorities authoritie);
}
