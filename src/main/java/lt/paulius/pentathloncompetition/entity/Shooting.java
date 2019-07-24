package lt.paulius.pentathloncompetition.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shooting {

    private int targetScore;

    public Shooting(int targetScore) {
        this.targetScore = targetScore;
    }
}
