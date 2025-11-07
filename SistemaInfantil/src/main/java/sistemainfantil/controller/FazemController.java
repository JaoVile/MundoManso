package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Fazem;
import sistemainfantil.repository.FazemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/fazem")
@CrossOrigin("*")
public class FazemController {

    @Autowired
    private FazemRepository repository;

    @GetMapping
    public ResponseEntity<List<Fazem>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_fazem}")
    public ResponseEntity<Fazem> getById(@PathVariable("id_fazem") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Fazem> create(@RequestBody Fazem fazem) {
        if (fazem.getId() != null) {
            return ResponseEntity.badRequest().build(); // Não deve-se fornecer ID para criar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(fazem));
    }

    @DeleteMapping("/{id_fazem}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_fazem") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}