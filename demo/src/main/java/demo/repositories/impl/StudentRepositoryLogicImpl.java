package demo.repositories.impl;

import demo.domain.models.Student;
import demo.exceptions.GradesException;
import demo.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<Student> {
    private List<Student> students;

    public StudentRepositoryLogicImpl() {
        Student s1 = new Student(1L, "Dokja", "1234@cue.edu.co", "Tercero");
        Student s2 = new Student(2L, "Joonghyuk", "1234@cue.edu.co", "Segundo");
        Student s3 = new Student(3L, "Sooyoung", "1234@cue.edu.c", "Primero");
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<Student> listar() {
        return students;
    }

    @Override
    public Student porId(Long id) {
        return students.stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElseThrow(() -> new GradesException("Student not found"));
    }

    @Override
    public void guardar(Student student) {
        students.add(student);
    }

    @Override
    public void eliminar(Long id) {
        students.removeIf(e -> e.getId().equals(id));
    }
}