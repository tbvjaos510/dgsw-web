package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping("/user/get/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user/add")
    public User addPost(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/user/remove/{userId}")
    public boolean removeUser(@PathVariable Long userId) {
        return userService.removeUser(userId);
    }

    @PutMapping("/user/modify/{userId}")
    public User modifyUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.modifyUser(userId, user);
    }

}
