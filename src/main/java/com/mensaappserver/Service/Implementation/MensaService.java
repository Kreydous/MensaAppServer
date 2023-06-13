package com.mensaappserver.Service.Implementation;

import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Mensa;
import com.mensaappserver.Models.Rating;
import com.mensaappserver.Repository.MensaRepository;
import com.mensaappserver.Service.IMensaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensaService implements IMensaService {
    private final MensaRepository _mensaRepo;

    public MensaService(MensaRepository mensaRepo) {
        _mensaRepo = mensaRepo;
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
}

