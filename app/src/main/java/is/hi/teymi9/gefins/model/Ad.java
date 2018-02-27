package is.hi.teymi9.gefins.model;


import java.util.ArrayList;
import is.hi.teymi9.gefins.model.Comment;

/**
 * Created by Kristín on 25.2.2018.
 */


public class Ad {

    private int adID;
    private String giveOrTake;
    private String adName;
    private String adType;
    private String adTypeOfType;
    private String adColor;
    private String adDescription;
    private String adUsername;
    private ArrayList<Comment> adComments;

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
