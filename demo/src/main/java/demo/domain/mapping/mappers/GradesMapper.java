package demo.domain.mapping.mappers;


import demo.domain.mapping.dtos.GradesDto;
import demo.domain.models.Grades;

import java.util.List;

public class GradesMapper {
    public static GradesDto mapFrom(Grades source) {
        return new GradesDto(source.getId_Grades(),
                source.getStudent(),
                source.getSubject(),
                source.getTerm());
    }

    public static Grades mapFrom(GradesDto source){
        return new Grades(source.id_Grades(),
                source.student(),
                source.subject(),
                source.corte());
    }

    public static List<GradesDto> mapFrom(List<Grades> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}
