package br.com.srpingboot.curso_treinamento_jdev.repository;

import br.com.srpingboot.curso_treinamento_jdev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
