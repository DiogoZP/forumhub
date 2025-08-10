package br.com.forumhub.services;

import br.com.forumhub.dtos.CreateUsuarioDto;
import br.com.forumhub.dtos.UsuarioDto;
import br.com.forumhub.models.Usuario;
import br.com.forumhub.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDto criarUsuario(CreateUsuarioDto dto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(dto.getEmail());
        if (optionalUsuario.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDto::new)
                .toList();
    }

    public UsuarioDto buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDto(usuario);
    }

    public UserDetails buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .build();
    }

    public UsuarioDto atualizarUsuario(Long id, CreateUsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}