package com.example.system_do_zarzadzania.Contollers;

import com.example.system_do_zarzadzania.Entity.*;
import com.example.system_do_zarzadzania.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class KadraController {
    @Autowired
    KadraService kadraService;
    @Autowired
    PrzedmiotyService przedmiotyService;
    @Autowired
    PrzedmiotyTypService przedmiotyTypService;
    @Autowired
    KadraPrzedmiotyService kadraPrzedmiotyService;
    @Autowired
    KierunekStudiowService kierunekStudiowService;
    @Autowired
    PlanStudiowService planStudiowService;
    @Autowired
    RokAkademickiService rokAkademickiService;
    @Autowired
    SzczegolyPlanuStudiowService szczegolyPlanuStudiowService;

    @GetMapping("/kadra")
    public String kadra(Model model) {
        List<Kadra> listaKadry = kadraService.findAll();
        listaKadry.sort(Comparator.comparing(Kadra::getNazwisko));
        model.addAttribute("listaKadry", listaKadry);
        model.addAttribute("NowaKadra", new Kadra());
        return "kadra";
    }


    @PostMapping("/szukaj")
    public String szukaj(@RequestParam("kadra") Kadra kadra,
                         @RequestParam("rokAkademicki") String rok,
                         Model model) {

        List<KadraPrzedmioty> kadraPrzedmioty = kadraPrzedmiotyService.findByKadra(kadra);
        List<SzczegolyPlanuStudiow> szczegoly = szczegolyPlanuStudiowService.findAllByRok_akademicki(rok);
        List<PrzedmiotTyp> zgadzajacePrzedmioty = new ArrayList<>();
        int sumaGodzin = 0;

        for (KadraPrzedmioty kadraPrzedmiot : kadraPrzedmioty) {
            for (SzczegolyPlanuStudiow szczegol : szczegoly) {
                int idPrzedmiotuTyp = kadraPrzedmiot.getPrzedmiotTyp().getId();
                int idSzczegolu = szczegol.getIdPrzedmiotuTyp().getId();
                if (idPrzedmiotuTyp == idSzczegolu) {
                    zgadzajacePrzedmioty.add(kadraPrzedmiot.getPrzedmiotTyp());
                    sumaGodzin += kadraPrzedmiot.getPrzedmiotTyp().getLiczbaGodzin();
                    break;
                }
            }
        }
        for (PrzedmiotTyp przedmiot : zgadzajacePrzedmioty) {
            System.out.println("Znaleziono pasujący przedmiot: " + przedmiot.getPrzedmiot().getNazwa()+ " "+ przedmiot.getLiczbaGodzin());
        }
        System.out.println("Suma liczby godzin pasujących przedmiotów: " + sumaGodzin);

        model.addAttribute("zgadzajacePrzedmioty", zgadzajacePrzedmioty);
        model.addAttribute("sumaGodzin", sumaGodzin);
        List<Kadra> listaKadry = kadraService.findAll();

        model.addAttribute("listaKadry", listaKadry);
        model.addAttribute("prowadzacy", kadra);
        return "kadragodziny";
    }



    @PostMapping("/podsumowanie")
    public String szukaj(@RequestParam("rokAkademicki") String rok,
                         Model model) {

        List<Kadra> listaKadry = kadraService.findAll();
        Map<Kadra, Integer> podsumowanieGodzinProwadzacych = new TreeMap<>((kadra1, kadra2) -> {
            // Porównujemy nazwiska prowadzących alfabetycznie
            return kadra1.getNazwisko().compareTo(kadra2.getNazwisko());
        });

        for (Kadra kadra : listaKadry) {
            List<KadraPrzedmioty> kadraPrzedmioty = kadraPrzedmiotyService.findByKadra(kadra);
            List<SzczegolyPlanuStudiow> szczegoly = szczegolyPlanuStudiowService.findAllByRok_akademicki(rok);
            int sumaGodzin = 0;

            for (KadraPrzedmioty kadraPrzedmiot : kadraPrzedmioty) {
                for (SzczegolyPlanuStudiow szczegol : szczegoly) {
                    int idPrzedmiotuTyp = kadraPrzedmiot.getPrzedmiotTyp().getId();
                    int idSzczegolu = szczegol.getIdPrzedmiotuTyp().getId();
                    if (idPrzedmiotuTyp == idSzczegolu) {
                        sumaGodzin += kadraPrzedmiot.getPrzedmiotTyp().getLiczbaGodzin();
                        break;
                    }
                }
            }

            podsumowanieGodzinProwadzacych.put(kadra, sumaGodzin);
        }

        model.addAttribute("podsumowanieGodzinProwadzacych", podsumowanieGodzinProwadzacych);
        model.addAttribute("rok", rok);
        model.addAttribute("listaKadry", listaKadry);

        return "kadragodziny";
    }




    @GetMapping("kadralista")
    public  String kadralista(){
        return "kadralista";
    }
    @GetMapping("/kadragodzinyy")
    public String kadragodz(Model model) {
        List<Kadra> listaKadry = kadraService.findAll();

        model.addAttribute("listaKadry", listaKadry);
        return "kadragodziny";
    }

    @PostMapping("/addKadra")
    public String dodajkadra(@ModelAttribute("NowaKadra") Kadra NowaKadra) {
        kadraService.addLecturer(NowaKadra);
        return "redirect:/kadra";
    }
    @PostMapping("/editKadra")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Optional<Kadra> pracownik = kadraService.findByid(id);
        model.addAttribute("pracownik", pracownik);
        return "editForm";
    }
    @PostMapping("/deleteKadra")
    public String usunKadra(@ModelAttribute("pracownik") Kadra pracownik) {
        kadraService.deleteLecturer(pracownik.getId());


        return "redirect:/kadra";
    }
    @PostMapping("/saveEditedKadra")
    public String zapiszKadra(@ModelAttribute("pracownik") Kadra pracownik) {

        Optional<Kadra> optionalKadra = kadraService.findByid((long) pracownik.getId());

        if (optionalKadra.isPresent()) {

            kadraService.updateLecturer(pracownik);
        } else {

            System.out.println("Pracownik o ID " + pracownik.getId() + " nie istnieje.");

        }

        return "redirect:/kadra";
    }


}
