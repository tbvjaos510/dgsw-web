package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_326.Repository.CommentRepository;
import kr.hs.dgsw.web_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Repository
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        User u = this.userRepository.save(new User("aaa", "aaa@dgsw","1234" , "E:\\Project\\Visual Studio 2017\\dgsw-web\\web_326\\upload\\img1.jpg", "img1.jpg"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111", "E:\\Project\\Visual Studio 2017\\dgsw-web\\web_326\\upload\\img2.jpg", "img2.jpg"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222","E:\\Project\\Visual Studio 2017\\dgsw-web\\web_326\\upload\\img2.jpg", "img2.jpg"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333","E:\\Project\\Visual Studio 2017\\dgsw-web\\web_326\\upload\\img2.jpg", "img2.jpg"));
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
    public CommentUsernameProtocol addComment(Comment comment) {
        Comment result =  commentRepository.save(comment);
        return this.userRepository.findById(result.getUserId())
                .map(c -> new CommentUsernameProtocol(comment, c.getUsername()))
                .orElse(null);
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
                    c.setOriginalName(comment.getOriginalName() != null ? comment.getOriginalName() : c.getOriginalName());
                    c.setStoredPath(comment.getStoredPath() != null ? comment.getStoredPath() : c.getStoredPath());
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
