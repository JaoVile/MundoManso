package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.TemTipo;
import sistemainfantil.repository.TemTipoRepository;
import sistemainfantil.repository.TiposRepository; // Import necessário para validação
import sistemainfantil.repository.ConteudosRepository; // Import necessário para validação

import java.util.List;

@RestController
@RequestMapping("/api/temtipo")
@CrossOrigin("*")
public class TemTipoController {

    @Autowired
    private TemTipoRepository repository;

    @Autowired // Repositório para validação de FK
    private TiposRepository tiposRepository;
    @Autowired // Repositório para validação de FK
    private ConteudosRepository conteudosRepository;

    @GetMapping
    public ResponseEntity<List<TemTipo>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_temtipo}")
    public ResponseEntity<TemTipo> getById(@PathVariable("id_temtipo") Long id) { // <-- CORRIGIDO AQUI
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody TemTipo temTipo) {
        if (temTipo.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID ao criar nova associação 'TemTipo'.");
        }
        if (temTipo.getTipo() == null || temTipo.getTipo().getId() == null ||
            temTipo.getConteudo() == null || temTipo.getConteudo().getId() == null) {
            return ResponseEntity.badRequest().body("IDs de Tipo e Conteúdo são obrigatórios para criar uma associação 'TemTipo'.");
        }

        // Validação da existência das FKs
        if (!tiposRepository.existsById(temTipo.getTipo().getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Tipo com ID " + temTipo.getTipo().getId() + " não encontrado.");
        }
        if (!conteudosRepository.existsById(temTipo.getConteudo().getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Conteúdo com ID " + temTipo.getConteudo().getId() + " não encontrado.");
        }

        try {
            TemTipo temTipoSalvo = repository.save(temTipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(temTipoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno ao criar associação 'TemTipo': " + e.getMessage());
        }
    }

    // Não é necessário o método PUT para esta entidade na estrutura atual

    @DeleteMapping("/{id_temtipo}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_temtipo") Long id) { // <-- CORRIGIDO AQUI
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}