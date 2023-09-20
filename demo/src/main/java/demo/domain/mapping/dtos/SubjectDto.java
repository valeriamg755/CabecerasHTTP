package demo.domain.mapping.dtos;

import demo.domain.models.Teacher;


public record SubjectDto(Long id_Subject,
                         String name,
                         Teacher teacher) {
}
