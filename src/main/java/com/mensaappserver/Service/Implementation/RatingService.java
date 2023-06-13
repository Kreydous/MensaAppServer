package com.mensaappserver.Service.Implementation;

import com.mensaappserver.Models.Rating;
import com.mensaappserver.Repository.RatingRepository;
import com.mensaappserver.Service.IRatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements IRatingService {
    private final RatingRepository _ratingRepo;

    public RatingService(RatingRepository ratingRepo) {
        _ratingRepo = ratingRepo;
    }

    @Override
    public Rating getRatingById(int id) {
        return _ratingRepo.findById(id);
    }

    @Override
    public List<Rating> getAllRatings() {
        return _ratingRepo.findAll();
    }
}
