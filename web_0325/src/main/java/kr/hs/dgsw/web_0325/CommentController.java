package kr.hs.dgsw.web_0325;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    @GetMapping("/list")
    public List<Comment> list() {
        return this.service.list();
    }

    @PostMapping("/add")
    public Comment add(@RequestBody Comment comment) {
        return this.service.add(comment);
    }
    @GetMapping("/view/{id}")
    public Comment view(@PathVariable Long id) {
        return this.service.view(id);
    }

    @PutMapping("/update/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        return this.service.update(id, comment);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

    @GetMapping("/viewbyuser/{username}")
    public List<Comment> viewByUser(@PathVariable String username) {
        return this.viewByUser(username);
    }

    @GetMapping("/findbyuser/{userId}")
    public UserCommentProtocal findByUser(@PathVariable Long userId) {
        return this.findByUser(userId);
    }
}
