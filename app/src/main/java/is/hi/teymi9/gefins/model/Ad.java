package is.hi.teymi9.gefins.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import is.hi.teymi9.gefins.model.Comment;

/**
 * Módel klasi fyrir auglýsingu (Ad)
 *
 * @author Kristín María
 * @date March 2018
 * @version 1.0
 */


public class Ad {

    // unique auðkenni fyrir ad
    private UUID id = UUID.randomUUID();
    // er auglýsingin að gefa eða þiggja?
    private String giveOrTake;
    // heiti
    private String adName;
    // tegund
    private String adType;
    // undirtegund
    private String adTypeOfType;
    // litur
    private String adColor;
    // lýsing
    private String adDescription;
    // höfundur auglýsingar
    private String adUsername;
    // athugasemdir sem gerðar hafa verið við auglýsinguna
    private List<Comment> adComments;
    // póstnúmer staðsetning hlutar
    private String adLocation;

    /**
     * Smiður með viðföngum
     * @param giveOrTake gefa eða þiggja?
     * @param adName heiti
     * @param adType tegund
     * @param adTypeOfType undirtegund
     * @param adColor litur
     * @param adDescription lýsing
     * @param adUsername höfundur
     * @param adComments athugasemdir
     * @param adLocation staðsetning
     */
    public Ad(String giveOrTake, String adName, String adType, String adTypeOfType,
              String adColor, String adDescription, String adUsername, ArrayList<Comment> adComments, String adLocation) {
        this.giveOrTake = giveOrTake;
        this.adName = adName;
        this.adType = adType;
        this.adTypeOfType = adTypeOfType;
        this.adColor = adColor;
        this.adDescription = adDescription;
        this.adUsername = adUsername;
        this.adComments = adComments;
        this.adLocation = adLocation;
    }

    /**
     * Tómur smiður
     */
    public Ad() {
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getGiveOrTake() { return giveOrTake; }

    public void setGiveOrTake(String giveOrTake) { this.giveOrTake = giveOrTake; }

    public String getAdName() { return adName; }

    public void setAdName(String adName) { this.adName = adName; }

    public String getAdType() { return adType; }

    public void setAdType(String adType) { this.adType = adType; }

    public String getAdTypeOfType() { return adTypeOfType; }

    public void setAdTypeOfType(String adTypeOfType) { this.adTypeOfType = adTypeOfType; }

    public String getAdColor() { return adColor; }

    public void setAdColor(String adColor) { this.adColor = adColor; }

    public String getAdDescription() { return adDescription; }

    public void setAdDescription(String adDescription) { this.adDescription = adDescription; }

    public String getAdUsername() { return adUsername; }

    public void setAdUsername(String adUsername) { this.adUsername = adUsername; }

    public List<Comment> getAdComments() {
        return adComments;
    }

    public void setAdComments(ArrayList<Comment> adComments) {
        this.adComments = adComments;
    }

    public String getAdLocation() { return adLocation; }

    public void setAdLocation(String adLocation) { this.adLocation = adLocation; }
}
