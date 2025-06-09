package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Fazem")
public class Fazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fazem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_autores_id", nullable = false)
    private Autores autor;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Autores getAutor() { return autor; }
    public void setAutor(Autores autor) { this.autor = autor; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}
