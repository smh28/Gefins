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
    private ArrayList<Ad> AdList = new ArrayList<Ad>();
    private Comment comment = new Comment();
    private ArrayList<Comment> comments = new ArrayList<Comment>();


    //Skilar lista af auglýsingum
    public List<Ad> getAdList() {
        if(AdList.isEmpty()) {
            Ad ad = new Ad(1, "gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments, "105");
            Ad ad2 = new Ad(2, "óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments, "201");
            Ad ad3 = new Ad(3, "gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments, "123");

            AdList.add(ad);
            AdList.add(ad2);
            AdList.add(ad3);

        }
        System.out.println("AdList í AdRepository inniheldur: " + AdList);
        return AdList;
    }

    public void addAd(Ad ad) {
        AdList.add(ad);
    }



}
