package com.nava.cooperfilmes_back.controller;

import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import com.nava.cooperfilmes_back.service.RoteiroClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/roteiro")
public class RoteiroClientController {

    @Autowired
    RoteiroClientService roteiroClientService;

    @PostMapping(value = "/send-movie-script")
    public ResponseEntity<String> sendMovieScript(@RequestBody RoteiroRequestDTO request){
        return roteiroClientService.sendMovieScript(request);
    }

    @GetMapping(value = "/consult-movie-script")
    public ResponseEntity consultMovieScript(@RequestParam String email){
        return roteiroClientService.consultMovieScript(email);
    }
}
