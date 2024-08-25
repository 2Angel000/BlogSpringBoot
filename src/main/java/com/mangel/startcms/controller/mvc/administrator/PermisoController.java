package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Permiso;
import com.mangel.startcms.data.repository.PermisoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/permiso")
public class PermisoController {
    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping
    public ModelAndView Permiso(HttpServletRequest request, Model model,
                                @RequestParam(defaultValue = "all", required = false) String view_name,
                                @RequestParam(defaultValue = "0", required = false) int id,
                                SpringDataWebProperties.Pageable pageable
    ) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("view_name", view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/permiso");
        switch (view_name){
            case "all":
                    modelAndView.addObject("permisos", permisoRepository.findAll(pageable));
                break;
            case "new":
                    modelAndView.addObject("permiso", new Permiso());
                System.out.println("view_name = " + view_name);
                break;
            case "update":
                    modelAndView.addObject("permiso", permisoRepository.findById(id));
                break;
        }
        return modelAndView;
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Permiso permiso){
        if (permiso.getIdPermiso() > 0) {
            permisoRepository.update(permiso);
        }else{
            permisoRepository.save(permiso);
        }
        return "redirect:/admin/permiso";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam int id){
        permisoRepository.deleteById(id);
        return "redirect:/admin/permiso";
    }
}
