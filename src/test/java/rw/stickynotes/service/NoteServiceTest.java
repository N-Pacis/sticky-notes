package rw.stickynotes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.stickynotes.v1.dtos.CreateNoteDto;
import rw.stickynotes.v1.exceptions.ResourceNotFoundException;
import rw.stickynotes.v1.models.Note;
import rw.stickynotes.v1.repositories.INoteRepository;
import rw.stickynotes.v1.serviceImpls.NoteServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @Mock
    private INoteRepository noteRepositoryMock;

    @InjectMocks
    private NoteServiceImpl noteService;

    @Test
    public void should_create_note_success() {
        String title = "Testing title";
        String description = "testing description";

        Note noteToCreate = new Note(title,description);
        when(noteRepositoryMock.save(any(Note.class))).thenReturn(noteToCreate);

        Note actualNote = noteService.createNote(new CreateNoteDto());

        assertThat(actualNote).usingRecursiveComparison().isEqualTo(noteToCreate);
    }

    @Test
    public void should_update_note_success(){
        String title = "testing title";
        String description = "testing description";
        UUID id = UUID.randomUUID();
        CreateNoteDto createNoteDto = new CreateNoteDto(title,description);
        Note note = new Note(id,title,description);

        when(noteRepositoryMock.findById(id)).thenReturn(Optional.of(note));
        Note actualNote = noteService.updateNote(id,createNoteDto);

        assertThat(actualNote).usingRecursiveComparison().isEqualTo(note);
    }

    @Test
    public void should_update_note_failure_404(){
        String title = "testing title";
        String description = "testing description";
        UUID id = UUID.randomUUID();
        CreateNoteDto createNoteDto = new CreateNoteDto(title,description);

        when(noteRepositoryMock.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,()->noteService.updateNote(id,createNoteDto));
    }

    @Test
    public void should_delete_note_success(){
        UUID id = UUID.randomUUID();
        Note note = new Note("testing title","testing description");
        when(noteRepositoryMock.findById(id)).thenReturn(Optional.of(note));

        noteService.deleteNote(id);
    }

    @Test
    public void should_get_all_students_success(){
        when(noteRepositoryMock.findAll()).thenReturn(List.of(new Note(),new Note()));

        assertThat(noteService.getAllNotes()).hasSize(2);
    }
}
