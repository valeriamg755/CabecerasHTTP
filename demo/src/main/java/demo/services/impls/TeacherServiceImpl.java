package demo.services.impls;

import demo.domain.models.Teacher;
import demo.repositories.impl.TeacherRepositoryLogicImpl;
import demo.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryLogicImpl repository;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Teacher> listar() {
        return repository.listar();
    }

    @Override
    public Teacher porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Teacher t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
