package br.com.forumhub.repositories;

import br.com.forumhub.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
