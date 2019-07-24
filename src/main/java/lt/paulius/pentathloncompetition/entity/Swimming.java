package lt.paulius.pentathloncompetition.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Swimming {

    private String time;

    public Swimming(String time) {
        this.time = time;
    }
}
