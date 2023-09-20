package demo.services;

import demo.domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> listar();
    Teacher byId(Long id);
    void guardar(Teacher t);
    void eliminar(Long id);
}
