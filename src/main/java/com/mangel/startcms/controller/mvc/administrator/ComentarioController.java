package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Comentario;
import com.mangel.startcms.data.repository.ComentarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/comentario")
public class ComentarioController {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @GetMapping
    public ModelAndView Comentario(HttpServletRequest request, Model model,
                                   @RequestParam(defaultValue = "all", required = false) String view_name,
                                   @RequestParam(defaultValue = "0", required = false) int id,
                                   SpringDataWebProperties.Pageable pageable
    ) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("view_name", view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/comentario");
        switch (view_name){
            case "all":
                modelAndView.addObject("comentarios", comentarioRepository.findAll(pageable));
                break;
            case "new":
                modelAndView.addObject("comentario", new Comentario());
                System.out.println("view_name: " + view_name);
                break;
            case "update":
                modelAndView.addObject("comentario",comentarioRepository.findById(id));
                break;
        }
        return modelAndView;
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Comentario comentario){
        if (comentario.getIdComentario() > 0) {
            comentarioRepository.update(comentario);
        }else {
            comentarioRepository.save(comentario);
        }
        return "redirect:/admin/comentario";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam int id){
        comentarioRepository.deleteById(id);
        return "redirect:/admin/comentario";
    }

}
