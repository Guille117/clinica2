package portafolio.Clinica2.servicio;

import java.util.List;

public interface IGenericService<T, Q> {
    void Sa(T t);
    T getOne(Long id);
    List<T> getAll();
    void Up(Q q);
    void De(Long id);
}
