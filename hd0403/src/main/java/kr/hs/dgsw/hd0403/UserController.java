package kr.hs.dgsw.hd0403;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getId(), user.getPassword());
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/remove/{id}")
    public boolean delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
