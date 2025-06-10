package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Contem;
import sistemainfantil.repository.ContemRepository;

import java.util.List;

@RestController
@RequestMapping("/contem")
public class ContemController {

    @Autowired
    private ContemRepository repository;

    @GetMapping
    public List<Contem> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Contem create(@RequestBody Contem contem) {
        return repository.save(contem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
