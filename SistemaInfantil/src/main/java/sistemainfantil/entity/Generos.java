package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Generos")
public class Generos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_generos")
    private Long id;

    @Column(name = "nome_genero", nullable = false, unique = true)
    private String nomeGenero;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TemGenero> temGeneroAssociations;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeGenero() { return nomeGenero; }
    public void setNomeGenero(String nomeGenero) { this.nomeGenero = nomeGenero; }

    public List<TemGenero> getTemGeneroAssociations() { return temGeneroAssociations; }
    public void setTemGeneroAssociations(List<TemGenero> temGeneroAssociations) {
        this.temGeneroAssociations = temGeneroAssociations;
        if (temGeneroAssociations != null) {
            for (TemGenero tg : temGeneroAssociations) {
                tg.setGenero(this);
            }
        }
    }
}