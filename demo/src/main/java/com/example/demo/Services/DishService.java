package Services;

import entity.Dish;
import repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired private DishRepository dishRepository;

    public List<Dish> getAvailableDishes(){ return dishRepository.findByAvailabilityTrue(); }
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }
    public Dish save(Dish d) {
        return dishRepository.save(d);
    }
}
