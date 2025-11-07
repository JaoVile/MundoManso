package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Publicam")
public class Publicam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicam")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_colaboradores_id", nullable = false)
    private Colaboradores colaborador;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Colaboradores getColaborador() { return colaborador; }
    public void setColaborador(Colaboradores colaborador) { this.colaborador = colaborador; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}