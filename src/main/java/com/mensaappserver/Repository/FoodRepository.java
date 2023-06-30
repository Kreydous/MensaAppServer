package com.mensaappserver.Repository;

import com.mensaappserver.Models.Category;
import com.mensaappserver.Models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    public Food getFoodById(int id);
    public List<Food> getAllByNameContaining(String Name);
    public List<Food> getAllByCategoriesIn(List<Category> categories);

    public Food findByNameAndMensaName(String Name,String mensaName);

    public List<Food> getAllByMensaNameAndDateModified(String mensaName, LocalDate dateModified);

}
