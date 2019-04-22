package kr.hs.dgsw.hd0403;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String id;

    private String username;
    private String password;
    private String email;
}
