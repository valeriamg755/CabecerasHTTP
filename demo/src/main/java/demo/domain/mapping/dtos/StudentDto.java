package demo.domain.mapping.dtos;

public record StudentDto(Long id_Student,
                         String name,
                         String email,
                         String semester,
                         String career) {
}
