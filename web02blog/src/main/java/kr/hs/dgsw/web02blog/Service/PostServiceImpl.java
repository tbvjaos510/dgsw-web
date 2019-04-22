package kr.hs.dgsw.web02blog.Service;

import kr.hs.dgsw.web02blog.Domain.Post;
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
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> listPost(Long userId) {
        return postRepository.findAllByUserId(userId);

    }

    @Override
    public List<Post> listPost() {
        return postRepository.findAll();
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
                    p.setContent(post.getContent() != null ? post.getContent() : p.getContent());
                    return postRepository.save(p);
                })
                .orElse(null);
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElse(null);
    }
}
