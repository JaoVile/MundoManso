package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Usuarios;
import sistemainfantil.repository.UsuariosRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository repository;

    @GetMapping
    public List<Usuarios> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id_usuarios}")
    public ResponseEntity<Usuarios> getById(@PathVariable("id_usuarios") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Usuarios usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }

        Usuarios usuarioSalvo = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{id_usuarios}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable("id_usuarios") Long id,
            @RequestBody Usuarios novoUsuario) {

        if (novoUsuario.getNome() == null || novoUsuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }

        return repository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(novoUsuario.getNome());
                    usuarioExistente.setEmail(novoUsuario.getEmail());
                    usuarioExistente.setSenha(novoUsuario.getSenha());

                    if (novoUsuario.getDataNascimento() != null) {
                        usuarioExistente.setDataNascimento(novoUsuario.getDataNascimento());
                    }

                    repository.flush();
                    return ResponseEntity.ok(usuarioExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_usuarios}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_usuarios") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            repository.flush();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
