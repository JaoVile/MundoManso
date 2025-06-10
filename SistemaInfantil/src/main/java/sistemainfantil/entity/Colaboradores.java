package sistemainfantil.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Colaboradores")
public class Colaboradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaboradores")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "chave")
    private String chave;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    // Relacionamento com Publicam
    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
    private List<Publicam> conteudosPublicados;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public List<Publicam> getConteudosPublicados() { return conteudosPublicados; }
    public void setConteudosPublicados(List<Publicam> conteudosPublicados) { this.conteudosPublicados = conteudosPublicados; }
}
