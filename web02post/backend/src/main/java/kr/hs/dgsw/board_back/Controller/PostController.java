package kr.hs.dgsw.board_back.Controller;

import kr.hs.dgsw.board_back.Domain.Post;
import kr.hs.dgsw.board_back.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/api/getBoards")
    public List board() {
        return this.postService.findAll();
    }

    @GetMapping(value = "/api/getBoardUserId/{userId}")
    public List findByUserId(@PathVariable Long userId){
        return this.postService.findByUserId(userId);
    }

    @GetMapping(value = "/api/getBoardId/{id}")
    public HashMap findById(@PathVariable Long id){
        return this.postService.findById(id);
    }

    @PostMapping(value = "/api/postBoard")
    public Long add(@RequestBody Post post){
        return this.postService.add(post);
    }

    @PostMapping(value = "/api/postBoardHash")
    public int addWithHashmap(@RequestBody Post post){
        return this.postService.addWithHashmap(post);
    }

    @PutMapping(value = "/api/putBoard")
    public int modify(@RequestBody Post post){
        return this.postService.modify(post);
    }

    @DeleteMapping(value = "/api/deleteBoard/{id}")
    public int deleteById(@PathVariable Long id){
        return this.postService.deleteById(id);
    }

}
