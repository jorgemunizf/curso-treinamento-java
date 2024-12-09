package br.com.srpingboot.curso_treinamento_jdev.service;

import br.com.srpingboot.curso_treinamento_jdev.model.Usuario;
import br.com.srpingboot.curso_treinamento_jdev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarSeIdFoiInformado(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID não foi informado!");
        }
    }


    public Usuario verificarSeUsuarioExiste(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        return usuario.get();
    }

}
