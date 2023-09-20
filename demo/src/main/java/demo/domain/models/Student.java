package demo.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Student {

    private Long id;
    private String name;
    private String email;
    private String semester;

}
