package br.com.forumhub.controllers;

import br.com.forumhub.dtos.CreateTopicoDto;
import br.com.forumhub.dtos.TopicoDto;
import br.com.forumhub.services.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TopicoController {

    private final TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoDto> criar(@RequestBody @Valid CreateTopicoDto dto) {
        TopicoDto resposta = service.criar(dto);
        return ResponseEntity.created(URI.create("/topicos/" + resposta.getId())).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid CreateTopicoDto dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}