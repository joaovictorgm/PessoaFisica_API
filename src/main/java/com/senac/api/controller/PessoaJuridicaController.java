package com.senac.api.controller;


import com.senac.api.dtos.PessoaJuridicaDTO;
import com.senac.api.entidades.PessoaJuridica;
import com.senac.api.service.PessoaJuridicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {

    private PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PessoaJuridica>> listar() {
        return  ResponseEntity.ok(pessoaJuridicaService.listar());
    }

    //buscar por Id
    //<?> buscar por qualquer tipo sem ficar limitado a  tipo fixo
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?>buscar(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pessoaJuridicaService.buscar(id));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new ErroDTO(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErroDTO("Erro interno do servido"));
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<PessoaJuridica> criar (@RequestBody PessoaJuridicaDTO pessoa) {
        try{
            return ResponseEntity.ok(pessoaJuridicaService.criar(pessoa));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaJuridica> atualizar(@PathVariable Long id, @RequestBody PessoaJuridicaDTO pessoa){
        try{
            return ResponseEntity.ok(pessoaJuridicaService.atualizar(id, pessoa));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try{
            pessoaJuridicaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e ){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
