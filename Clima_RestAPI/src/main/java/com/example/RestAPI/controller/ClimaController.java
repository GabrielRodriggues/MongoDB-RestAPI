package com.example.RestAPI.controller;

import com.example.RestAPI.model.ClimaEntity;
import com.example.RestAPI.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClimaController {

    @Autowired
    private ClimaService service;

    @GetMapping("/clima")
    public String preverTempo(){
        return service.preverTempo();
    }

    @Autowired
    private ClimaService userService;

    @GetMapping
    public List<ClimaEntity> obterTodos() {
        return userService.obterTodos();
    }

    @GetMapping("/{id}")
    public ClimaEntity obterPorId(@PathVariable String id) {
        return userService.obterPorId(id);
    }

    @PostMapping
    public ClimaEntity inserir(@RequestBody ClimaEntity clima) { return userService.inserir(clima); }

    @PutMapping("/{id}")
    public ClimaEntity atualizar(@PathVariable String id, @RequestBody ClimaEntity clima) {
        return userService.atualizar(id, clima);
    }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        userService.excluir(id);
    }

}