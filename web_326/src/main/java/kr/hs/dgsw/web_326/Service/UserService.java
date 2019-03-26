package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User modifyUser(Long userId, User user);

    boolean removeUser(Long userId);

    List<User> listUser();
}
