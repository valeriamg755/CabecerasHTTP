package CabecerasHTTP.domain.mapping.dto;

import CabecerasHTTP.domain.model.Student;
import CabecerasHTTP.domain.model.Subject;

public record GradesDto(Long idGrades, Student student, Subject subject, double corte){}