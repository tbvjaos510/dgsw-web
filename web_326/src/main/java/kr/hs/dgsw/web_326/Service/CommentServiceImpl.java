package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_326.Repository.CommentRepository;
import kr.hs.dgsw.web_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("aaa", "aaa@dgsw"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = found.map(User::getUsername).orElse(null);
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean removeComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Comment modifyComment(Long commentId, Comment comment) {
        return this.commentRepository.findById(commentId)
                .map(c -> {
                    c.setContent(comment.getContent() != null ? comment.getContent() : c.getContent());
                    c.setUserId(comment.getUserId() != null ? comment.getUserId() : c.getUserId());
                    return this.commentRepository.save(c);
                })
                .orElse(null);
    }

    @Override
    public CommentUsernameProtocol getComment(Long userId) {
        return this.commentRepository.findById(userId)
                .map(comment -> this.userRepository.findById(comment.getUserId())
                        .map(user -> new CommentUsernameProtocol(comment, user.getUsername()))
                        .orElse(null)).orElse(null);
    }
}
