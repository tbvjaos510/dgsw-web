package kr.hs.dgsw.web02blog.Protocol;

import kr.hs.dgsw.web02blog.Domain.Post;
import kr.hs.dgsw.web02blog.Domain.User;
import lombok.Data;

@Data
public class PostUserProtocol extends Post {
    private User writer;

    public PostUserProtocol(User user, Post post) {
        this.writer = user;
        this.setId(post.getId());
        this.setContent(post.getContent());
        this.setCreated(post.getCreated());
        this.setModified(post.getModified());
        this.setPictures(post.getPictures());
        this.setUserId(post.getUserId());
        this.setTitle(post.getTitle());
    }
}
