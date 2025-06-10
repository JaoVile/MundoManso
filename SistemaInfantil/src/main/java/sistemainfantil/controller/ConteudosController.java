package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Conteudos;
import sistemainfantil.repository.ConteudosRepository;

import java.util.List;

@RestController
@RequestMapping("/conteudos")
public class ConteudosController {

    @Autowired
    private ConteudosRepository repository;

    @GetMapping
    public List<Conteudos> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Conteudos create(@RequestBody Conteudos conteudos) {
        return repository.save(conteudos);
    }

    @PutMapping("/{id_conteudos}")
    public ResponseEntity<Conteudos> update(
            @PathVariable("id_conteudos") Long id,
            @RequestBody Conteudos novoConteudos) {
        return repository.findById(id)
                .map(conteudos -> {
                    conteudos.setTitulo(novoConteudos.getTitulo());
                    conteudos.setTempo(novoConteudos.getTempo());
                    conteudos.setSinopse(novoConteudos.getSinopse());
                    Conteudos atualizado = repository.save(conteudos);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_conteudos}")
    public ResponseEntity<Void> delete(@PathVariable("id_conteudos") Long id) {
        return repository.findById(id)
                .map(conteudos -> {
                    repository.delete(conteudos);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}