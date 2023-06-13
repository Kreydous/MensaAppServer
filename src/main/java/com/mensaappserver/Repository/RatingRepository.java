package com.mensaappserver.Repository;

import com.mensaappserver.Models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    public Rating findById(int id);
}

