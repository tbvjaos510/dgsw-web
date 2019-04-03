package kr.hs.dgsw.web_326.Controller;

import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @PutMapping("/modifyUser/{id}")
    public User modifyUser(@PathVariable Long id, @RequestBody User user) {
        return this.userService.modifyUser(id, user);
    }

    @PostMapping("/addProfile/{id}")
    public User addProfile(@PathVariable Long id, @RequestBody AttachmentProtocol attach) {
        return this.userService.addProfile(id, attach);
    }

    @DeleteMapping("/removeUser/{id}")
    public Boolean removeUser(@PathVariable Long id) {
        return this.userService.removeUser(id);
    }

    @GetMapping("/user")
    public List<User> listUser() {
        return this.userService.listUser();
    }
}
