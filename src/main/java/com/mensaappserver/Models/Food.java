package com.mensaappserver.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String priceForStudents;
    String priceForNonStudents;
    @Enumerated(value = EnumType.STRING)
    List<Category> categories;
    float rating;
    String offerCategory;
    LocalDate dateModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String mensaName;
    @OneToMany(fetch = FetchType.EAGER)
    List<Rating> ratings;

    public Food(){}

    public Food(String name, String priceForStudents, String priceForNonStudents, List<Category> categories, float rating, List<Rating> ratings, String offerCategory,String mensaName,LocalDate dateModified) {
        this.name = name;
        this.priceForStudents = priceForStudents;
        this.priceForNonStudents = priceForNonStudents;
        this.categories = categories;
        this.rating = rating;
        this.ratings = ratings;
        this.offerCategory = offerCategory;
        this.mensaName = mensaName;
        this.dateModified = dateModified;
    }


    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String getMensaName() {
        return mensaName;
    }

    public void setMensaName(String mensaName) {
        this.mensaName = mensaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceForStudents() {
        return priceForStudents;
    }

    public void setPriceForStudents(String priceForStudents) {
        this.priceForStudents = priceForStudents;
    }

    public String getPriceForNonStudents() {
        return priceForNonStudents;
    }

    public void setPriceForNonStudents(String priceForNonStudents) {
        this.priceForNonStudents = priceForNonStudents;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getOfferCategory() {
        return offerCategory;
    }

    public void setOfferCategory(String offerCategory) {
        this.offerCategory = offerCategory;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

