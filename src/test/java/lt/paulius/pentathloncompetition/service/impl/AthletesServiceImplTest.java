package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;
import lt.paulius.pentathloncompetition.entity.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthletesServiceImplTest extends PentathlonCompetitionApplicationTests {

    private AthletesServiceImpl service;

    @Before
    public void setUp() {
        service = new AthletesServiceImpl(new CSVFileReader());
    }

    @Test
    public void calculatesResults() {
        service.init();
        service.calculateResults();

        Athlete athlete = service.getAthletes().get(0);

        assertEquals(4704, athlete.getTotalScore());
        assertEquals("00:00.0", athlete.getConcludingEventTime());
        assertEquals("10:38.0", athlete.getFinalScoreTime());
        assertEquals("1", athlete.getPlace());
    }
}