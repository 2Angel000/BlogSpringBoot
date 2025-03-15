package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Grupo;
import com.mangel.startcms.data.model.GrupoPermiso;
import com.mangel.startcms.data.repository.GrupoRepository;
import com.mangel.startcms.data.repository.PermisoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/grupo")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private PermisoRepository permisoRepository;
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
                modelAndView.addObject("update", true);
                modelAndView.addObject("permisos", permisoRepository.findByGrupo(id));
                modelAndView.addObject("permisos_disponibles", permisoRepository.findNotByIdGrupo(id));
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

    @PostMapping("/addPermiso")
    public String addPermiso(@RequestParam int idGrupo, @RequestParam int idPermiso){
        GrupoPermiso grupoPermiso = new GrupoPermiso();
        grupoPermiso.setIdPermiso(idPermiso);
        grupoPermiso.setIdGrupo(idGrupo);
        return "redirect:/admin/grupo?view_name=update&id="+idGrupo;

    }
}
