package kr.hs.dgsw.web_20190424_2.Service;

import kr.hs.dgsw.web_20190424_2.Domain.User;
import kr.hs.dgsw.web_20190424_2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateUser(String id, User user) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setEmail(user.getEmail() != null ? user.getEmail() : u.getEmail());
                    return userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public User createUser(User user) {
        return (User) userRepository.findById(user.getId())
                .map(u -> null)
                .orElse(userRepository.save(user));
    }
}
