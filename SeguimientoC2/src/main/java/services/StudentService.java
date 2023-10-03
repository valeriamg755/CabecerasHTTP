package services;

import domain.mapping.dto.StudentDto;
import domain.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> list();
    StudentDto byId(Long id);
    void save(StudentDto t);
    void delete(Long id);
}
