package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autores")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "obras")
    private String obras;
    
    // Relacionamento com Fazem
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Fazem> conteudos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getObras() { return obras; }
    public void setObras(String obras) { this.obras = obras; }
    
    public List<Fazem> getConteudos() { return conteudos; }
    public void setConteudos(List<Fazem> conteudos) { this.conteudos = conteudos; }
}
