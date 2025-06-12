package sistemainfantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemainfantil.entity.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
