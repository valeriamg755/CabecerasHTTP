package domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Teacher {
    private Long id;
    private String name;
    private String email;
}
