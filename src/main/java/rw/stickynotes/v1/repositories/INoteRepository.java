package rw.stickynotes.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.stickynotes.v1.models.Note;

import java.util.List;
import java.util.UUID;

@Repository
public interface INoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findByTitleContains(String title);
}
