package CabecerasHTTP.domain.mapping.mappers;

import CabecerasHTTP.domain.mapping.dto.GradesDto;
import CabecerasHTTP.domain.model.Grades;

import java.util.List;

public class GradesMapper {
    public static GradesDto mapFrom(Grades source) {
        return new GradesDto(source.getId(),
                source.getStudent(),
                source.getSubject(),
                source.getGrade());
    }
    public static Grades mapFrom(GradesDto source){
        return new Grades(source.idGrades(),
                source.student(),
                source.subject(),
                source.corte());
    }
    public static List<GradesDto> mapFrom(List<Grades> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}