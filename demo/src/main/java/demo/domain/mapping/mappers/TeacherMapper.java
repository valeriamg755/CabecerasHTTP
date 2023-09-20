package demo.domain.mapping.mappers;



import demo.domain.mapping.dtos.TeacherDto;
import demo.domain.models.Teacher;

import java.util.List;

public class TeacherMapper {

    public static TeacherDto mapFrom(Teacher source){
        return new TeacherDto(source.getId(),
                source.getName(),
                source.getEmail());
    }

    public static Teacher mapFrom(Teacher source){
        return new Teacher(source.id_Teacher(),
                source.name(),
                source.email());
    }

    public static List<TeacherDto> mapFrom(List<Teacher> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}
