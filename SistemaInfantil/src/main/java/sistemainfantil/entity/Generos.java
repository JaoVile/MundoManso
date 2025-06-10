package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Generos")
public class Generos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_generos")
    private Long id;

    @Column(name = "acao")
    private String acao;
    
    @Column(name = "suspense")
    private String suspense;
    
    @Column(name = "fantasia")
    private String fantasia;
    
    @Column(name = "musical")
    private String musical;
    
    @Column(name = "aventura")
    private String aventura;
    
    // Relacionamento com TemGenero (Conteudos)
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private List<TemGenero> conteudos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }
    
    public String getSuspense() { return suspense; }
    public void setSuspense(String suspense) { this.suspense = suspense; }
    
    public String getFantasia() { return fantasia; }
    public void setFantasia(String fantasia) { this.fantasia = fantasia; }
    
    public String getMusical() { return musical; }
    public void setMusical(String musical) { this.musical = musical; }
    
    public String getAventura() { return aventura; }
    public void setAventuras(String aventura) { this.aventura = aventura; }
    
    public List<TemGenero> getConteudos() { return conteudos; }
    public void setConteudos(List<TemGenero> conteudos) { this.conteudos = conteudos; }
}
