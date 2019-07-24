package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplication;
import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;

import lt.paulius.pentathloncompetition.service.api.FileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVFileReaderTest extends PentathlonCompetitionApplicationTests {

    @Test
    public void shouldReadLinesFromFile() {
        int expectedLines = 10;

        FileReader fileReader = new CSVFileReader();

        List<List<String>> fileContent = fileReader.readFile(PentathlonCompetitionApplication.FILE_PATH);

        assertEquals(expectedLines, fileContent.size());
    }
}