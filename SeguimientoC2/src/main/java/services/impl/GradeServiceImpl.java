package services.impl;

import domain.mapping.dto.GradeDto;
import repositories.Repository;
import repositories.impls.normal.GradeRepositoryImpl;
import services.GradeService;

import java.sql.Connection;
import java.util.List;

public class GradeServiceImpl implements GradeService {
    private Repository<GradeDto> repo;

    public GradeServiceImpl(Connection connection) {
        this.repo = new GradeRepositoryImpl(connection);
    }

    @Override
    public List<GradeDto> list() {
        return repo.list();
    }

    @Override
    public GradeDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(GradeDto grades) {
        repo.update(grades);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
