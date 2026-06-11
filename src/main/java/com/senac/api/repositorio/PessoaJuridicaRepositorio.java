package com.senac.api.repositorio;

import com.senac.api.entidades.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaJuridicaRepositorio extends JpaRepository <PessoaJuridica, Long> {

    Optional<PessoaJuridica> findByEmail(String Email);
}
