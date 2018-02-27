package is.hi.teymi9.gefins.model;

/**
 * Módel klasi fyrir athugasemdir (Comment)
 *
 * @author Ólöf
 * @version 1.0
 */


public class Comment {

    // unique auðkenni fyrir comment
    private int commentId;
    // höfundur, þ.e. sá sem skrifaði commentið
    private String username;
    // commentið sjálft
    private String comment;
    // auðkenni fyrir auglýsinguna sem commentið tilheyrir
    private int adId;

    /**
     * Smiður með viðföndum
     * @param commentId auðkenni comments
     * @param username notendanafn höfundar
     * @param comment commentið sjálft
     * @param adId auðkenni auglýsingar sem comment tilheyrir
     */
    public Comment(int commentId, String username, String comment, int adId) {
        this.commentId = commentId;
        this.username = username;
        this.comment = comment;
        this.adId = adId;
    }

    /**
     * Tómur smiður
     */
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
