package rw.stickynotes.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.stickynotes.v1.dtos.CreateNoteDto;
import rw.stickynotes.v1.payload.ApiResponse;
import rw.stickynotes.v1.services.INoteService;
import rw.stickynotes.v1.utils.Formatter;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final INoteService noteService;

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(ApiResponse.success(noteService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        return ResponseEntity.ok(ApiResponse.success(noteService.getAllNotes()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestParam(defaultValue = "",required = false) String title){
        return ResponseEntity.ok(ApiResponse.success(noteService.searchByTitle(title)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateNoteDto dto){
        return ResponseEntity.ok(ApiResponse.success(noteService.createNote(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") UUID id,@RequestBody CreateNoteDto dto){
        return ResponseEntity.ok(ApiResponse.success(noteService.updateNote(id,dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") UUID id){
        noteService.deleteNote(id);
        return Formatter.done();
    }



}
