package sistemainfantil.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Episodios")
public class Episodios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episodios")
    private Long id;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Url")
    private String url;

    // Relacionamento com "Contem"
    @OneToMany(mappedBy = "episodio", cascade = CascadeType.ALL)
    private List<Contem> contemList;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public List<Contem> getContemList() { return contemList; }
    public void setContemList(List<Contem> contemList) { this.contemList = contemList; }
}
