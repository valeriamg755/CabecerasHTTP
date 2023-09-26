package CabecerasHTTP.repositories.impls;

import CabecerasHTTP.repositories.Repository;
import CabecerasHTTP.domain.model.Subject;
import CabecerasHTTP.exceptions.GradesException;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private List<Subject> subjects;
    public SubjectRepositoryLogicImpl() {
        Subject su1 = new Subject(1L,"History", "Violet");
        Subject su2 = new Subject(2L,"Physics", "Xaden");
        Subject su3 = new Subject(3L,"Chemistry", "Liam");
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
