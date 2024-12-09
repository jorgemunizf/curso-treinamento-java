package br.com.srpingboot.curso_treinamento_jdev.controllers;

import br.com.srpingboot.curso_treinamento_jdev.model.Usuario;
import br.com.srpingboot.curso_treinamento_jdev.repository.UsuarioRepository;
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
    @Autowired /*IC/CD ou CDI - Injeção de dependência*/
    private UsuarioRepository usuarioRepository;

    /**
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }

    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);    /*Vai gravar no banco de dados*/


        return "Ola mundo " + nome;
    }


    // METODO LISTAR TODOS OS USUARIOS
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {

        List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista em JSON*/
    }


    // METODO PARA SALVAR NOVO USUÁRIO
    @PostMapping(value = "salvar")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { //Recebe os dados para salvar


        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    //METODO PARA DELETAR USUÁRIO
    @DeleteMapping(value = "delete")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<String> delete(@RequestParam Long iduser) { //Recebe os dados para deletar


        usuarioRepository.deleteById(iduser);
        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }

    //METODO PARA LOCALIZAR USUÁRIO PELO ID
    @GetMapping(value = "buscaruserid")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) {  // Recebe dados para
        // consultar

        Usuario usuario = usuarioRepository.findById(iduser).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    // METODO PARA ATUALIZAR USUÁRIO
    @PutMapping(value = "atualizar")  //Mapeia a url
    @ResponseBody //Descrição da resposta
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) { //Recebe os dados para atualizar


        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }


}
