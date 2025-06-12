package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; // Importar esta classe!
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Generos;
import sistemainfantil.repository.GenerosRepository;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@CrossOrigin("*")
public class GenerosController {

    @Autowired
    private GenerosRepository repository;

    @GetMapping
    public ResponseEntity<List<Generos>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_generos}")
    public ResponseEntity<Generos> getById(@PathVariable("id_generos") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Generos genero) {
        if (genero.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID para criar um novo gênero.");
        }
        if (genero.getNomeGenero() == null || genero.getNomeGenero().isBlank()) {
            return ResponseEntity.badRequest().body("Nome do gênero é obrigatório.");
        }

        try {
            Generos generoSalvo = repository.save(genero);
            return ResponseEntity.status(HttpStatus.CREATED).body(generoSalvo);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Já existe um gênero com o nome '" + genero.getNomeGenero() + "'.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno ao criar gênero: " + e.getMessage());
        }
    }

    @PutMapping("/{id_generos}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("id_generos") Long id, @RequestBody Generos updated) { // <-- TIPO DE RETORNO ALTERADO AQUI
        if (updated.getNomeGenero() == null || updated.getNomeGenero().isBlank()) {
            return ResponseEntity.badRequest().body("Nome do gênero é obrigatório para atualização.");
        }

        return repository.findById(id)
                .map(generoExistente -> {
                    generoExistente.setNomeGenero(updated.getNomeGenero());
                    try {
                        return ResponseEntity.ok(repository.save(generoExistente)); // Retorna ResponseEntity<Generos>
                    } catch (DataIntegrityViolationException e) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                             .body("Já existe outro gênero com o nome '" + updated.getNomeGenero() + "'."); // Retorna ResponseEntity<String>
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                             .body("Erro interno ao atualizar gênero: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build()); // Retorna ResponseEntity<Void> (se não encontrar)
    }

    @DeleteMapping("/{id_generos}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_generos") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}