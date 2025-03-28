package com.example.system_do_zarzadzania.Contollers;

import com.example.system_do_zarzadzania.DTO.PrzedmiotDTO;
import com.example.system_do_zarzadzania.Entity.*;
import com.example.system_do_zarzadzania.Services.*;
import com.example.system_do_zarzadzania.DTO.SzczegolyPrzedmiotuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class MainController {
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

    @GetMapping("/home")
    public String rokstudiow(Model model) {
        List<RokAkademicki> listaRokowAkademickich = rokAkademickiService.findAll();
        model.addAttribute("listaRokowAkademickich", listaRokowAkademickich);
        model.addAttribute("nowyRok", new RokAkademicki());

        return "home";
    }

    @PostMapping("/addRokAkademicki")
    public String dodajRokAkademicki(@ModelAttribute("nowyRok") RokAkademicki nowyRok) {
        System.out.println(nowyRok.getRok());
        rokAkademickiService.addRokAkademicki(nowyRok.getRok());
        return "redirect:/home";
    }

    @GetMapping("/plany-studiow/{rok1}/{rok2}")
    public String znajdzPlanyStudiowDlaOkresu(@PathVariable String rok1, @PathVariable String rok2, Model model) {
        String okres = rok1 + "/" + rok2;
        List<PlanStudiow> planyStudiow = planStudiowService.znajdzPlanyStudiowDlaRoku(okres);
        RokAkademicki rok = rokAkademickiService.znajdzRokAkademickiByRok(okres);
        List<KierunekStudiow> kierunkiStudiow = kierunekStudiowService.findAll();
        model.addAttribute("kierunkiStudiow", kierunkiStudiow);
        model.addAttribute("nowykierunek", new KierunekStudiow());
        model.addAttribute("planyStudiow", planyStudiow);
        model.addAttribute("rok", rok);
        return "plany-studiow";
    }

    @GetMapping("/detale-kierunku/{id}")
    public String wyswietlDetaleKierunku(@PathVariable int id, Model model) {
        try {
            RokAkademicki rokAkademicki = rokAkademickiService.findById(id);
            if (rokAkademicki == null) {
                return "redirect:/lista-kierunkow-studiow";
            }


            List<Przedmiot> wszystkiePrzedmioty = przedmiotyService.findAll(); // Zakładam, że masz odpowiednią usługę do pobierania wszystkich przedmiotów



            List<SzczegolyPlanuStudiow> szczegoly = szczegolyPlanuStudiowService.znajdzPlanyStudiowDlaRoku(id);

            Map<Integer, List<SzczegolyPlanuStudiow>> przedmiotyWedlugSemestru = new HashMap<>();

            for (SzczegolyPlanuStudiow szczegol : szczegoly) {
                int numerSemestru = szczegol.getNumerSemestru();
                przedmiotyWedlugSemestru.computeIfAbsent(numerSemestru, k -> new ArrayList<>()).add(szczegol);
            }

            model.addAttribute("rok", rokAkademicki);
            model.addAttribute("przedmiotyWedlugSemestru", przedmiotyWedlugSemestru);
            model.addAttribute("wszystkiePrzedmioty", wszystkiePrzedmioty);
            model.addAttribute("listaProwadzacych", kadraService.findAll());
            model.addAttribute("przedmiotyTyp", przedmiotyTypService.findAll());
            model.addAttribute("kadraPrzedmioty", kadraPrzedmiotyService.findAll()); // Dodajemy listę KadraPrzedmioty
            model.addAttribute("typyPrzedmiotow", przedmiotyTypService.pobierzWszystkieTypyPrzedmiotow());
            return "detale-kierunku";
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:/lista-kierunkow-studiow";
        }
    }


    @PostMapping("/deleteRokAkademicki")
    public String usunRokAkademicki(@ModelAttribute("rok") RokAkademicki rok, RedirectAttributes redirectAttributes) {
        try {
            System.out.println(rok.getId());
            rokAkademickiService.usunRokAkademicki(rok.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Rok akademicki został pomyślnie usunięty.");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Wystąpił błąd podczas usuwania roku akademickiego.");
            return "redirect:/home";
        }
    }

    @PostMapping("/addMajorToYear")
    public String dodajKierunekDoRoku(@RequestParam("nowyKierunekId") int kierunekId,
                                      @RequestParam("rokId") int rokId,
                                      RedirectAttributes redirectAttributes) {
        Optional<KierunekStudiow> optionalKierunek = kierunekStudiowService.findById(kierunekId);
        if (optionalKierunek.isPresent()) {
            KierunekStudiow kierunek = optionalKierunek.get();
            RokAkademicki rok = rokAkademickiService.findById(rokId);
            PlanStudiow planStudiow = new PlanStudiow();
            planStudiow.setKierunekStudiow(kierunek);
            planStudiow.setRok(rok);
            planStudiowService.dodajKierunekStudiow(planStudiow);
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Kierunek studiów o podanym identyfikatorze nie istnieje.");
            return "redirect:/some-error-page";
        }
    }

    @PostMapping("/przypiszPrzedmiot")
    public String przypiszPrzedmiot(@ModelAttribute("szczegolyPrzedmiotuDTO") SzczegolyPrzedmiotuDTO szczegolyPrzedmiotuDTO,
                                    @RequestParam("szczegolyPlanuStudiow.rokAkademicki") String rokAkademickii,
                                    @RequestParam("rok") int rok,
                                    @RequestParam("kadraPrzedmioty.kadra.id") int kadraId,
                                    @RequestParam("przedmiotId") String przedmiotIdTypId) {

        String[] idArray = przedmiotIdTypId.split(":");
        Long przedmiotId = Long.parseLong(idArray[0]);
        Long typId = Long.parseLong(idArray[1]);
        Przedmiot przedmiot = przedmiotyService.findById(przedmiotId);
        System.out.println(przedmiot.getNazwa());

        PrzedmiotTyp przedmiotTyp = przedmiotyTypService.znajdzPrzedmiotyTyp(typId);
        System.out.println(przedmiotTyp.getTyp());

        int numerSemestru = szczegolyPrzedmiotuDTO.getSzczegolyPlanuStudiow().getNumerSemestru();
        String rokAkademicki = rokAkademickii;

        int rokId = rok;

        if (przedmiotTyp != null) {
            Optional<Kadra> optionalKadra = kadraService.findById((long) kadraId);
            if (optionalKadra.isPresent()) {
                Kadra kadraa = optionalKadra.get();
                SzczegolyPlanuStudiow szczegolyPlanuStudiow = new SzczegolyPlanuStudiow();
                szczegolyPlanuStudiow.setIdPlanuStudiow(rokId);

                szczegolyPlanuStudiow.setNumerSemestru(numerSemestru);
                szczegolyPlanuStudiow.setIdPrzedmiotuTyp(przedmiotTyp);
                szczegolyPlanuStudiow.setRok_akademicki(rokAkademicki);

                szczegolyPlanuStudiowService.save(szczegolyPlanuStudiow);

                KadraPrzedmioty kadraPrzedmioty = new KadraPrzedmioty();
                kadraPrzedmioty.setKadra(kadraa);
                kadraPrzedmioty.setPrzedmiotTyp(przedmiotTyp);
                kadraPrzedmiotyService.save(kadraPrzedmioty);

                System.out.println("Zapisano do bazy danych: " + szczegolyPlanuStudiow.getIdPlanuStudiow() + " " + szczegolyPlanuStudiow.getRok_akademicki() + " " + szczegolyPlanuStudiow.getIdSzczegolu() + " " + szczegolyPlanuStudiow.getNumerSemestru() + " " + szczegolyPlanuStudiow.getIdPrzedmiotuTyp());
            } else {
                System.out.println("Nie znaleziono karty o podanym ID");
            }
        } else {
            System.out.println("Nie znaleziono przedmiotu typ o podanym ID");
        }
        return "redirect:/home";
    }
}














