package is.hi.teymi9.gefins.model;

import java.util.UUID;

/**
 * Módel klasi fyrir athugasemdir (Comment)
 *
 * @author Ólöf Fríða
 * @date March 2018
 * @version 1.0
 */


public class Comment {

    // unique auðkenni fyrir comment
    private UUID id = UUID.randomUUID();
    // höfundur, þ.e. sá sem skrifaði commentið
    private String username;
    // commentið sjálft
    private String comment;
    // auglýsinguna sem commentið tilheyrir
    private Ad ad;

    /**
     * Smiður með viðföndum
     * @param username notendanafn höfundar
     * @param comment commentið sjálft
     * @param ad auglýsing sem comment tilheyrir
     */
    public Comment(String username, String comment, Ad ad) {
        this.username = username;
        this.comment = comment;
        this.ad = ad;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Ad getAd() {
        return ad;
    }
}
