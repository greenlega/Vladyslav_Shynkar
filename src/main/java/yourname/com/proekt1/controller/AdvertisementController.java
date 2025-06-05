package yourname.com.proekt1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import yourname.com.proekt1.model.Advertisement;
import yourname.com.proekt1.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementController(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }



    @GetMapping("/")
    public String listAdvertisements(Model model) {
        model.addAttribute("advertisements", advertisementRepository.findAll());
        return "advertisements";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("advertisement", new Advertisement());
        return "add-advertisement";
    }

    @GetMapping("/advertisements/{id}")
    public String showAdvertisementDetails(@PathVariable Long id, Model model) {

        Advertisement advertisement = advertisementRepository.findById(id).orElse(null);

        if (advertisement == null) {

            return "redirect:/";
        }

        model.addAttribute("advertisement", advertisement);

        model.addAttribute("showContact", false);

        return "advertisement-details";
    }




    @PostMapping("/add")
    public String addAdvertisement(@ModelAttribute Advertisement advertisement) {
        advertisementRepository.save(advertisement);
        return "redirect:/";
    }
}