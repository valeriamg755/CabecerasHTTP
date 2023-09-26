package CabecerasHTTP.services;

import CabecerasHTTP.domain.mapping.dto.StudentDto;
import CabecerasHTTP.domain.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();
    List<Student> listar();
    Student porId(Long id);
    void guardar(Student t);
    void eliminar(Long id);
}