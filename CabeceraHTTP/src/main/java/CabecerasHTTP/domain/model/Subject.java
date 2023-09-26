package CabecerasHTTP.domain.model;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Subject {
    private Long id;
    private String name;
    private String teacher;
}