package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Usuario;
import com.mangel.startcms.data.repository.GrupoRepository;
import com.mangel.startcms.data.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

 @Controller
 @RequestMapping("/admin/usuario")
public class UsuarioController {
     @Autowired
     private UsuarioRepository usuarioRepository;
     @Autowired
     private GrupoRepository grupoRepository;

    @GetMapping
    public ModelAndView getHome(HttpServletRequest request, Model model,
                                @RequestParam(defaultValue = "all", required = false) String view_name,
                                @RequestParam(defaultValue = "0", required = false) int id,
                                SpringDataWebProperties.Pageable pageable
    ){
        model.addAttribute("currentUri",request.getRequestURI());
        model.addAttribute("view_name",view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/usuario");
        switch (view_name) {
            case "all":
                modelAndView.addObject("usuarios", usuarioRepository.findAll(pageable));
                break;
            case "new":
                modelAndView.addObject("usuario", new Usuario());
                modelAndView.addObject("grupos", grupoRepository.findAll(pageable));
                modelAndView.addObject("update", false);
                System.out.println("view_name = "+ view_name);
                break;
            case "update":
                modelAndView.addObject("usuario", usuarioRepository.findById(id));
                modelAndView.addObject("grupos", grupoRepository.findAll(pageable));
                modelAndView.addObject("update", true);
                break;
        }
        return modelAndView;
    }

    @PostMapping
     public String newAndUpdate(@ModelAttribute Usuario usuario){
        if (usuario.getIdUsuario() > 0){
            usuarioRepository.update(usuario);
        }else{
            usuarioRepository.save(usuario);
        }
        return "redirect:/admin/usuario";
    }

    @PostMapping("/delete")
     public String deleteById(@RequestParam int id){
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuario";
    }

}
