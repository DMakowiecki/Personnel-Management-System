package com.example.system_do_zarzadzania.DTO;
import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrzedmiotDTO {
    private Przedmiot przedmiot;
    private PrzedmiotTyp przedmiotTyp;
}
