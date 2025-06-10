package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.TemGenero;
import sistemainfantil.repository.TemGeneroRepository;

import java.util.List;

@RestController
@RequestMapping("/temgenero")
public class TemGeneroController {

    @Autowired
    private TemGeneroRepository repository;

    @GetMapping
    public List<TemGenero> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public TemGenero create(@RequestBody TemGenero temGenero) {
        return repository.save(temGenero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
