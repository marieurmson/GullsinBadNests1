package models;

import java.io.Serializable;

public class PostModel implements Serializable {
    private int postId = 0;
    private String post;
    private String username;
    private int commentOnPostID;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentOnPostID() {
        return commentOnPostID;
    }

    public void setCommentOnPostID(int commentOnPostID) {
        this.commentOnPostID = commentOnPostID;
    }
}