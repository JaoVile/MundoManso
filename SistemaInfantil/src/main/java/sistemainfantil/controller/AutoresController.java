package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Autores;
import sistemainfantil.repository.AutoresRepository;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin("*")
public class AutoresController {

    @Autowired
    private AutoresRepository repository;

    @GetMapping
    public ResponseEntity<List<Autores>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_autores}")
    public ResponseEntity<Autores> getById(@PathVariable("id_autores") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Autores> create(@RequestBody Autores autor) {
        if (autor.getId() != null) {
            return ResponseEntity.badRequest().build(); // Não deve-se fornecer ID para criar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(autor));
    }

    @PutMapping("/{id_autores}")
    @Transactional
    public ResponseEntity<Autores> update(@PathVariable("id_autores") Long id, @RequestBody Autores novoAutor) {
        return repository.findById(id)
                .map(autorExistente -> {
                    autorExistente.setNome(novoAutor.getNome());
                    autorExistente.setObras(novoAutor.getObras()); // Cuidado: "obras" não deveria estar na entidade Autor
                    return ResponseEntity.ok(repository.save(autorExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_autores}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_autores") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}