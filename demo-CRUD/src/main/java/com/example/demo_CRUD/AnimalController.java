import com.example.demo_CRUD.Animal;
import com.example.demo_CRUD.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;

    // Constructor injection of AnimalService
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Endpoint to get details of a single animal by ID
    @GetMapping("/animals/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        // Fetch the existing animal by ID and add it to the model
        Animal animal = animalService.getAnimalById(id);
        model.addAttribute("animal", animal);
        return "animal-update"; // Refers to animal-update.html
    }
    @PostMapping("/animals/update")
    public String updateAnimal(@ModelAttribute Animal animal) {
        // Update the existing animal using the service
        animalService.updateAnimal(animal);
        // Redirect to the details page of the updated animal
        return "redirect:/animals/" + animal.getId();
    }
    @GetMapping("/animals/delete/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        // Call the service to delete the animal by ID
        animalService.deleteAnimal(id);
        // Redirect to the list of animals after deletion
        return "redirect:/animals";
    }


}
