package br.com.forumhub.dtos;

import br.com.forumhub.models.Usuario;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto extends CreateUsuarioDto {
    private Long id;

    public UsuarioDto(Usuario usuario) {
        super(usuario);
        this.id = usuario.getId();
    }
}
