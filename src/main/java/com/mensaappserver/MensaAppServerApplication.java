package com.mensaappserver;

import com.mensaappserver.Models.Category;
import com.mensaappserver.Models.Food;
import com.mensaappserver.Models.Mensa;
import com.mensaappserver.Models.Rating;
import com.mensaappserver.Service.Implementation.FoodService;
import com.mensaappserver.Service.Implementation.MensaService;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class MensaAppServerApplication implements CommandLineRunner{
    private final MensaService _mensaService;
    private final FoodService _foodService;

    public MensaAppServerApplication(MensaService mensaService, FoodService foodService) {
        _mensaService = mensaService;
        _foodService = foodService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MensaAppServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //scrapeWebsite();
    }

    @Scheduled(cron = "0 0 0 * * *") // Run once every day at midnight
    public void scrapeWebsite() {
        try {
            // Scrape the website using Jsoup
            String url = "https://www.stw-bremen.de/de/mensa";
            Document webPage = Jsoup.connect(url).get();
            List<Mensa> mensas = new ArrayList<>();
            List<String> mensaNames = webPage.select(".menu li").stream().map(Element::text).toList().stream().filter(s -> (s.toLowerCase().contains("mensa") || s.toLowerCase().contains("cafeteria") || s.toLowerCase().contains("café"))&&!s.toLowerCase().contains("mensacard")).distinct().toList();
            mensaNames.toString();
            List<String> mensaUrlNames = Arrays.asList("mensa/uni-mensa","mensa/cafe-central","mensa/nw-1","cafeteria/gw2","cafeteria/grazer-straße","mensa/neustadtswall","mensa/werderstraße","mensa/airport","mensa/bremerhaven","cafeteria/bremerhaven","mensa/interimsmensa-hfk");
            for(int i=0;i<mensaUrlNames.size()-1;i++){
                Document mensaPage = Jsoup.connect("https://www.stw-bremen.de/".concat(mensaUrlNames.get(i))).get();
                List<String> info = mensaPage.select(".field-name-field-description p em").stream().map(Element::text).toList();
                String mensaName = mensaNames.get(i);
                String mensaAddress = info.size() > 0?info.get(0):"";
                float rating = 0;
                List<Rating> ratings = new ArrayList<>();
                List<Food> foodMeals = new ArrayList<>();
                List<Element> foodDescription =mensaPage.select("table.food-category");
                for(Element el:foodDescription){
                    List<String> offerCategory = el.select("th.category-name").stream().map(Element::text).toList();
                    String foodOfferCategory = offerCategory.get(0);

                    System.out.print(foodOfferCategory);
                    List<Element> foodOffer = el.select("tbody tr").stream().toList();
                    for(Element foodRow:foodOffer){
                        String foodName = "";
                        String foodPriceStudents = "";
                        String foodPriceWorkers="";
                        List<Category> foodCategories = new ArrayList<>();
                        float foodRating = 0;
                        List<Rating> foodRatings = new ArrayList<>();
                        String classStr = foodRow.attr("class");
                        if(classStr.toLowerCase().contains("rindfleisch")){
                            foodCategories.add(Category.Rindfleisch);
                        }
                        if(classStr.toLowerCase().contains("artgerechte")){
                            foodCategories.add(Category.AT);
                        }
                        if(classStr.toLowerCase().contains("fisch")){
                            foodCategories.add(Category.Fisch);
                        }
                        if(classStr.toLowerCase().contains("geflügel")){
                            foodCategories.add(Category.Geflügel);
                        }
                        if(classStr.toLowerCase().contains("lamm")){
                            foodCategories.add(Category.Lamm);
                        }
                        if(classStr.toLowerCase().contains("schweinefleisch")){
                            foodCategories.add(Category.Schweinefleisch);
                        }
                        if(classStr.toLowerCase().contains("vegan")){
                            foodCategories.add(Category.Vegan);
                        }
                        if(classStr.toLowerCase().contains("vegetarisch")){
                            foodCategories.add(Category.Vegetarisch);
                        }
                        if(classStr.toLowerCase().contains("wild")){
                            foodCategories.add(Category.Wild);
                        }
                        foodName = foodRow.select("td.field.field-name-field-description").stream().map(Element::text).toList().get(0);
                        try {
                            foodPriceStudents = (foodRow.select("td.field.field-name-field-price-students").stream().map(Element::text).toList().get(0));
                            foodPriceWorkers = (foodRow.select("td.field.field-name-field-price-employees").stream().map(Element::text).toList().get(0));
                        }catch (Exception ex){
                            foodPriceStudents= "";
                            foodPriceWorkers = "";
                        }

                        System.out.println(foodName);
                        //Food newFood = new Food(foodName,foodPriceStudents,foodPriceWorkers,foodCategories,rating,ratings,foodOfferCategory,mensaName);
                        Food newFood = _foodService.addFood(foodName,foodPriceStudents,foodPriceWorkers,foodCategories,rating,ratings,foodOfferCategory,mensaName);
                        foodMeals.add(newFood);
                    }
                }
                System.out.print(mensaAddress);
                //Mensa mensa = new Mensa(mensaName,mensaAddress,rating,ratings,foodMeals);
                _mensaService.addMensa(mensaName,mensaAddress,rating,ratings);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

