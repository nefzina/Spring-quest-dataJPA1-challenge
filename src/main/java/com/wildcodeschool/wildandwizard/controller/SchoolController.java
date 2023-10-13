package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SchoolController {

    // get school repository by dependency injection
    @Autowired
    private SchoolRepository repository;

    @GetMapping("/school/list")
    public String getAll(Model model) {
        model.addAttribute("schools", repository.findAll());
        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {

        School school = new School();
        if(id != null) {
            Optional<School> optionalSchool = repository.findById(id);
            if (optionalSchool.isPresent()) {
                school = optionalSchool.get();
            }
        }
        model.addAttribute("school", school);

        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@RequestBody School school) {
        // create or update a school
        repository.save(school);
        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/schools";
    }
}
