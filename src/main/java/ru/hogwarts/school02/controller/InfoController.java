package ru.hogwarts.school02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school02.service.InfoService;

@RestController
public class InfoController {

    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/getPort")
    public int getPort( ) {
        return infoService.getPort();

    }
}