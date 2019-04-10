package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Protocol.CommentUsernameProtocol;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    List<CommentUsernameProtocol> listAllComments();

    CommentUsernameProtocol addComment(Comment comment);

    boolean removeComment(Long commentId);

    Comment modifyComment(Long commentId, Comment comment);

    Comment getComment(Long commentId);
}
