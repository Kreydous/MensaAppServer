package com.mensaappserver.Service;

import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Mensa;
import com.mensaappserver.Models.Rating;

import java.util.List;

public interface IMensaService {
    public Mensa getMensa(int id);
    public List<Mensa> getAllMensas();
    public Mensa addMensa(String name, String location, float rating, List<Rating> ratings);
    public Mensa addReviewToMensa(String mensa,String comment,int rating);
}
