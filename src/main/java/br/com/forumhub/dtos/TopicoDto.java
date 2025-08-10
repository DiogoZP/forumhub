package br.com.forumhub.dtos;

import br.com.forumhub.models.StatusTopico;
import br.com.forumhub.models.Topico;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TopicoDto extends CreateTopicoDto {
    private Long id;

    public TopicoDto(Topico topico) {
        super(topico);
        this.id = topico.getId();
    }
}