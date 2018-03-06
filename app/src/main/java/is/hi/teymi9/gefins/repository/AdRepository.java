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
        System.out.println("í getAdList í AdRepository áður en listinn er myndaður úr harðkóðuðu: " + AdList);
        //Sýna í system.out.print hvað listinn inniheldur
        int countAds = AdList.size();
        System.out.println("allAds í DisplayAdsFragment stærð: " + countAds);

        String[] adName = new String[countAds];
        String[] adGiveOrTake = new String[countAds];
        String[] adDescription = new String[countAds];

        int i = 0;
        for(Ad a: AdList) {
            String name = a.getAdName();
            String giveOrTake = a.getGiveOrTake();
            String description = a.getAdDescription();
            adName[i] = name;
            adGiveOrTake[i] = giveOrTake;
            adDescription[i] = description;
            System.out.println("name augl: " + name);
            System.out.println("giveorTake: " + giveOrTake);
            System.out.println("description augl: " + description);
            System.out.println(i);
            i++;
        }

        if(AdList.isEmpty()) {
            Ad ad = new Ad(1, "gefins", "Mjúkur sófi", "Húsgögn", "Sófi", "Svartur", "Mjúkur 3ja sæta sófi úr microsoft efni", "olla", comments);
            Ad ad2 = new Ad(2, "óska eftir", "Eldhúsborð", "Húsgögn", "Borð", "Hvítur", "4 manna eldhúsborð úr Ikea", "sandra", comments);
            Ad ad3 = new Ad(3, "gefins", "Leðurjakki", "Fatnaður", "Yfirhöfn", "Svartur", "Stuttur leðurjakki í stærð 38", "sandra", comments);

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
