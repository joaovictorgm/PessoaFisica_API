package com.senac.api.service;


import com.senac.api.dtos.PessoaJuridicaDTO;
import com.senac.api.entidades.PessoaJuridica;
import com.senac.api.repositorio.PessoaJuridicaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {
    private PessoaJuridicaRepositorio pessoaJuridicaRepositorio;

    public PessoaJuridicaService(PessoaJuridicaRepositorio pessoaJuridicaRepositorio) {
        this.pessoaJuridicaRepositorio = pessoaJuridicaRepositorio;
    }

    public PessoaJuridica criar(PessoaJuridicaDTO pessoa){
        this.validarCnpj(pessoa.getCnpj());

        if (pessoaJuridicaRepositorio.findByEmail(pessoa.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        PessoaJuridica pessoaJuridica = this.PessoaJuridicaDtoParaPessoaJuridica(pessoa);
        return pessoaJuridicaRepositorio.save(pessoaJuridica);
    }

    public PessoaJuridica buscar(Long id){
        return pessoaJuridicaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pessoa jurídica não encontrada!"));
    }

    public PessoaJuridica atualizar(Long id, PessoaJuridicaDTO entrada) {
        this.validarCnpj(entrada.getCnpj());

        PessoaJuridica saida = pessoaJuridicaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pessoa jurídica não encontrada"));
        saida.setRazaoSocial(entrada.getRazaoSocial());
        saida.setDataFundacao(entrada.getDataFundacao());
        saida.setInscricaoEstadual(entrada.getInscricaoEstadual());
        saida.setCnpj(entrada.getCnpj());

        return pessoaJuridicaRepositorio.save(saida);

    }

    public void deletar(Long id) {
        if (pessoaJuridicaRepositorio.existsById(id)) {
            pessoaJuridicaRepositorio.deleteById(id);
            return;
        }
        throw new RuntimeException("Pessoa jurídica não encontrada!");
    }

    public List<PessoaJuridica> listar() {
        return pessoaJuridicaRepositorio.findAll();
    }

    private PessoaJuridica PessoaJuridicaDtoParaPessoaJuridica(PessoaJuridicaDTO entrada) {
        PessoaJuridica saida= new PessoaJuridica();
        saida.setRazaoSocial(entrada.getRazaoSocial());
        saida.setDataFundacao(entrada.getDataFundacao());
        saida.setInscricaoEstadual(entrada.getInscricaoEstadual());
        saida.setCnpj(entrada.getCnpj());
        saida.setEmail(entrada.getEmail());
        return saida;
    }

    private void validarCnpj(String cnpj) {
        if(cnpj == null) {
            throw new RuntimeException("CNPJ deve ser preenchido!");
        }
        if (cnpj.length() != 14) {
            throw  new RuntimeException("CNPJ inválido! Deve ter 14 caracteres. ");
        }
    }


}
