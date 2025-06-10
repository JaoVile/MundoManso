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
@RequestMapping("/colaboradores")
public class ColaboradoresController {

    @Autowired
    private ColaboradoresRepository repository;

    @GetMapping
    public List<Colaboradores> getAll() {
        return repository.findAll();
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
        if (colaborador.getNome() == null || colaborador.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }

        Colaboradores colaboradorSalvo = repository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorSalvo);
    }

    @PutMapping("/{id_colaboradores}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable("id_colaboradores") Long id,
            @RequestBody Colaboradores novoColaborador) {

        if (novoColaborador.getNome() == null || novoColaborador.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório");
        }

        return repository.findById(id)
                .map(colabExistente -> {
                    colabExistente.setNome(novoColaborador.getNome());
                    colabExistente.setEmail(novoColaborador.getEmail());
                    colabExistente.setChave(novoColaborador.getChave());

                    if (novoColaborador.getDataNascimento() != null) {
                        colabExistente.setDataNascimento(novoColaborador.getDataNascimento());
                    }

                    repository.flush();
                    return ResponseEntity.ok(colabExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_colaboradores}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_colaboradores") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            repository.flush();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

