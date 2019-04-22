package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    List<Post> listPost(Long userId);
    List<Post> listPost();
    boolean removePost(Long postId);
    Post modifyPost(Long postId, Post post);
    Post getPost(Long postId);
}
