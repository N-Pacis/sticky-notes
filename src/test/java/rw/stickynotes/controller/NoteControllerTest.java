package rw.stickynotes.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.stickynotes.v1.dtos.CreateNoteDto;
import rw.stickynotes.v1.models.Note;
import rw.stickynotes.v1.payload.ApiResponse;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success(){
        ResponseEntity<ApiResponse> notes = this.restTemplate.getForEntity("/api/v1/notes", ApiResponse.class);

        assertTrue(notes.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void createNote_Success(){
        CreateNoteDto dto = new CreateNoteDto("Testing note","Testing description");

        ResponseEntity<ApiResponse> response = this.restTemplate.postForEntity("/api/v1/notes",dto,ApiResponse.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
