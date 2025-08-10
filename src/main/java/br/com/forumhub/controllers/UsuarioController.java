package br.com.forumhub.controllers;

import br.com.forumhub.dtos.CreateUsuarioDto;
import br.com.forumhub.dtos.UsuarioDto;
import br.com.forumhub.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioDto criar(@Valid @RequestBody CreateUsuarioDto dto) {
        return usuarioService.criarUsuario(dto);
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDto buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioDto atualizar(@PathVariable Long id, @Valid @RequestBody CreateUsuarioDto dto) {
        return usuarioService.atualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}