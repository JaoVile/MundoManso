package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Episodios;
import sistemainfantil.repository.EpisodiosRepository;

import java.util.List;

@RestController
@RequestMapping("/episodios")
public class EpisodiosController {

    @Autowired
    private EpisodiosRepository repository;

    // Buscar todos os episódios
    @GetMapping
    public List<Episodios> getAll() {
        return repository.findAll();
    }

    // Criar novo episódio
    @PostMapping
    public Episodios create(@RequestBody Episodios episodio) {
        return repository.save(episodio);
    }

    // Atualizar episódio por ID
    @PutMapping("/{id_episodios}")
    public ResponseEntity<Episodios> update(@PathVariable("id_episodios") Long id_episodios,
                                            @RequestBody Episodios novoEpisodio) {
        return repository.findById(id_episodios)
                .map(ep -> {
                    ep.setNumero(novoEpisodio.getNumero());
                    ep.setUrl(novoEpisodio.getUrl());
                    return ResponseEntity.ok(repository.save(ep));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar episódio por ID
    @DeleteMapping("/{id_episodios}")
    public ResponseEntity<Void> delete(@PathVariable("id_episodios") Long id_episodios) {
        if (!repository.existsById(id_episodios)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id_episodios);
        return ResponseEntity.noContent().build();
    }
}
