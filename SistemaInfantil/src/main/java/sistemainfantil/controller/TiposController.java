package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Tipos;
import sistemainfantil.repository.TiposRepository;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TiposController {

    @Autowired
    private TiposRepository repository;

    @GetMapping
    public List<Tipos> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Tipos create(@RequestBody Tipos tipo) {
        return repository.save(tipo);
    }

    @PutMapping("/{id_tipos}")
    public ResponseEntity<Tipos> update(@PathVariable("id_tipos") Long id_tipos, @RequestBody Tipos novoTipo) {
        return repository.findById(id_tipos).map(t -> {
            t.setLivros(novoTipo.getLivros());
            t.setJogos(novoTipo.getJogos());
            t.setSeries(novoTipo.getSeries());
            t.setFilmes(novoTipo.getFilmes());
            return ResponseEntity.ok(repository.save(t));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_tipos}")
    public ResponseEntity<Void> delete(@PathVariable("id_tipos") Long id_tipos) {
        if (!repository.existsById(id_tipos)) return ResponseEntity.notFound().build();
        repository.deleteById(id_tipos);
        return ResponseEntity.noContent().build();
    }
}
