package lt.paulius.pentathloncompetition.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Run {

    private String time;

    public Run(String time) {
        this.time = time;
    }
}
