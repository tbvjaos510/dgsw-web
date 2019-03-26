package kr.hs.dgsw.web_326.Controller;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_326.Service.CommentService;
import kr.hs.dgsw.web_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/list")
    public List<CommentUsernameProtocol> listComments() {
        return this.commentService.listAllComments();
    }

    @GetMapping("/comment/{id}")
    public Comment getComment(@PathVariable Long id) {
        return this.commentService.getComment(id);
    }

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment) {
        return this.commentService.addComment(comment);
    }

    @DeleteMapping("/removeComment/{id}")
    public boolean removeComment(@PathVariable Long id) {
        return this.commentService.removeComment(id);
    }

    @PutMapping("/modifyComment/{id}")
    public Comment modifyComment(@PathVariable Long id, @RequestBody Comment comment) {
        return this.commentService.modifyComment(id, comment);
    }


}
