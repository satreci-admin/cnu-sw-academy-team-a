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
    @GetMapping("update-work")
    public String updateWorkPage(){
        return "update-work";
    }

    @PostMapping("/works")
    public String newWork(CreateWorkRequest createWorkRequest){
        workService.createWork(
                new Email(createWorkRequest.email()),
                createWorkRequest.robotId(),
                createWorkRequest.name(),
                createWorkRequest.contents(),
                createWorkRequest.schedulerCron());
        return "redirect:/works";
    }

    @PostMapping("/update-work")
    public String updateWork(UpdateWorkRequest updateWorkRequest){
        workService.updateWork(
                updateWorkRequest.statementId(),
                updateWorkRequest.robotId(),
                updateWorkRequest.name(),
                updateWorkRequest.contents(),
                updateWorkRequest.schedulerCron());
        return "redirect:/works";
    }
}
