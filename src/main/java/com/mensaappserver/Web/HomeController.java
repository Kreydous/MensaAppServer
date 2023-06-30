package com.mensaappserver.Web;

import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Mensa;
import com.mensaappserver.Service.IFoodService;
import com.mensaappserver.Service.IMensaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private final IFoodService _foodService;
    private final IMensaService _mensaService;

    public HomeController(IFoodService foodService, IMensaService mensaService) {
        _foodService = foodService;
        _mensaService = mensaService;
    }

    @GetMapping(path = "/mensas")
    public List<Mensa> getAllMensas() {

        return _mensaService.getAllMensas();
    }

    @PostMapping(path = "/plate")
    public Food getPlate(@RequestParam int plateId){
        return _foodService.getMeal(plateId);
    }

    @GetMapping(path = "/plates")
    public List<Food> getPlates(){
       return _foodService.getAllPlates();
    }

    @PostMapping(path = "/platesFromMensa")
    public List<Food> getPlate(@RequestParam String mensaName){
        return _foodService.getAllFromMensa(mensaName);
    }
}