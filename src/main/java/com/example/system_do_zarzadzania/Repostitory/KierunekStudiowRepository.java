package com.example.system_do_zarzadzania.Repostitory;


import com.example.system_do_zarzadzania.Entity.KierunekStudiow;
import com.example.system_do_zarzadzania.Entity.PlanStudiow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KierunekStudiowRepository extends JpaRepository<KierunekStudiow, Long> {

}
