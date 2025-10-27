package acceso.datos.games.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private long id;
    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private LocalDate registerDate;
}
