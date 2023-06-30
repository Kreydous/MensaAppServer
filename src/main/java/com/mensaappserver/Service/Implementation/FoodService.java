package com.mensaappserver.Service.Implementation;

import com.mensaappserver.Models.Category;
import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Rating;
import com.mensaappserver.Repository.FoodRepository;
import com.mensaappserver.Repository.RatingRepository;
import com.mensaappserver.Service.IFoodService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService implements IFoodService {
    private final FoodRepository _foodRepo;
    private final RatingRepository _ratingRepo;
    public FoodService(FoodRepository foodRepo, RatingRepository ratingRepo) {
        _foodRepo = foodRepo;
        _ratingRepo = ratingRepo;
    }

    @Override
    public List<Food> getAllPlates() {
        return _foodRepo.findAll();
    }

    @Override
    public Food getMeal(int id) {
        return _foodRepo.getFoodById(id);
    }

    @Override
    public List<Food> getAllByName(String name) {
        return _foodRepo.getAllByNameContaining(name);
    }

    @Override
    public List<Food> getAllByCategory(List<Category> categories) {
        return _foodRepo.getAllByCategoriesIn(categories);
    }

    @Override
    public Food addFood(String name, String PriceForStudents, String PriceForNonStudents, List<Category> Categories, float Rating, List<Rating> Ratings,String offerCategory,String mensaName) {
        Food newFood = _foodRepo.findByNameAndMensaName(name,mensaName);
        if(newFood == null){
            newFood = new Food(name,PriceForStudents,PriceForNonStudents,Categories, Rating,Ratings,offerCategory,mensaName, LocalDate.now());
        }else{
            newFood.setName(name);
            newFood.setPriceForStudents(PriceForStudents);
            newFood.setPriceForNonStudents(PriceForNonStudents);
            newFood.setCategories(Categories);
            newFood.setOfferCategory(offerCategory);
            newFood.setDateModified(LocalDate.now());
        }
        return _foodRepo.save(newFood);
    }

    @Override
    public List<Food> getAllFromMensa(String mensaName) {
        return _foodRepo.getAllByMensaNameAndDateModified(mensaName,LocalDate.now());
    }

    @Override
    public Food addReviewForFood(String foodName,String mensaName, String comment, int rating) {
        Food foodForRating = _foodRepo.findByNameAndMensaName(foodName,mensaName);
        if(foodForRating !=null){
            List<Rating> ratings = foodForRating.getRatings();
            Rating newRating = new Rating(comment,rating);
            newRating = _ratingRepo.save(newRating);
            ratings.add(newRating);
            foodForRating.setRatings(ratings);
            _foodRepo.save(foodForRating);
        }
        return null;
    }
}
