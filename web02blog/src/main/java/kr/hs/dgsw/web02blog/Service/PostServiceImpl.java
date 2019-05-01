package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Protocol.PostUserProtocol;
import kr.hs.dgsw.web02blog.Repository.PostRepository;
import kr.hs.dgsw.web02blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostUserProtocol addPost(Post post) {
        Post p = postRepository.save(post);
        return this.userRepository.findById(p.getUserId())
                .map(user -> {
                    return new PostUserProtocol(user, p);
                })
                .orElse(null);
    }

    @Override
    public List<Post> listPost(Long userId) {
        return postRepository.findAllByUserId(userId);

    }

    @Override
    public List<Post> listPost() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public boolean removePost(Long postId) {
        try {
            postRepository.deleteById(postId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Post modifyPost(Long postId, Post post) {

        return postRepository.findById(postId)
                .map(p -> {
                    p.setTitle(post.getTitle() != null ? post.getTitle() : p.getTitle());
                    p.setContent(post.getContent() != null ? post.getContent() : p.getContent());
                    return postRepository.save(p);
                })
                .orElse(null);
    }

    @Override
    public PostUserProtocol getPost(Long postId) {
        return postRepository.findById(postId)
                .map(post -> this.userRepository.findById(post.getUserId())
                        .map(user -> new PostUserProtocol(user, post))
                        .orElse(null))
                .orElse(null);
    }

    @Override
    public PostUserProtocol getPostByUser(Long userId) {
        return postRepository
                .findTopByUserIdOrderByIdDesc(userId)
                .map(post -> {
                    return this.userRepository.findById(post.getUserId())
                            .map(user -> new PostUserProtocol(user, post))
                            .orElse(null);
                })
                .orElse(null);
    }
}
