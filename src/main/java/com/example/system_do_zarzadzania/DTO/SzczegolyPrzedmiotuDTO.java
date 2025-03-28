package com.example.system_do_zarzadzania.DTO;

import com.example.system_do_zarzadzania.Entity.Kadra;
import com.example.system_do_zarzadzania.Entity.KadraPrzedmioty;
import com.example.system_do_zarzadzania.Entity.SzczegolyPlanuStudiow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SzczegolyPrzedmiotuDTO {
    private SzczegolyPlanuStudiow szczegolyPlanuStudiow;
    private KadraPrzedmioty kadraPrzedmioty;

    private Kadra kadra;
}