package com.mangel.startcms.controller.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//ruta padre: localhost:8080/admin
@Controller
@RequestMapping("/admin")
public class AdministratorController {

    //sub ruta: localhost:8080/admin/
    @GetMapping("/")
    public ModelAndView getHome(HttpServletRequest request, Model model){
        model.addAttribute("currentUri",request.getRequestURI());
        return new ModelAndView("administrator/admin");
    }
}
