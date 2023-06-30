package com.mensaappserver.Service.Implementation;

import com.mensaappserver.Models.Mensa;
import com.mensaappserver.Models.Rating;
import com.mensaappserver.Repository.MensaRepository;
import com.mensaappserver.Repository.RatingRepository;
import com.mensaappserver.Service.IMensaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensaService implements IMensaService {
    private final MensaRepository _mensaRepo;
    private final RatingRepository _ratingRepo;
    public MensaService(MensaRepository mensaRepo, RatingRepository ratingRepo) {
        _mensaRepo = mensaRepo;
        _ratingRepo = ratingRepo;
    }

    @Override
    public Mensa getMensa(int id) {
        return _mensaRepo.findById(id);
    }

    @Override
    public List<Mensa> getAllMensas() {
        return _mensaRepo.findAll();
    }

    @Override
    public Mensa addMensa(String name, String location, float rating, List<Rating> ratings) {
        Mensa newMensa = _mensaRepo.findByNameContaining(name);
        if(newMensa == null){
            newMensa = new Mensa(name,location,rating,ratings);
        }else{
            newMensa.setName(name);
            newMensa.setLocation(location);
            //newMensa.setFoodMeals(meals);
        }
        return _mensaRepo.save(newMensa);
    }

    @Override
    public Mensa addReviewToMensa(String mensa, String comment, int rating) {
        Mensa mensaForReview = _mensaRepo.findByNameContaining(mensa);
        if(mensaForReview !=null){
            List<Rating> ratings = mensaForReview.getRatings();
            Rating newRating = new Rating(comment,rating);
            newRating = _ratingRepo.save(newRating);
            ratings.add(newRating);
            mensaForReview.setRatings(ratings);
            return _mensaRepo.save(mensaForReview);
        }
        return null;
    }
}

