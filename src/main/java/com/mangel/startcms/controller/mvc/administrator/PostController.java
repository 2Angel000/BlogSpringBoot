package com.mangel.startcms.controller.mvc.administrator;

import com.mangel.startcms.data.model.Post;
import com.mangel.startcms.data.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ModelAndView getHome(HttpServletRequest request, Model model,
                                @RequestParam(defaultValue = "all", required = false) String view_name,
                                @RequestParam(defaultValue = "0", required = false) int id,
                                SpringDataWebProperties.Pageable pageable
                                ){
        model.addAttribute("currentUri",request.getRequestURI());
        model.addAttribute("view_name",view_name);
        ModelAndView modelAndView = new ModelAndView("administrator/post");
        switch (view_name){
            case "all":
                modelAndView.addObject("posts", postRepository.findAll(pageable));
                break;
            case "new":
                modelAndView.addObject("post", new Post());
                break;
            case "update":
                modelAndView.addObject("post", postRepository.findById(id));
                break;
        }
        return modelAndView;
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Post post){
        if (post.getIdPost() > 0){
            postRepository.update(post);
        }else{
            postRepository.save(post);
        }
        return "redirect:/admin/post";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam int id){
        postRepository.deleteById(id);
        return "redirect:/admin/post";
    }
}
