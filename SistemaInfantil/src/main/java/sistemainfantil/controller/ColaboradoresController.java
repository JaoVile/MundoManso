package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Colaboradores;
import sistemainfantil.repository.ColaboradoresRepository;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
@CrossOrigin("*")
public class ColaboradoresController {

    @Autowired
    private ColaboradoresRepository repository;

    @GetMapping
    public ResponseEntity<List<Colaboradores>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_colaboradores}")
    public ResponseEntity<Colaboradores> getById(@PathVariable("id_colaboradores") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Colaboradores colaborador) {
        if (colaborador.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID ao criar novo colaborador.");
        }
        if (colaborador.getNome() == null || colaborador.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        Colaboradores colaboradorSalvo = repository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorSalvo);
    }

    @PutMapping("/{id_colaboradores}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("id_colaboradores") Long id, @RequestBody Colaboradores dados) {
        if (dados.getNome() == null || dados.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }
        return repository.findById(id)
                .map(colabExistente -> {
                    colabExistente.setNome(dados.getNome());
                    colabExistente.setEmail(dados.getEmail());
                    colabExistente.setChave(dados.getChave());
                    if (dados.getDataNascimento() != null) {
                        colabExistente.setDataNascimento(dados.getDataNascimento());
                    }
                    return ResponseEntity.ok(repository.save(colabExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_colaboradores}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_colaboradores") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}