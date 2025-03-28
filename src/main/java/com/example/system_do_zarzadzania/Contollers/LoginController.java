package com.example.system_do_zarzadzania.Contollers;

import com.example.system_do_zarzadzania.Repostitory.UsersRepository;
import com.example.system_do_zarzadzania.Services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private HttpSession httpSession;
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    public LoginController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){

        return "login.html";
    }
    @GetMapping("/showMyLoginPage?error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "redirect:/login";
    }
    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, Model model) {

        httpSession.setAttribute("username", "exampleUser");
        return "redirect:/home";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam("registerUsername") String username,
                               @RequestParam("registerPassword") String password,
                               @RequestParam("registerRepeatPassword") String repeatPassword,
                               Model model) {
        usersService.saveUser(username,password);
        return "redirect:/showMyLoginPage";
    }
    @GetMapping("/logout")
    public String logoutUser() {
        httpSession.invalidate();
        return "redirect:/showMyLoginPage";
    }
}
