package rw.stickynotes.v1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rw.stickynotes.v1.audits.TimestampAudit;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note extends TimestampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String description;

    public Note(String title, String description){
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
