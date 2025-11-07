package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Conteudos;
import sistemainfantil.repository.ConteudosRepository;

import java.util.List;

@RestController
@RequestMapping("/api/conteudos")
@CrossOrigin("*")
public class ConteudosController {

    @Autowired
    private ConteudosRepository repository;

    @GetMapping
    public ResponseEntity<List<Conteudos>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_conteudos}")
    public ResponseEntity<Conteudos> getById(@PathVariable("id_conteudos") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Conteudos> create(@RequestBody Conteudos conteudos) {
        if (conteudos.getId() != null) {
            return ResponseEntity.badRequest().build(); // Não deve-se fornecer ID para criar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(conteudos));
    }

    @PutMapping("/{id_conteudos}")
    @Transactional
    public ResponseEntity<Conteudos> update(@PathVariable("id_conteudos") Long id, @RequestBody Conteudos novoConteudos) {
        return repository.findById(id)
                .map(conteudosExistente -> {
                    conteudosExistente.setTitulo(novoConteudos.getTitulo());
                    conteudosExistente.setTempo(novoConteudos.getTempo());
                    conteudosExistente.setSinopse(novoConteudos.getSinopse());
                    return ResponseEntity.ok(repository.save(conteudosExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_conteudos}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_conteudos") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}