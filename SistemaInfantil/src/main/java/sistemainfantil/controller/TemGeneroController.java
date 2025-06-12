package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.TemGenero;
import sistemainfantil.repository.TemGeneroRepository;

import java.util.List;

@RestController
@RequestMapping("/api/temgenero")
@CrossOrigin("*")
public class TemGeneroController {

    @Autowired
    private TemGeneroRepository repository;

    @GetMapping
    public ResponseEntity<List<TemGenero>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_temgenero}")
    public ResponseEntity<TemGenero> getById(@PathVariable("id_temgenero") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TemGenero> create(@RequestBody TemGenero temGenero) {
        if (temGenero.getId() != null) {
            return ResponseEntity.badRequest().build(); // Não deve-se fornecer ID para criar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(temGenero));
    }

    @DeleteMapping("/{id_temgenero}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_temgenero") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}