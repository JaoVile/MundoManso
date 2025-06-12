package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException; // Importar se for tratar outras exceções UNIQUE
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.Episodios;
import sistemainfantil.repository.EpisodiosRepository;
import sistemainfantil.repository.ConteudosRepository; // <--- NOVO IMPORT AQUI

import java.util.List;

@RestController
@RequestMapping("/api/episodios")
@CrossOrigin("*")
public class EpisodiosController {

    @Autowired
    private EpisodiosRepository repository;

    @Autowired // <--- INJETAR ConteudosRepository
    private ConteudosRepository conteudosRepository;

    @GetMapping
    public ResponseEntity<List<Episodios>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id_episodios}")
    public ResponseEntity<Episodios> getById(@PathVariable("id_episodios") Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Episodios episodio) { // Alterado para ResponseEntity<?>
        if (episodio.getId() != null) {
            return ResponseEntity.badRequest().body("Não forneça um ID ao criar novo episódio.");
        }
        if (episodio.getNumero() == null || episodio.getNumero().isBlank() ||
            episodio.getUrl() == null || episodio.getUrl().isBlank()) {
            return ResponseEntity.badRequest().body("Número e URL do episódio são obrigatórios.");
        }
        if (episodio.getConteudo() == null || episodio.getConteudo().getId() == null) {
            return ResponseEntity.badRequest().body("Um episódio deve estar associado a um Conteúdo existente (ID do Conteúdo é obrigatório).");
        }

        // --- VALIDAÇÃO CRÍTICA DE CHAVE ESTRANGEIRA AQUI ---
        Long conteudoId = episodio.getConteudo().getId();
        if (!conteudosRepository.existsById(conteudoId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Conteúdo com ID " + conteudoId + " não encontrado.");
        }
        // --- FIM DA VALIDAÇÃO CRÍTICA ---

        try {
            Episodios episodioSalvo = repository.save(episodio);
            return ResponseEntity.status(HttpStatus.CREATED).body(episodioSalvo);
        } catch (DataIntegrityViolationException e) {
            // Este catch pode ser útil para outras restrições de unicidade, se houver
            // Para FK, o `existsById` já trata antes de chegar aqui
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Conflito de dados ao criar episódio: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno ao criar episódio: " + e.getMessage());
        }
    }

    @PutMapping("/{id_episodios}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable("id_episodios") Long id, @RequestBody Episodios novoEpisodio) { // Alterado para ResponseEntity<?>
        if (novoEpisodio.getNumero() == null || novoEpisodio.getNumero().isBlank() ||
            novoEpisodio.getUrl() == null || novoEpisodio.getUrl().isBlank()) {
            return ResponseEntity.badRequest().body("Número e URL do episódio são obrigatórios para atualização.");
        }
        if (novoEpisodio.getConteudo() == null || novoEpisodio.getConteudo().getId() == null) {
            return ResponseEntity.badRequest().body("Um episódio deve estar associado a um Conteúdo existente (ID do Conteúdo é obrigatório).");
        }

        // Validação da FK também no PUT
        Long conteudoId = novoEpisodio.getConteudo().getId();
        if (!conteudosRepository.existsById(conteudoId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Conteúdo com ID " + conteudoId + " não encontrado para associar.");
        }

        return repository.findById(id)
                .map(epExistente -> {
                    epExistente.setNumero(novoEpisodio.getNumero());
                    epExistente.setUrl(novoEpisodio.getUrl());
                    epExistente.setConteudo(novoEpisodio.getConteudo()); // Associa o Conteúdo
                    try {
                        return ResponseEntity.ok(repository.save(epExistente));
                    } catch (DataIntegrityViolationException e) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                             .body("Conflito de dados ao atualizar episódio: " + e.getMessage());
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                             .body("Erro interno ao atualizar episódio: " + e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_episodios}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id_episodios") Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}