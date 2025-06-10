package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Consomem;
import sistemainfantil.repository.ConsomemRepository;

import java.util.List;

@RestController
@RequestMapping("/consomem")
public class ConsomemController {

    @Autowired
    private ConsomemRepository repository;

    @GetMapping
    public List<Consomem> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Consomem create(@RequestBody Consomem consomem) {
        return repository.save(consomem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
