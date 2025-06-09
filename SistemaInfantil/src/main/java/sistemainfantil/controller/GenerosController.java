package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Generos;
import sistemainfantil.repository.GenerosRepository;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GenerosController {

    @Autowired
    private GenerosRepository repository;

    @GetMapping
    public List<Generos> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Generos create(@RequestBody Generos genero) {
        return repository.save(genero);
    }

    // Atualizar gênero por ID
    @PutMapping("/{id_generos}")
    public ResponseEntity<Generos> update(@PathVariable("id_generos") Long id_generos,
                                          @RequestBody Generos novoGenero) {
        return repository.findById(id_generos).map(g -> {
            g.setAcao(novoGenero.getAcao());
            g.setSuspense(novoGenero.getSuspense());
            g.setFantasia(novoGenero.getFantasia());
            g.setMusical(novoGenero.getMusical());
            g.setAventuras(novoGenero.getAventura());
            return ResponseEntity.ok(repository.save(g));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Deletar gênero por ID
    @DeleteMapping("/{id_generos}")
    public ResponseEntity<Void> delete(@PathVariable("id_generos") Long id_generos) {
        if (!repository.existsById(id_generos)) return ResponseEntity.notFound().build();
        repository.deleteById(id_generos);
        return ResponseEntity.noContent().build();
    }
}
