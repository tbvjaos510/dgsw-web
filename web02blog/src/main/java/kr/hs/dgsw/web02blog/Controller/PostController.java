package kr.hs.dgsw.web02blog.Controller;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserProtocol;
import kr.hs.dgsw.web02blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web02blog.Protocol.ResponseType;
import kr.hs.dgsw.web02blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {


    @Autowired
    private PostService postService;

    @GetMapping("/post/list")
    public ResponseFormat listPost() {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        List<Post> postList = postService.listPost();
        if (postList != null) {
            rf = new ResponseFormat(
                    ResponseType.POST_LIST,
                    postList,
                    (long) postList.size()
            );
        }
        return rf;
    }

    @GetMapping("/post/get/{postId}")
    public ResponseFormat getPost(@PathVariable Long postId) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        Post post = postService.getPost(postId);
        if (post != null) {
            rf = new ResponseFormat(
                    ResponseType.POST_GET,
                    post,
                    post.getId()
            );
        }
        return rf;
    }

    @GetMapping("/post/user/{userId}")
    public ResponseFormat getTopPost(@PathVariable Long userId) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        Post post = postService.getPostByUser(userId);
        if (post != null) {
            rf = new ResponseFormat(
                    ResponseType.POST_GET,
                    post,
                    post.getId()
            );
        }
        return rf;
    }
//
//    @GetMapping("/post/list/{userId}")
//    public List<Post> listPostByUserId(@PathVariable Long userId) {
//        return postService.listPost(userId);
//    }

    @PostMapping("/post/add")
    public ResponseFormat addPost(@RequestBody Post post) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        PostUserProtocol p = postService.addPost(post);
        if (p != null) {
            rf = new ResponseFormat(
                    ResponseType.POST_ADD,
                    p,
                    p.getId()
            );
        }
        return rf;
    }

    @DeleteMapping("/post/remove/{postId}")
    public ResponseFormat removePost(@PathVariable Long postId) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);

        if (postService.removePost(postId)) {
            rf = new ResponseFormat(
                    ResponseType.POST_DELETE,
                    true,
                    postId
            );
        }
        return rf;
    }

    @PutMapping("/post/modify/{postId}")
    public ResponseFormat modifyPost(@PathVariable Long postId, @RequestBody Post post) {
        ResponseFormat rf = new ResponseFormat(ResponseType.FAIL, null);
        Post p = postService.modifyPost(postId, post);
        if (p != null) {
            rf = new ResponseFormat(
                    ResponseType.POST_UPDATE,
                    p,
                    p.getId()
            );
        }
        return rf;
    }
}
