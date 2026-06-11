package com.senac.api.service;

import com.senac.api.dtos.PessoaFisicaDTO;
import com.senac.api.entidades.PessoaFisica;
import com.senac.api.repositorio.PessoaFisicaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public  PessoaFisica buscar (Long id) {
        return  pessoaFisicaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pessoa Fisica não encontrada"));}

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
        if (cpf.isBlank()){
            throw new RuntimeException("CPF não pode ser vazio");
        }
        if(cpf.length() != 11) {
            throw new RuntimeException("CPF inválido!");
        }
        if(cpf.chars().distinct().count() == 1) {
            throw new RuntimeException("CPF inválido!");
        }
    }
    // isBlank() verifica se a String está vazia ou contém só espaços"".isBlank()        // true  — vazio
    //"   ".isBlank()     // true  — só espaços
    //"123".isBlank()     // false — tem conteúdo
    //chars() converte a String em um stream de números, onde cada número representa o código de um caractere
    //"111".chars()  // stream de [49, 49, 49]
    // '1' = código 49 na tabela ASCII
   // Aqui conta quantos elementos sobraram no stream"11111111111".chars().distinct().count()   // 1  — só tem o dígito 1
  //12345678901".chars().distinct().count()  // 9  — vários dígitos diferentes

}


