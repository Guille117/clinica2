package portafolio.Clinica2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.MUsuario;


@Repository
public interface IMUsuarioRepository extends JpaRepository<MUsuario, Long>{
    public abstract MUsuario findByAliasUsuario(String aliasUsuario);
}
