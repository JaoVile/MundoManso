package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Fazem;
import sistemainfantil.repository.FazemRepository;

import java.util.List;

@RestController
@RequestMapping("/fazem")
public class FazemController {

    @Autowired
    private FazemRepository repository;

    @GetMapping
    public List<Fazem> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Fazem create(@RequestBody Fazem fazem) {
        return repository.save(fazem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
