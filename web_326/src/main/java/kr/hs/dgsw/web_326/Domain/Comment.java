package kr.hs.dgsw.web_326.Domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String content;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(Comment c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.created = c.getCreated();
        this.modified = c.getModified();
        this.storedPath = c.getStoredPath();
        this.originalName = c.getOriginalName();
    }

    public Comment(Long userId, String content, String storedPath, String originalName) {
        this.userId = userId;
        this.content = content;
        this.storedPath = storedPath;
        this.originalName = originalName;
    }


    private String originalName;
    private String storedPath;

    public Comment() {

    }
}
