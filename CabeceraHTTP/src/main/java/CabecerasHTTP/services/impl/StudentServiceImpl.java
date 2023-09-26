package CabecerasHTTP.services.impl;

import CabecerasHTTP.domain.mapping.dto.StudentDto;
import CabecerasHTTP.domain.model.Student;
import CabecerasHTTP.repositories.impls.StudentRepositoryLogicImpl;
import CabecerasHTTP.services.StudentService;

import java.util.List;
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repository;

    public StudentServiceImpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDto> list() {
        return null;
    }

    @Override
    public List<Student> listar() {
        return repository.listar();
    }

    @Override
    public Student porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Student t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
