package lt.paulius.pentathloncompetition.rest;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PenthathlonResultsControllerTest extends PentathlonCompetitionApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getLeaderboard() throws Exception {
        this.mvc.perform(get("/api/leaderboard")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}