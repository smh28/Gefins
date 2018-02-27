package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.Ad;

/**
 * Created by Einar on 25.2.2018.
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
