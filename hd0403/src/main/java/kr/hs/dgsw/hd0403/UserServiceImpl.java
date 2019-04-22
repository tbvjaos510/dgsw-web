package kr.hs.dgsw.hd0403;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User login(String id, String password) {
        return userRepository.findById(id)
                .map(u -> {
                    if (u.getPassword().equals(password)) {
                        return u;
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public User register(User user) {
        return (User) userRepository.findById(user.getId())
                .map(u -> null)
                .orElse(userRepository.save(user));
    }

    @Override
    public User update(String id, User user) {
        return userRepository.findById(id)
                .map(u -> {
                    if (user.getPassword() != null)
                        u.setPassword(user.getPassword());
                    if (user.getEmail() != null)
                        u.setEmail(user.getEmail());
                    if (user.getUsername() != null)
                        u.setUsername(user.getUsername());
                    return userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(String id) {
        return userRepository.findById(id)
                .map(u -> {
                    userRepository.delete(u);
                    return true;
                })
                .orElse(false);
    }
}
