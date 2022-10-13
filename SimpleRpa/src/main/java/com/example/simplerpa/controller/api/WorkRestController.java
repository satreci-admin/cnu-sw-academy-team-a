package com.example.simplerpa.controller.api;

import com.example.simplerpa.model.Work;
import com.example.simplerpa.service.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WorkRestController {
    private final WorkService workService;

    public WorkRestController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/api/v1/works")
    public List<Work> productList(){
        return workService.getAllWork();
    }

}
