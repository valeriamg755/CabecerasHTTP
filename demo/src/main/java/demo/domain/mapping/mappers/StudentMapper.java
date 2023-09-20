package demo.domain.mapping.mappers;

import demo.domain.mapping.dtos.StudentDto;
import demo.domain.models.Student;

import java.util.List;

public class StudentMapper {

    public static StudentDto mapFrom (Student source){
       return new StudentDto(source.getId_Student(),
               source.getName(),
               source.getEmail(),
               source.getSemester(),
               source.getCareer());
    }

    public static Student mapFrom (StudentDto source){
        return new Student(source.id_Student(),
                source.name(),
                source.email(),
                source.career(),
                source.semester());
    }

    public static List<StudentDto> mapFrom(List<Student> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }

}
