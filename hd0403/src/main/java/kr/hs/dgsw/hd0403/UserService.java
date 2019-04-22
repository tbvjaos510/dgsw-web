package kr.hs.dgsw.hd0403;

import org.springframework.stereotype.Service;

public interface UserService {
    User login(String id, String password);
    User register(User user);
    User update(String id, User user);
    boolean delete(String id);
}
