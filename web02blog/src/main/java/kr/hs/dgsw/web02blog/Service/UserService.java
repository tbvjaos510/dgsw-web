package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.User;

import java.util.List;

public interface UserService {
    User login(String account, String password);
    User addUser(User user);
    boolean removeUser(Long userId);
    User modifyUser(Long userId, User user);
    List<User> listUser();
    User getUser(Long userId);
}
