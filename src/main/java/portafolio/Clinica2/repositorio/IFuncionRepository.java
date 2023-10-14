package portafolio.Clinica2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.Funcion;


@Repository
public interface IFuncionRepository extends JpaRepository<Funcion, Long>{
    public abstract Funcion findByNombreFuncion(String nombreFuncion);
    
}
