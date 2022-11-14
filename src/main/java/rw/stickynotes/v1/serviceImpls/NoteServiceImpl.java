package rw.stickynotes.v1.serviceImpls;

import org.springframework.stereotype.Service;
import rw.stickynotes.v1.dtos.CreateNoteDto;
import rw.stickynotes.v1.exceptions.ResourceNotFoundException;
import rw.stickynotes.v1.models.Note;
import rw.stickynotes.v1.repositories.INoteRepository;
import rw.stickynotes.v1.services.INoteService;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements INoteService {

    private final INoteRepository noteRepository;

    public NoteServiceImpl(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(UUID id) {
        return noteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Note","id",id));
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> searchByTitle(String title) {
        return noteRepository.findByTitleContains(title);
    }

    @Override
    public Note createNote(CreateNoteDto dto) {
        Note note = new Note(dto.getTitle(),dto.getDescription());
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(UUID id, CreateNoteDto dto) {
        Note note = findById(id);
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        return note;
    }

    @Override
    public void deleteNote(UUID id) {
        Note note = findById(id);
        noteRepository.delete(note);
    }
}
