package lt.paulius.pentathloncompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = { "finalScoreTime" })
public class Athlete {

    private String name;

    private Fencing fencing;

    private Swimming swimming;

    private Riding riding;

    private Shooting shooting;

    private Run run;

    private int totalScore;

    private String concludingEventTime;

    private String finalScoreTime;

    private String place;

    public Athlete(String name, Fencing fencing, Swimming swimming, Riding riding, Shooting shooting, Run run) {
        this.name = name;
        this.fencing = fencing;
        this.swimming = swimming;
        this.riding = riding;
        this.shooting = shooting;
        this.run = run;
    }
}
