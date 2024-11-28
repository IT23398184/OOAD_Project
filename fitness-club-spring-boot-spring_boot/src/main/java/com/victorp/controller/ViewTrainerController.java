package com.victorp.controller;

import com.victorp.model.User;
import com.victorp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewTrainerController {

    // Encapsulation
    private TrainerService trainerService;

    // Dependency Injection
    @Autowired
    public ViewTrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    //Handles GET request to view trainers
    //Abstraction:
    @GetMapping("/trainerView")
    public String viewTrainer(Model model, String keyword) throws Exception{

        if(keyword != null){
            model.addAttribute("allTrainers", trainerService.findTrainerByKeyword(keyword));
        }else {
            model.addAttribute("allTrainers", trainerService.getAll());
        }


        return "trainerView";

    }

    //request to delete a trainer by their ID
    @PostMapping("/trainerView")
    public String deleteTrainer(@RequestParam Long idTrainer)throws Exception{

        trainerService.delete(idTrainer);

        return "redirect:/";

    }
    //update a trainer's information.
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userForm") User user) throws Exception {
        trainerService.update(user);
        return "redirect:/trainerView"; // Redirect to the user list page
    }
    

}
