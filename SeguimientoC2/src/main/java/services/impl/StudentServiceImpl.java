package services.impl;

import domain.mapping.dto.StudentDto;
import repositories.Repository;
import services.StudentService;

import java.sql.Connection;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private Repository<StudentDto> repo;
    public StudentServiceImpl(Connection connection) {
        this.repo = new StudentRepositoryJDBCImpl(connection);
    }

    @Override
    public List<StudentDto> list() {
        return repo.list();
    }

    @Override
    public StudentDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(StudentDto student) {
        repo.save(student);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
