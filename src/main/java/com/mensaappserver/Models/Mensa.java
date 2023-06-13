package com.mensaappserver.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String name;
    String location;
    float rating;

    @OneToMany(fetch = FetchType.EAGER)
    List<Rating> ratings;
    /*@OneToMany
    List<Food> foodMeals;*/

    public Mensa(){}

    public Mensa(String name, String location, float rating, List<Rating> ratings) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.ratings = ratings;
        //this.foodMeals = foodMeals;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
