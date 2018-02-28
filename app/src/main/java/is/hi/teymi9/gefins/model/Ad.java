package is.hi.teymi9.gefins.model;


import java.util.ArrayList;
import is.hi.teymi9.gefins.model.Comment;

/**
 * Módel klasi fyrir auglýsingu (Ad)
 *
 * @author Kristín María
 * @version 1.0
 */


public class Ad {

    // unique auðkenni fyrir ad
    private int adID;
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
    private ArrayList<Comment> adComments;

    /**
     * Smiður með viðföngum
     * @param adID auðkenni
     * @param giveOrTake gefa eða þiggja?
     * @param adName heiti
     * @param adType tegund
     * @param adTypeOfType undirtegund
     * @param adColor litur
     * @param adDescription lýsing
     * @param adUsername höfundur
     * @param adComments athugasemdir
     */
    public Ad(int adID, String giveOrTake, String adName, String adType, String adTypeOfType,
              String adColor, String adDescription, String adUsername, ArrayList<Comment> adComments) {
        this.adID = adID;
        this.giveOrTake = giveOrTake;
        this.adName = adName;
        this.adType = adType;
        this.adTypeOfType = adTypeOfType;
        this.adColor = adColor;
        this.adDescription = adDescription;
        this.adUsername = adUsername;
        this.adComments = adComments;
    }

    /**
     * Tómur smiður
     */
    public Ad() {
    }

    public int getAdID() { return adID; }

    public void setAdID(int adID) { this.adID = adID; }

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

    public ArrayList<Comment> getAdComments() {
        return adComments;
    }

    public void setAdComments(ArrayList<Comment> adComments) {
        this.adComments = adComments;
    }
}
