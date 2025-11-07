package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Consomem;
import sistemainfantil.repository.ConsomemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/consomem")
@CrossOrigin("*")
public class ConsomemController {

    @Autowired
    private ConsomemRepository repository;

    @GetMapping
    public ResponseEntity<List<Consomem>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_consomem}")
    public ResponseEntity<Consomem> getById(@PathVariable("id_consomem") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Consomem consomem) {
        if (consomem.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID ao criar novo registro de consumo.");
        }
        if (consomem.getUsuario() == null || consomem.getUsuario().getId() == null ||
            consomem.getConteudo() == null || consomem.getConteudo().getId() == null) {
            return ResponseEntity.badRequest().body("Usuário e Conteúdo com IDs válidos são obrigatórios.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(consomem));
    }

    @DeleteMapping("/{id_consomem}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_consomem") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}