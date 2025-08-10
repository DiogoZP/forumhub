package br.com.forumhub.services;

import br.com.forumhub.dtos.CreateTopicoDto;
import br.com.forumhub.dtos.TopicoDto;
import br.com.forumhub.models.Topico;
import br.com.forumhub.repositories.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoDto criar(CreateTopicoDto dto) {
        Topico topico = new Topico(dto);
        repository.save(topico);
        return new TopicoDto(topico);
    }

    public List<TopicoDto> listar() {
        return repository.findAll()
                .stream()
                .map(TopicoDto::new)
                .collect(Collectors.toList());
    }

    public Optional<TopicoDto> buscarPorId(Long id) {
        return repository.findById(id).map(TopicoDto::new);
    }

    public Optional<TopicoDto> atualizar(Long id, CreateTopicoDto dto) {
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return Optional.empty();
        }
        Topico topico = optionalTopico.get();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setAutor(dto.getAutor());
        topico.setCurso(dto.getCurso());

        repository.save(topico);
        return Optional.of(new TopicoDto(topico));
    }

    public boolean deletar(Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}