package demo.domain.mapping.dtos;

import demo.domain.models.Student;

import javax.security.auth.Subject;

public record GradesDto(Long id_Grades,
                       Student student,
                       Subject subject,
                       String corte){

}
