package is.hi.teymi9.gefins.service;

import java.util.ArrayList;
import java.util.List;

import is.hi.teymi9.gefins.model.Ad;
import is.hi.teymi9.gefins.repository.AdRepository;

/**
 * Created by Kristín María on 26.2.2018.
 */

public class AdService {

    AdRepository adRepository = new AdRepository();


    private List<Ad> adList;

    public List<Ad> getAllAds() {
        adList = adRepository.getAdList();
        System.out.println("getAllAds adList í AdService: " + adList.toString());
        return adList;
    }

    public String addAd(Ad u, boolean validate) {
        if(validate) {
            // ganga úr skugga um að user info sé valid og user sé ekki þegar til
        }
        adRepository.addAd(u);
        return "Skráning auglýsingar tókst!"; // eitthvað vesen að nálgast strings.xml héðan...
    }



}
