package br.com.forumhub.dtos;
import br.com.forumhub.models.Topico;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTopicoDto {

        public CreateTopicoDto(Topico topico){
                this.titulo = topico.getTitulo();
                this.mensagem = topico.getMensagem();
                this.autor = topico.getAutor();
                this.curso = topico.getCurso();
        }

        @NotBlank(message = "O título é obrigatório")
        @Size(min = 5, message = "O título deve ter pelo menos 5 caracteres")
        private String titulo;

        @NotBlank(message = "A mensagem é obrigatória")
        private String mensagem;

        @NotBlank(message = "O autor é obrigatório")
        private String autor;

        @NotBlank(message = "O curso é obrigatório")
        private String curso;
}
