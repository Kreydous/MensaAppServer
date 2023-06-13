package com.mensaappserver.Service;

import com.mensaappserver.Models.Rating;

import java.util.List;

public interface IRatingService {
    public Rating getRatingById(int id);
    public List<Rating> getAllRatings();
}
