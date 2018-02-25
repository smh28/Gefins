package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.GiveAd;
import is.hi.teymi9.gefins.model.ReceiveAd;

/**
 * Created by Einar on 25.2.2018.
 */

public class AdRepository {

    private ArrayList<GiveAd> giveAdList = new ArrayList<>();
    private ArrayList<ReceiveAd> receiveAdList = new ArrayList<>();

    public ArrayList<GiveAd> getGiveAdList() {
        return giveAdList;
    }

    public ArrayList<ReceiveAd> getReceiveAdList() {
        return receiveAdList;
    }

    public void addGiveAd(GiveAd ad) {
        giveAdList.add(ad);
    }

    public void addReceiveAd(ReceiveAd ad) {
        receiveAdList.add(ad);
    }
}
