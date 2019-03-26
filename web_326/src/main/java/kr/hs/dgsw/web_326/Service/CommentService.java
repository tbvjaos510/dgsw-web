package kr.hs.dgsw.web_326.Service;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {

    List<CommentUsernameProtocol> listAllComments();

    Comment addComment(Comment comment);

    boolean removeComment(Long commentId);

    Comment modifyComment(Long commentId, Comment comment);

    Comment getComment(Long commentId);
}
