package br.com.srpingboot.curso_treinamento_jdev.controllers;

import br.com.srpingboot.curso_treinamento_jdev.model.Usuario;
import br.com.srpingboot.curso_treinamento_jdev.repository.UsuarioRepository;
import br.com.srpingboot.curso_treinamento_jdev.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired /*IC/CD ou CDI - Injeção de dependência*/
    private UsuarioRepository usuarioRepository;


    // METODO LISTAR TODOS OS USUARIOS
    @GetMapping(value = "listarTodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {


        List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/
        return new ResponseEntity<>(usuarios, HttpStatus.OK); /*Retorna a lista em JSON*/
    }


    // METODO PARA SALVAR NOVO USUÁRIO
    @PostMapping(value = "salvar")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<?> salvar(@org.jetbrains.annotations.NotNull @RequestBody Usuario usuario) { //Recebe os dados para salvar

        usuarioService.verificarSeIdFoiInformado(usuario.getId());
//        usuarioService.verificarSeUsuarioExiste(usuario.getId());

        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //METODO PARA DELETAR USUÁRIO
    @DeleteMapping(value = "delete")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<?> delete(@RequestParam Long iduser) { //Recebe os dados para deletar

        usuarioService.verificarSeIdFoiInformado(iduser);
        usuarioService.verificarSeUsuarioExiste(iduser);

        usuarioRepository.deleteById(iduser);
        return new ResponseEntity<>("User deletado com sucesso", HttpStatus.OK);
    }

    //METODO PARA LOCALIZAR USUÁRIO PELO ID
    @GetMapping(value = "buscarUserId")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<?> buscarUserId(@RequestParam(name = "iduser") Long iduser) {  // Recebe dados para
        // consultar

        usuarioService.verificarSeIdFoiInformado(iduser);
        usuarioService.verificarSeUsuarioExiste(iduser);

        Usuario usuarioEncontrado = usuarioRepository.findById(iduser).get();
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    }

    // METODO PARA ATUALIZAR USUÁRIO
    @PutMapping(value = "atualizar")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { //Recebe os dados para atualizar

        usuarioService.verificarSeIdFoiInformado(usuario.getId());
        usuarioService.verificarSeUsuarioExiste(usuario.getId());

        Usuario usuarioAtualizado = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }

    //METODO PARA LOCALIZAR USUÁRIO POR PARTE DO NOME

    @GetMapping(value = "buscarPorNome")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

        List<Usuario> usuarioEncontrado = usuarioRepository.buscarPorNome(name);
        return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
    }


}

