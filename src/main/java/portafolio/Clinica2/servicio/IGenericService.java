package portafolio.Clinica2.servicio;

import java.util.List;

public interface IGenericService<T> {
    void Sa(T t);
    T getOne(Long id);
    List<T> getAll();
    void De(Long id);
}
