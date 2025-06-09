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
@RequestMapping("/autores")
public class AutoresController {

    @Autowired
    private AutoresRepository repository;

    @GetMapping
    public List<Autores> getAll() {
        return repository.findAll();
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
        Autores autorSalvo = repository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @PutMapping("/{id_autores}")
    @Transactional
    public ResponseEntity<Autores> update(
            @PathVariable("id_autores") Long id,
            @RequestBody Autores novoAutor) {
        
        if (novoAutor.getNome() == null || novoAutor.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return repository.findById(id)
                .map(autorExistente -> {

                    autorExistente.setNome(novoAutor.getNome());
                    autorExistente.setObras(novoAutor.getObras());
                  
                    repository.flush();
                    return ResponseEntity.ok(autorExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_autores}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_autores") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            repository.flush();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}