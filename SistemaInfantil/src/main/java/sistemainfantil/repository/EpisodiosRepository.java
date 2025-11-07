package sistemainfantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemainfantil.entity.Episodios;

public interface EpisodiosRepository extends JpaRepository<Episodios, Long> {
}
