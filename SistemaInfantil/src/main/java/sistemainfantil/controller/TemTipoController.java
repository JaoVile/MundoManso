package sistemainfantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemainfantil.entity.TemTipo;
import sistemainfantil.entity.Tipos;
import sistemainfantil.entity.Conteudos;
import sistemainfantil.repository.TemTipoRepository;
import sistemainfantil.repository.TiposRepository;
import sistemainfantil.repository.ConteudosRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temtipo")
public class TemTipoController {

    @Autowired
    private TemTipoRepository temTipoRepository;

    @Autowired
    private TiposRepository tiposRepository;

    @Autowired
    private ConteudosRepository conteudosRepository;

    @GetMapping
    public List<TemTipo> getAll() {
        return temTipoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TemTipo temTipo) {
        // Validação do campo tipo
        if (temTipo.getTipo() == null || temTipo.getTipo().getId() == null) {
            return ResponseEntity.badRequest().body("O campo 'tipo' deve conter um ID válido.");
        }

        // Validação do campo conteudo
        if (temTipo.getConteudo() == null || temTipo.getConteudo().getId() == null) {
            return ResponseEntity.badRequest().body("O campo 'conteudo' deve conter um ID válido.");
        }

        Optional<Tipos> tipoExistente = tiposRepository.findById(temTipo.getTipo().getId());
        Optional<Conteudos> conteudoExistente = conteudosRepository.findById(temTipo.getConteudo().getId());

        if (tipoExistente.isEmpty()) {
            return ResponseEntity.badRequest().body("O tipo com ID " + temTipo.getTipo().getId() + " não existe.");
        }

        if (conteudoExistente.isEmpty()) {
            return ResponseEntity.badRequest().body("O conteúdo com ID " + temTipo.getConteudo().getId() + " não existe.");
        }

        temTipo.setTipo(tipoExistente.get());
        temTipo.setConteudo(conteudoExistente.get());

        TemTipo novoTemTipo = temTipoRepository.save(temTipo);
        return ResponseEntity.ok(novoTemTipo);
    }

    @DeleteMapping("/{id_tem_tipo}")
    public ResponseEntity<Void> delete(@PathVariable Long id_tem_tipo) {
        if (!temTipoRepository.existsById(id_tem_tipo)) {
            return ResponseEntity.notFound().build();
        }
        temTipoRepository.deleteById(id_tem_tipo);
        return ResponseEntity.noContent().build();
    }
}
