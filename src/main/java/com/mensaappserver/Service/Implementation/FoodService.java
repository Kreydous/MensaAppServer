package com.mensaappserver.Service.Implementation;

import com.mensaappserver.Models.Category;
import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Rating;
import com.mensaappserver.Repository.FoodRepository;
import com.mensaappserver.Service.IFoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {
    private final FoodRepository _foodRepo;

    public FoodService(FoodRepository foodRepo) {
        _foodRepo = foodRepo;
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
        Food newFood = _foodRepo.findByNameAndAndMensaName(name,mensaName);
        if(newFood == null){
            newFood = new Food(name,PriceForStudents,PriceForNonStudents,Categories, Rating,Ratings,offerCategory,mensaName);
        }else{
            newFood.setName(name);
            newFood.setPriceForStudents(PriceForStudents);
            newFood.setPriceForNonStudents(PriceForNonStudents);
            newFood.setCategories(Categories);
            newFood.setOfferCategory(offerCategory);
        }
        return _foodRepo.save(newFood);
    }
}
