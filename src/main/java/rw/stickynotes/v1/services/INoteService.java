package rw.stickynotes.v1.services;

import rw.stickynotes.v1.dtos.CreateNoteDto;
import rw.stickynotes.v1.models.Note;

import java.util.List;
import java.util.UUID;

public interface INoteService {
    Note findById(UUID id);

    List<Note> getAllNotes();

    List<Note> searchByTitle(String title);

    Note createNote(CreateNoteDto dto);

    Note updateNote(UUID id, CreateNoteDto dto);

    void deleteNote(UUID id);
}
