package com.mangel.startcms.controller.mvc.administrator;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/permiso")
public class PermisoController {
    @GetMapping
    public ModelAndView Permiso(HttpServletRequest request, Model model){
        model.addAttribute("currentUri", request.getRequestURI());
        return new ModelAndView("administrator/permiso");
    }
}
