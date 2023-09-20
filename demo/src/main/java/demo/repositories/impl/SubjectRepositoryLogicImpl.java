package demo.repositories.impl;

import demo.exceptions.GradesException;
import demo.repositories.Repository;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private List<Subject> subjects;

    public SubjectRepositoryLogicImpl() {
        Subject su1 = new Subject(1L,"Maths", "Auron");
        Subject su2 = new Subject(2L,"Phisics", "Rubius");
        Subject su3 = new Subject(3L,"Chemistry", "Rivers");
        subjects = new ArrayList<>(List.of(su1, su2, su3));
    }

    @Override
    public List<Subject> listar() {
        return subjects;
    }

    @Override
    public Subject porId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new GradesException("Subject not found"));
    }

    @Override
    public void guardar(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void eliminar(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}