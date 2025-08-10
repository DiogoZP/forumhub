package br.com.forumhub.dtos;

import br.com.forumhub.models.Topico;
import lombok.*;

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