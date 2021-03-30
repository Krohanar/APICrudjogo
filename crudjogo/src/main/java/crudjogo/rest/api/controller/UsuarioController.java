package crudjogo.rest.api.controller;

import crudjogo.rest.api.model.UsuarioModel;
import crudjogo.rest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") Integer codigo) {
        return repository.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

      @PostMapping(path = "/api/usuario/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

    @PutMapping(value = "/api/usuario/{codigo}")
    public ResponseEntity update(@PathVariable("codigo") Integer id,
                                 @RequestBody UsuarioModel usuario) {
        return repository.findById(id)
                .map(usuarioModel -> {
                    usuario.setCodigo(usuario.getCodigo());
                    usuario.setNome(usuario.getNome());
                    usuario.setLogin(usuario.getLogin());
                    usuario.setSenha(usuario.getSenha());
                    UsuarioModel updated = repository.save(usuario);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/api/usuario/{codigo}"})
    public ResponseEntity <?> delete(@PathVariable Integer codigo) {
        return repository.findById(codigo)
                .map(usuarioModel -> {
                    repository.deleteById(codigo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/usuario")
    public List<UsuarioModel> getallPersonagens(){
        return (List<UsuarioModel>) repository.findAll();
    }


}





