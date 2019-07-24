package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.service.api.FileReader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVFileReader implements FileReader {

    public static final String COMMA_DELIMITER = ",";

    @Override
    public List<List<String>> readFile(String filePath) {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}
