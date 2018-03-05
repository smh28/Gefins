package is.hi.teymi9.gefins.model;

import java.util.UUID;

/**
 * Módel klasi fyrir athugasemdir (Comment)
 *
 * @author Ólöf Fríða
 * @version 1.0
 */


public class Comment {

    // unique auðkenni fyrir comment
    private UUID commentId = UUID.randomUUID();
    // höfundur, þ.e. sá sem skrifaði commentið
    private String username;
    // commentið sjálft
    private String comment;
    // auðkenni fyrir auglýsinguna sem commentið tilheyrir
    private int adId;

    /**
     * Smiður með viðföndum
     * @param username notendanafn höfundar
     * @param comment commentið sjálft
     * @param adId auðkenni auglýsingar sem comment tilheyrir
     */
    public Comment(String username, String comment, int adId) {
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
