package sistemainfantil.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Colaboradores")
public class Colaboradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaboradores")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "chave", nullable = false, unique = true)
    private String chave;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    // Relacionamento com Conteúdos publicados
    @OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Publicam> publicacoes;

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

    public List<Publicam> getPublicacoes() { return publicacoes; }
    public void setPublicacoes(List<Publicam> publicacoes) {
        this.publicacoes = publicacoes;
        if (publicacoes != null) {
            for (Publicam p : publicacoes) {
                p.setColaborador(this);
            }
        }
    }
}