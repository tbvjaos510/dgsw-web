package kr.hs.dgsw.web_20190424_2.Service;

import kr.hs.dgsw.web_20190424_2.Domain.User;

public interface UserService {
    User updateUser(String id, User user);

    User createUser(User user);
}
