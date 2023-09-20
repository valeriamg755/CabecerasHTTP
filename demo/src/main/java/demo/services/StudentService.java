package demo.services;

import demo.domain.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> listar();
    Student byId(Long id);
    void guardar(Student t);
    void eliminar(Long id);
}
