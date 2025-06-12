package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Publicam;
import sistemainfantil.repository.PublicamRepository;

import java.util.List;

@RestController
@RequestMapping("/api/publicam")
@CrossOrigin("*")
public class PublicamController {

    @Autowired
    private PublicamRepository repository;

    @GetMapping
    public ResponseEntity<List<Publicam>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_publicam}")
    public ResponseEntity<Publicam> getById(@PathVariable("id_publicam") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Publicam> create(@RequestBody Publicam publicam) {
        if (publicam.getId() != null) {
            return ResponseEntity.badRequest().build(); // Não deve-se fornecer ID para criar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(publicam));
    }

    @DeleteMapping("/{id_publicam}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_publicam") Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}