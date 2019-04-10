package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.AttachmentProtocol;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User modifyUser(Long userId, User user);

    boolean removeUser(Long userId);

    User addProfile(Long userId, AttachmentProtocol attach);

    List<User> listUser();

    User getUser(Long userId);

    User login(User user);
}
