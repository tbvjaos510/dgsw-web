package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return (User) this.userRepository.findByEmail(user.getEmail())
                .map(u -> null)
                .orElse(this.userRepository.save(user));

    }

    @Override
    public User modifyUser(Long userId, User user) {
        return this.userRepository.findById(userId)
                .map(u-> {
                    u.setUsername(user.getUsername() != null ? user.getUsername() : u.getUsername());
                    u.setEmail(user.getEmail() != null ? user.getEmail() : u.getEmail());
                    return this.userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public boolean removeUser(Long userId){
        try {
            this.userRepository.deleteById(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> listUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User addProfile(Long userId, AttachmentProtocol attach) {
        return this.userRepository.findById(userId)
                .map(u -> {
                    String pastPath = u.getStoredPath();
                    if (pastPath != null)
                    {
                        File file = new File(u.getStoredPath());

                        file.deleteOnExit();
                    }
                    u.setOriginalName(attach.getOriginalName());
                    u.setStoredPath(attach.getStoredPath());
                    return this.userRepository.save(u);
                })
                .orElse(null);
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id)
                .orElse(null);
    }

    public User login(User user) {
        return this.userRepository.findByUsername(user.getUsername())
                .map(u -> {
                    if (u.getPassword().equals(user.getPassword()))
                        return u;
                    else return null;
                })
                .orElse(null);
    }
}
