package com.projetolombok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetolombok.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario,Long> {

}
