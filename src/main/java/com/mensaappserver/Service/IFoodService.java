package com.mensaappserver.Service;

import com.mensaappserver.Models.Category;
import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Rating;

import java.util.List;

public interface IFoodService {
    public List<Food> getAllPlates();
    public Food getMeal(int id);
    public List<Food> getAllByName(String name);
    public List<Food> getAllByCategory(List<Category> categories);
    public Food addFood(String name, String PriceForStudents, String PriceForNonStudents, List<Category> Categories, float Rating, List<Rating> ratings,String offerCategory,String mensaName);
}
