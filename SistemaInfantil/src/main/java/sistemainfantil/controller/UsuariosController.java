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
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuariosController {

    @Autowired
    private UsuariosRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuarios>> getAll() {
        return ResponseEntity.ok(repository.findAll());
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
        if (usuario.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID ao criar novo usuário.");
        }
        // nome, email, senha continuam obrigatórios aqui:
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) { // Adicionado validação para email
            return ResponseEntity.badRequest().body("Email é obrigatório");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) { // Adicionado validação para senha
            return ResponseEntity.badRequest().body("Senha é obrigatória");
        }
        // dataNascimento não é validado aqui, pois se torna opcional
        Usuarios usuarioSalvo = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{id_usuarios}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("id_usuarios") Long id, @RequestBody Usuarios novoUsuario) {
        // nome, email, senha continuam obrigatórios aqui:
        if (novoUsuario.getNome() == null || novoUsuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        if (novoUsuario.getEmail() == null || novoUsuario.getEmail().trim().isEmpty()) { // Adicionado validação para email
            return ResponseEntity.badRequest().body("Email é obrigatório");
        }
        if (novoUsuario.getSenha() == null || novoUsuario.getSenha().trim().isEmpty()) { // Adicionado validação para senha
            return ResponseEntity.badRequest().body("Senha é obrigatória");
        }

        return repository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(novoUsuario.getNome());
                    usuarioExistente.setEmail(novoUsuario.getEmail());
                    usuarioExistente.setSenha(novoUsuario.getSenha());
                    // dataNascimento é opcional: só atualiza se for fornecido no JSON
                    // Se for fornecido como null no JSON, o campo no banco será setado para null
                    usuarioExistente.setDataNascimento(novoUsuario.getDataNascimento());
                    // Ou, se quiser manter o valor existente se não for fornecido:
                    // if (novoUsuario.getDataNascimento() != null) {
                    //     usuarioExistente.setDataNascimento(novoUsuario.getDataNascimento());
                    // } else if (novoUsuario.getDataNascimento() == null && seuJsonIncluiuExplicitamente) {
                    //     usuarioExistente.setDataNascimento(null); // Se você realmente enviou null no JSON
                    // }
                    return ResponseEntity.ok(repository.save(usuarioExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_usuarios}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_usuarios") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}