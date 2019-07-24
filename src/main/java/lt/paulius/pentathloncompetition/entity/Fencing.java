package lt.paulius.pentathloncompetition.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fencing {

    private int victories;

    public Fencing(int victories) {
        this.victories = victories;
    }
}
