package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Publicam;
import sistemainfantil.repository.PublicamRepository;

import java.util.List;

@RestController
@RequestMapping("/publicam")
public class PublicamController {

    @Autowired
    private PublicamRepository repository;

    @GetMapping
    public List<Publicam> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Publicam create(@RequestBody Publicam publicam) {
        return repository.save(publicam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
