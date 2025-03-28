package com.example.system_do_zarzadzania.Contollers;

import com.example.system_do_zarzadzania.DTO.PrzedmiotDTO;
import com.example.system_do_zarzadzania.Entity.Kadra;
import com.example.system_do_zarzadzania.Entity.Przedmiot;
import com.example.system_do_zarzadzania.Entity.PrzedmiotTyp;
import com.example.system_do_zarzadzania.Entity.Typ;
import com.example.system_do_zarzadzania.Services.PrzedmiotyService;
import com.example.system_do_zarzadzania.Services.PrzedmiotyTypService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PrzedmiotController {


        @Autowired
        PrzedmiotyService przedmiotyService;

        @Autowired
        PrzedmiotyTypService przedmiotyTypService;

    @GetMapping("/przedmioty")
    public String przedmiotyLista(Model model) {
        List<Przedmiot> przedmioty = przedmiotyService.findAll();
        List<PrzedmiotTyp> przedmiotTypList = przedmiotyTypService.findAll();
        List<PrzedmiotDTO> przedmiotyDTO = new ArrayList<>();

        for (Przedmiot przedmiot : przedmioty) {
            List<PrzedmiotTyp> typyPrzedmiotu = przedmiotyTypService.findByprzedmiot(przedmiot);
            for (PrzedmiotTyp typ : typyPrzedmiotu) {
                PrzedmiotDTO dto = new PrzedmiotDTO(przedmiot, typ);
                przedmiotyDTO.add(dto);
            }
        }
        przedmiotyDTO.sort(Comparator.comparing(dto -> dto.getPrzedmiot().getNazwa()));
        przedmioty.sort(Comparator.comparing((dto -> dto.getNazwa())));
        List<Typ> typy = Arrays.asList(Typ.values());
        model.addAttribute("typy", typy);
        model.addAttribute("przedmiotylista", przedmioty);
        model.addAttribute("nowyprzedmiot", new Przedmiot());
        model.addAttribute("przedmiotyDTO", przedmiotyDTO);
        model.addAttribute("przedmiottyplist", przedmiotTypList);

        return "przedmioty";
    }


    @PostMapping("/przypiszPrzedmiotTyp")
    public String przypiszPrzedmiot(@RequestParam("nazwa") String przedmiot,
                                    @RequestParam("typ") Typ typ,
                                    @RequestParam("liczbaGodzin") int liczbaGodzin) {
        PrzedmiotTyp przedmiotTyp=new PrzedmiotTyp();
        przedmiotTyp.setLiczbaGodzin(liczbaGodzin);
        Przedmiot przedmiot1=przedmiotyService.findByNazwa(przedmiot);

        przedmiotTyp.setPrzedmiot(przedmiot1);
        przedmiotTyp.setTyp(typ);
        System.out.println(przedmiot1.getNazwa() + " "+ przedmiotTyp.getTyp());
      przedmiotyTypService.save(przedmiotTyp);
        return "redirect:/przedmioty";
    }




    @PostMapping("/addSubject")
    public String dodajprzedmiot(@RequestParam("nazwa") String nazwa) {
        Przedmiot przedmiot=new Przedmiot();
        przedmiot.setNazwa(nazwa);
        System.out.println("Przedmiot został dodany " + przedmiot.getNazwa());
        przedmiotyService.addPrzedmiot(przedmiot);
        return "redirect:/przedmioty";
    }


    @PostMapping("/deleteSubject")
        public String usunprzedmiot(@ModelAttribute("przedmiotylista") Przedmiot przedmiot){
            przedmiotyTypService.deletePrzedmiot(przedmiot.getId());
            return "redirect:/przedmioty";
        }
    @PostMapping("/deleteMainSubject")
    public String usunglownyprzedmiot(@ModelAttribute("przedmiotylista") Przedmiot przedmiot){
        przedmiotyService.deletePrzedmiot(przedmiot.getId());
        return "redirect:/przedmioty";
    }
}




