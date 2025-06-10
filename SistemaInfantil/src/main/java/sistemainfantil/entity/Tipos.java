package sistemainfantil.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Tipos")
public class Tipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipos")
    private Long id;
    
    @Column(name = "livros")
    private String livros;
    
    @Column(name = "jogos")
    private String jogos;
    
    @Column(name = "series")
    private String series;
    
    @Column(name = "filmes")
    private String filmes;
    
    // 🔑 Relacionamento com TemTipo (Conteudos)
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<TemTipo> conteudos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLivros() { return livros; }
    public void setLivros(String livros) { this.livros = livros; }
    
    public String getJogos() { return jogos; }
    public void setJogos(String jogos) { this.jogos = jogos; }
    
    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }
    
    public String getFilmes() { return filmes; }
    public void setFilmes(String filmes) { this.filmes = filmes; }
    
    public List<TemTipo> getConteudos() { return conteudos; }
    public void setConteudos(List<TemTipo> conteudos) { this.conteudos = conteudos; }
}
