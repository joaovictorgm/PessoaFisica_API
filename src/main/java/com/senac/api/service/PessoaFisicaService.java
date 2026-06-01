package com.senac.api.service;

import com.senac.api.dtos.PessoaFisicaDTO;
import com.senac.api.entidades.PessoaFisica;
import com.senac.api.repositorio.PessoaFisicaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    private PessoaFisicaRepositorio pessoaFisicaRepositorio;

    public PessoaFisicaService(PessoaFisicaRepositorio pessoaFisicaRepositorio) {
        this.pessoaFisicaRepositorio = pessoaFisicaRepositorio;
    }

    public PessoaFisica criar(PessoaFisicaDTO pessoa) {

        this.validarCpf(pessoa.getCpf());

        PessoaFisica pessoaFisicaPersist = this.pessoaFisicaDTOParaPessoaFisica(pessoa);

        return pessoaFisicaRepositorio.save(pessoaFisicaPersist);
    }

    public PessoaFisica atualizar(Long id, PessoaFisicaDTO pessoa) {

        this.validarCpf(pessoa.getCpf());

        if(pessoaFisicaRepositorio.existsById(id)){
            PessoaFisica pessoaFisicaPersist = this.pessoaFisicaDTOParaPessoaFisica(pessoa);
            pessoaFisicaPersist.setId(id);

            return pessoaFisicaRepositorio.save(pessoaFisicaPersist);
        }

        throw new RuntimeException("Pessoa não encontrada!");
    }

    public void deletar(Long id) {
        if(pessoaFisicaRepositorio.existsById(id)){
            pessoaFisicaRepositorio.deleteById(id);
            return;
        }

        throw  new RuntimeException("Pessoa não encontrada!");
    }

    public List<PessoaFisica> listar(){
        return pessoaFisicaRepositorio.findAll();
    }

    private PessoaFisica pessoaFisicaDTOParaPessoaFisica(PessoaFisicaDTO entrada) {
        PessoaFisica saida = new PessoaFisica();
        saida.setNome(entrada.getNome());
        saida.setCpf(entrada.getCpf());
        saida.setEmail(entrada.getEmail());
        saida.setDataNascimento(entrada.getDataNascimento());

        return saida;
    }

    private void validarCpf(String cpf) {
        if(cpf == null){
            throw new RuntimeException("CPF deve ser preenchido");
        }

        if(cpf.length() != 11) {
            throw new RuntimeException("CPF inválido!");
        }
    }

}


