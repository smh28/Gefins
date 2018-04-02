package is.hi.teymi9.gefins.repository;

import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.model.Comment;


/**
 * Geymsla fyrir auglýsingar (Ad)
 *
 * @author Einar
 * @version 1.0
 */

public class AdRepository {


    //private ArrayList<Ad> AdList = new ArrayList<>();
    //private List<Ad> AdList;
    // Listi sem geymir alla notendur í kerfinu
    private ArrayList<Ad> adList = new ArrayList<Ad>();
    private Comment comment = new Comment();
    private ArrayList<Comment> comments = new ArrayList<>();


    //Skilar lista af auglýsingum
    public List<Ad> getAdList() {
        if(adList.isEmpty()) {
            //Ad ad = new Ad("gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");
            //Ad ad2 = new Ad("óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments, "201");
            Ad ad3 = new Ad("gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");

            //adList.add(ad);
            //adList.add(ad2);
            adList.add(ad3);

        }
        return adList;
    }

    public void addAd(Ad ad) {
        adList.add(ad);
    }

    public void setAdList(ArrayList<Ad> adList) {
        System.out.println("AdRepository setAdList, adList inniheldur: ");
        for(Ad a: adList) {
            System.out.println(a.getAdName());
        }
        this.adList = adList;

    }
}
