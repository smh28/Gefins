package is.hi.teymi9.gefins.model;

/**
 * Created by oloff 27.02.2018
 */


public class Comment {

    private int commentId;
    private String username;
    private String comment;
    private int adId;


    public Comment(int commentId, String username, String comment, int adId) {
        this.commentId = commentId;
        this.username = username;
        this.comment = comment;
        this.adId = adId;
    }

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
}
