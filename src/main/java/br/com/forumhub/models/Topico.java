package br.com.forumhub.models;

import br.com.forumhub.dtos.CreateTopicoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topicos")
public class Topico {

    public Topico(CreateTopicoDto dto) {
        this.titulo = dto.getTitulo();
        this.mensagem = dto.getMensagem();
        this.autor = dto.getAutor();
        this.curso = dto.getCurso();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres")
    private String titulo;

    @NotBlank(message = "A mensagem é obrigatória")
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.ABERTO;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

}
