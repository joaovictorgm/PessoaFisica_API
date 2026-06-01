package com.senac.api.repositorio;

import com.senac.api.entidades.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaFisicaRepositorio extends JpaRepository<PessoaFisica, Long> {
}
