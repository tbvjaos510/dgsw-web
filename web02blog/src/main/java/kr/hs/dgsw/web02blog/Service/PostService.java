package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserProtocol;

import java.util.List;

public interface PostService {
    PostUserProtocol addPost(Post post);

    List<Post> listPost(Long userId);

    List<Post> listPost();

    boolean removePost(Long postId);

    Post modifyPost(Long postId, Post post);

    PostUserProtocol getPost(Long postId);

    Post getPostByUser(Long userid);
}
