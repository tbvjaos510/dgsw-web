package kr.hs.dgsw.web_326.Domain;

import kr.hs.dgsw.web_326.Protocol.AttachmentProtocol;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    @CreationTimestamp
    private LocalDateTime joined;

    @UpdateTimestamp
    private LocalDateTime modified;

    private String storedPath;
    private String originalName;


    public User(String username, String email,String password , String storedPath, String originalName) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.storedPath = storedPath;
        this.originalName = originalName;
    }

}
