package com.senac.api.controller;


import com.senac.api.dtos.PessoaFisicaDTO;
import com.senac.api.entidades.PessoaFisica;
import com.senac.api.service.PessoaFisicaService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController {

    private PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService){
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaFisica>> listar() {
        return ResponseEntity.ok(pessoaFisicaService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<PessoaFisica> criar(@RequestBody PessoaFisicaDTO pessooa) {
        try {
            return ResponseEntity.ok(pessoaFisicaService.criar(pessooa));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaFisica> atualizar(@PathVariable Long id, @RequestBody PessoaFisicaDTO pessoa) {
        try {
            return ResponseEntity.ok(pessoaFisicaService.atualizar(id, pessoa));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pessoaFisicaService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
