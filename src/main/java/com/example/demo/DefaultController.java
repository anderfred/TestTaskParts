package com.example.demo;


import com.example.demo.entity.Part;
import com.example.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class DefaultController {
    @Autowired
    PartService partService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/parts")
    public String getAllParts(Model model) {
        model.addAttribute("parts", partService.findAll());
        model.addAttribute("compQty", partService.compQty());
        return "parts";
    }

    @GetMapping("/update")
    public String updatePart(@RequestParam("id") String id, Model model) {
        model.addAttribute("part", partService.findById(Integer.parseInt(id)).get());
        return "edit";
    }

    @PostMapping("/updatePart")
    public String updatePart(@ModelAttribute("id") Integer id, @ModelAttribute("name") String name, @ModelAttribute("qty") Integer qty, @ModelAttribute("important") boolean important, Model model) {
        Part p = partService.findById(id).get();
        p.setName(name);
        p.setImportant(important);
        p.setQty(qty);
        partService.save(p);
        return "redirect:/parts";
    }

    @GetMapping("/delete")
    public String deletePart(@RequestParam("id") String id) {
        partService.deleteById(Integer.parseInt(id));
        return "redirect:/parts";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("part", new Part());
        return "add";
    }

    @PostMapping("/add")
    public String addPart(@ModelAttribute("part") Part part) {
        partService.save(part);
        return "redirect:/parts";
    }

    @GetMapping("/populateDB")
    public String populate() {
        try {
            partService.populateDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/parts";
    }
}
