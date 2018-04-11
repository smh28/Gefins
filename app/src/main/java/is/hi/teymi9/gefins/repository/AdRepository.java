package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;


/**
 * Geymsla fyrir auglýsingar (Ad)
 *
 * @author Einar
 * @date
 * @version 1.0
 */

public class AdRepository {

    // Listi sem geymir allar auglýsingar í kerfinu
    private ArrayList<Ad> adList = new ArrayList<Ad>();
    private ArrayList<Comment> comments = new ArrayList<>();

    //Skilar lista af auglýsingum
    public List<Ad> getAdList() {
        // ef listi er tómur þá skila "dummy" auglýsingu
        //if(adList.isEmpty()) {
        //    Ad ad3 = new Ad("gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");
        //    adList.add(ad3);
        //}
        return adList;
    }

    /**
     * Bætir auglýsingu við listann af auglýsingum
     * @param ad auglýsing
     */
    public void addAd(Ad ad) {
        adList.add(ad);
    }

    /**
     * Stillir listann af auglýsingum
     * @param adList
     */
    public void setAdList(ArrayList<Ad> adList) {
        System.out.println("AdRepository setAdList, adList inniheldur: ");
        for(Ad a: adList) {
            System.out.println(a.getAdName());
        }
        this.adList = adList;
    }

    /**
     * Eyðir auglýsingu úr kerfinu
     * @param ad auglýsing sem skal eyða
     */
    public void deleteAd(Ad ad) {
        adList.remove(ad);
    }

}
