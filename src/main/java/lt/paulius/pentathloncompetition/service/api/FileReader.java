package lt.paulius.pentathloncompetition.service.api;

import java.util.List;

public interface FileReader {

    List<List<String>> readFile(String filePath);
}
