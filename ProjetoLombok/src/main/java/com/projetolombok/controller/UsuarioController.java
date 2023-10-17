package com.projetolombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetolombok.entities.Usuario;
import com.projetolombok.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name= "Usuarios", description = "API REST DE GERENCIAMENTO  DE USUARIOS")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza usuario por ID")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if(usuario !=null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os usuarios")
	public ResponseEntity<List<Usuario>> buscaTodosUsuariosControl(){
		List<Usuario> Usuarios = usuarioService.buscaTodosUsuarios();
		return ResponseEntity.ok(Usuarios);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastra um usuario")
	public ResponseEntity<Usuario> salvaUsuariosControl(@RequestBody @Valid Usuario usuario){
		Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
}
	@PutMapping("/{id}")
	@Operation(summary= "Altera usuario")
	public ResponseEntity<Usuario> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario alteraUsuario = usuarioService.alterarUsuario(id, usuario);
		if(alteraUsuario !=null) {
			return ResponseEntity.ok(usuario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Deleta usuario")
	public ResponseEntity<Usuario> apagaUsuarioControl(@PathVariable Long id){
		boolean apagar = usuarioService.apagarUsuario(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
