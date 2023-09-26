package CabecerasHTTP.domain.mapping.mappers;

import CabecerasHTTP.domain.mapping.dto.StudentDto;
import CabecerasHTTP.domain.model.Student;

import java.util.List;
public class StudentMapper {
    public static StudentDto mapFrom (Student source){
       return new StudentDto(source.getId(),
               source.getName(),
               source.getEmail(),
               source.getSemester());
    }
    public static Student mapFrom (StudentDto source){
        return new Student(source.idStudent(),
                source.name(),
                source.email(),
                source.semester());
    }
    public static List<StudentDto> mapFrom(List<Student> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}
