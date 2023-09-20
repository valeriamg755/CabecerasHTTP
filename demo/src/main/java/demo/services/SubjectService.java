package demo.services;

import javax.security.auth.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> listar();
    Subject byId(Long id);
    void guardar(Subject t);
    void eliminar(Long id);
}
