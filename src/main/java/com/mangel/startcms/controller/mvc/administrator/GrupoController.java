package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Grupo;
import com.mangel.startcms.data.repository.GrupoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/grupo")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;
    @GetMapping
    public ModelAndView Comentario(HttpServletRequest request, Model model,
                                    @RequestParam(defaultValue = "all", required = false) String view_name,
                                    @RequestParam(defaultValue = "0", required = false) int id,
                                    SpringDataWebProperties.Pageable pageable
    ){
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("view_name", view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/grupo");
        switch (view_name){
            case "all":
                modelAndView.addObject("grupos",grupoRepository.findAll(pageable));
                break;
            case "new":
                modelAndView.addObject("grupo", new Grupo());
                System.out.println("view_name = " + view_name);
                break;
            case "update":
                modelAndView.addObject("grupo", grupoRepository.findById(id));
                break;
        }
        return modelAndView;
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Grupo grupo){
        if (grupo.getIdGrupo() > 0) {
            grupoRepository.update(grupo);
        }else {
            grupoRepository.save(grupo);
        }
        return "redirect:/admin/grupo";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam int id){
        grupoRepository.deleteById(id);
        return "redirect:/admin/grupo";
    }
}
