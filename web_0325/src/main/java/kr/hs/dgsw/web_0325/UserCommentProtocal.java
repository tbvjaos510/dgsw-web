package kr.hs.dgsw.web_0325;

import java.util.List;

public class UserCommentProtocal {
    User user;
    List<Comment> comments;

    public UserCommentProtocal(User user, List<Comment> comments) {
        this.user = user;
        this.comments = comments;
    }
}
