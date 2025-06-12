package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; // Importar esta classe!
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Tipos;
import sistemainfantil.repository.TiposRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin("*")
public class TiposController {

    @Autowired
    private TiposRepository repository;

    @GetMapping
    public ResponseEntity<List<Tipos>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_tipos}")
    public ResponseEntity<Tipos> getById(@PathVariable("id_tipos") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Tipos tipo) { // Usamos ResponseEntity<?> para maior flexibilidade
        if (tipo.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID para criar um novo tipo.");
        }
        if (tipo.getNomeTipo() == null || tipo.getNomeTipo().isBlank()) {
            return ResponseEntity.badRequest().body("Nome do tipo é obrigatório.");
        }

        try {
            Tipos tipoSalvo = repository.save(tipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalvo);
        } catch (DataIntegrityViolationException e) {
            // Captura a exceção de violação de restrição UNIQUE
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Já existe um tipo com o nome '" + tipo.getNomeTipo() + "'.");
        } catch (Exception e) {
            // Captura outras possíveis exceções e retorna 500 genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno ao criar tipo: " + e.getMessage());
        }
    }

    @PutMapping("/{id_tipos}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("id_tipos") Long id, @RequestBody Tipos novoTipo) { // Alterado para ResponseEntity<?>
        // Validação de nome também no PUT
        if (novoTipo.getNomeTipo() == null || novoTipo.getNomeTipo().isBlank()) {
            return ResponseEntity.badRequest().body("Nome do tipo é obrigatório para atualização.");
        }

        return repository.findById(id)
                .map(tipoExistente -> {
                    tipoExistente.setNomeTipo(novoTipo.getNomeTipo());
                    try {
                        return ResponseEntity.ok(repository.save(tipoExistente));
                    } catch (DataIntegrityViolationException e) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                             .body("Já existe outro tipo com o nome '" + novoTipo.getNomeTipo() + "'.");
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                             .body("Erro interno ao atualizar tipo: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_tipos}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_tipos") Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}                                