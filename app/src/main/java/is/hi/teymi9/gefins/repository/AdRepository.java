package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;

import is.hi.teymi9.gefins.model.GiveAd;
import is.hi.teymi9.gefins.model.ReceiveAd;

/**
 * Geymsla fyrir auglýsingar (Ad)
 *
 * @author Einar
 * @version 1.0
 */

public class AdRepository {

    // Listi af 'gefa' auglýsingum
    private ArrayList<GiveAd> giveAdList = new ArrayList<>();
    // Listi af 'þiggja' auglýsingum
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
