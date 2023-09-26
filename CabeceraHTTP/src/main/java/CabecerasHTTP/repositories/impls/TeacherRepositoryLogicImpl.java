package CabecerasHTTP.repositories.impls;


import CabecerasHTTP.repositories.Repository;
import CabecerasHTTP.domain.model.Teacher;
import CabecerasHTTP.exceptions.GradesException;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<Teacher> {
    private List<Teacher> teachers;
    public TeacherRepositoryLogicImpl() {
        Teacher t1 = new Teacher(1L,"Violet", "1234@cue.edu.co");
        Teacher t2 = new Teacher(2L,"Xaden", "1234@cue.edu.co");
        Teacher t3 = new Teacher(3L,"Liam","1234@cue.edu.co");
        teachers = new ArrayList<>(List.of(t1, t2, t3));
    }
    @Override
    public List<Teacher> listar() {
        return teachers;
    }
    @Override
    public Teacher porId(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new GradesException("Teacher not found"));
    }
    @Override
    public void guardar(Teacher teacher) {
        teachers.add(teacher);
    }
    @Override
    public void eliminar(Long id) {
        teachers.removeIf(e->e.getId().equals(id));
    }
}
