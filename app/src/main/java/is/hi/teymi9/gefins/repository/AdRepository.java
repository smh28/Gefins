package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.Ad;

/**
 * Geymsla fyrir auglýsingar (Ad)
 *
 * @author Einar
 * @version 1.0
 */

public class AdRepository {

    private ArrayList<Ad> AdList = new ArrayList<>();

    public ArrayList<Ad> getAdList() {
        return AdList;
    }

    public void addAd(Ad ad) {
        AdList.add(ad);
    }

}
