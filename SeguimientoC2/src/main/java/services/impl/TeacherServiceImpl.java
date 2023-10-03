package services.impl;

import domain.mapping.dto.TeacherDto;
import repositories.Repository;
import repositories.impls.normal.TeacherRepositoryImpl;
import services.TeacherService;

import java.sql.Connection;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private Repository<TeacherDto> repo;
    public TeacherServiceImpl(Connection connection) {
        this.repo = new TeacherRepositoryImpl(connection);
    }

    @Override
    public List<TeacherDto> list() {
        return repo.list();
    }

    @Override
    public TeacherDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(TeacherDto teacher) {
        repo.save(teacher);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
