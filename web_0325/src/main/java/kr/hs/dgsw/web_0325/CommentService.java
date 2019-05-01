package kr.hs.dgsw.web_0325;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comment> list() {
        return this.commentRepository.findAll();
    }

    public Comment add(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public Comment view(Long id) {
        return this.commentRepository.findById(id)
                .orElse(null);
    }

    public Comment update(Long id, Comment comment) {
        return this.commentRepository.findById(id)
                .map(c -> {
                    c.setComment(Optional.ofNullable(comment.getComment()).orElse(c.getComment()));
                    c.setUserId(Optional.ofNullable(comment.getUserId()).orElse(c.getUserId()));
                    return this.commentRepository.save(comment);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        try {
            this.commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Comment> viewByUser(String username) {
        return this.userRepository.findByUsername(username)
                .map(user -> this.commentRepository.findByUserId(user.getId()))
                .orElse(null);
    }

    public UserCommentProtocal findByUser(Long userId) {
        return this.userRepository.findById(userId)
                .map(user -> new UserCommentProtocal(user, this.commentRepository.findByUserId(user.getId())))
                .orElse(null);
    }
}
