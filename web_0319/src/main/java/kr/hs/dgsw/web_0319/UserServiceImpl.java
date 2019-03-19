package kr.hs.dgsw.web_0319;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> userLIst;

    public UserServiceImpl() {
        this.userLIst = new ArrayList<>();
        this.userLIst.add(new User(1, "user1", "user111@dgsw"));
        this.userLIst.add(new User(2, "user2", "user222@dgsw"));
        this.userLIst.add(new User(3, "user3", "user333@dgsw"));
    }
    @Override
    public List<User> list() {
        return this.userLIst;
    }

    @Override
    public User view(int id) {
        User found = this.userLIst.stream()
                .filter(user -> user.getId() == id)
                .findAny()
                .orElse(null);
        return found;
    }

    @Override
    public boolean add(User user) {
        User found = this.view(user.getId());
        if (found == null)
            return this.userLIst.add(user);
        return false;
    }

    @Override
    public User update(User user) {
        User found = view(user.getId());
        if (found != null) {
            found.setEmail(user.getEmail());
            found.setName(user.getName());
        }
        return found;
    }

    @Override
    public boolean delete(int id) {
        User found = this.view(id);
        return this.userLIst.remove(found);
    }
}
