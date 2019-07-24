package lt.paulius.pentathloncompetition.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Riding {

    private int knockingDown;

    private int refusal;

    private int disobedienceLeading;

    public Riding(int knockingDown, int refusal, int disobedienceLeading) {
        this.knockingDown = knockingDown;
        this.refusal = refusal;
        this.disobedienceLeading = disobedienceLeading;
    }
}
