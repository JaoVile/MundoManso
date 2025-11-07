package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Autores")
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autores")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name= "obras", nullable = false, length = 50)
    private String obras;
    
    // Relacionamento com a tabela Fazem (Conteúdos criados por este autor)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Fazem> criacoes;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getObras() { return obras; }
    public void setObras(String obras) { this.obras = obras; }

    public List<Fazem> getCriacoes() { return criacoes; }
    public void setCriacoes(List<Fazem> criacoes) { 
        this.criacoes = criacoes;
        if (criacoes != null) {
            for (Fazem f : criacoes) {
                f.setAutor(this);
            }
        }
    }
}