package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Categoria;
import com.mangel.startcms.data.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ModelAndView Categoria(HttpServletRequest request, Model model,
                                  @RequestParam(defaultValue = "all", required = false) String view_name, //qué vista me manda
                                  @RequestParam(defaultValue = "0", required = false) int id,
                                  SpringDataWebProperties.Pageable pageable
    ) {
        model.addAttribute("currentUri", request.getRequestURI());
        model.addAttribute("view_name", view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/categoria");
        switch (view_name) {
            case "all":
                modelAndView.addObject("categorias", categoriaRepository.findAll(pageable));
                break;
            case "new":
                modelAndView.addObject("categoria", new Categoria());
                System.out.println("view_name = " + view_name);
                break;
            case "update":
                //recuperar por ID
                modelAndView.addObject("categoria", categoriaRepository.findById(id));
                break;
        }
        return modelAndView;
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Categoria categoria) {
        if (categoria.getIdCategoria() > 0) {
            categoriaRepository.update(categoria);
        }else{
            categoriaRepository.save(categoria);
        }
        return "redirect:/admin/categoria";
    }

    @PostMapping("/delete")
    public String deleteById( @RequestParam int id){
        categoriaRepository.deleteById(id);
        return "redirect:/admin/categoria";
    }

}
