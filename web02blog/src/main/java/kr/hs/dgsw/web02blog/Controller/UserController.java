package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.User;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public ResponseFormat listUser() {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        List<User> userList = userService.listUser();
        if (userList != null) {
            rf = new ResponseFormat(
                    ResponseType.USER_LIST,
                    userList,
                    (long) userList.size()
            );
        }
        return rf;
    }

    @GetMapping("/user/get/{userId}")
    public ResponseFormat getUser(@PathVariable Long userId) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        User user = userService.getUser(userId);
        if (user != null) {
            rf = new ResponseFormat(
                    ResponseType.USER_GET,
                    user,
                    user.getId()
            );
        }
        return rf;
    }

    @PostMapping("/user/add")
    public ResponseFormat addPost(@RequestBody User user) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        User u = userService.addUser(user);
        if (u != null) {
            rf = new ResponseFormat(
                    ResponseType.USER_ADD,
                    u,
                    u.getId()
            );
        }
        return rf;
    }

    @DeleteMapping("/user/remove/{userId}")
    public ResponseFormat removeUser(@PathVariable Long userId) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        if (userService.removeUser(userId)) {
            rf = new ResponseFormat(
                    ResponseType.USER_DELETE,
                    true,
                    userId
            );
        }
        return rf;
    }

    @PutMapping("/user/modify/{userId}")
    public ResponseFormat modifyUser(@PathVariable Long userId, @RequestBody User user) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        User u = userService.modifyUser(userId, user);
        if (u != null) {
            rf = new ResponseFormat(
                    ResponseType.USER_UPDATE,
                    u,
                    u.getId()
            );
        }
        return rf;
    }

}
