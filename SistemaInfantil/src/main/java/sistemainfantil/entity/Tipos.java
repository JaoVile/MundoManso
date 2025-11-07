package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Tipos")
public class Tipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipos")
    private Long id;

    @Column(name = "nome_tipo", nullable = false, unique = true)
    private String nomeTipo;

    // Relacionamento com TemTipo (Conteúdos)
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TemTipo> conteudos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeTipo() { return nomeTipo; }
    public void setNomeTipo(String nomeTipo) { this.nomeTipo = nomeTipo; }

    public List<TemTipo> getConteudos() { return conteudos; }
    public void setConteudos(List<TemTipo> conteudos) {
        this.conteudos = conteudos;
        if (conteudos != null) {
            for (TemTipo t : conteudos) {
                t.setTipo(this);
            }
        }
    }
}