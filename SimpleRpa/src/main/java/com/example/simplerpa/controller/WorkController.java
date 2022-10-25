package com.example.simplerpa.controller;

import com.example.simplerpa.model.Email;
import com.example.simplerpa.service.WorkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/works")
    public String worksPage(Model model){
        var works = workService.getAllWork();
        model.addAttribute("works", works);
        return "work-list";
    }

    @GetMapping("new-work")
    public String newWorkPage() {
        return "new-work";
    }

    @PostMapping("/works")
    public String newProduct(CreateWorkRequest createWorkRequest){
        workService.createWork(
                new Email(createWorkRequest.email()),
                createWorkRequest.name(),
                createWorkRequest.contents(),
                createWorkRequest.schedulerCron());
        return "redirect:/works";
    }
}
