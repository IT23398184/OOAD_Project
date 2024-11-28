package com.victorp.controller;

import com.victorp.model.User;
import com.victorp.service.SecurityService;
import com.victorp.service.UserService;
import com.victorp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    // Encapsulation
    private UserService userService;

    private SecurityService securityService;

    private UserValidator userValidator;

    // Dependency Injection
    @Autowired
    public RegistrationController(UserService userService,SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }
    //Handles the GET request for the login page. Displays error or logout messages if present.
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login"; // Returns the login view
    }


    //Displays the registration form for a generic user.(member 1)
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration"; // Returns the registration form view
    }

   // Displays the registration form for a client. (member 2)
    @GetMapping("/registrationClient")
    public String registrationClient(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationClient"; // Client-specific registration form
    }


    //Displays the registration form for an admin. (member 4)
    @GetMapping("/registrationAdmin")
    public String registrationAdmin(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationAdmin"; // Admin-specific registration form
    }


    //Handles the registration of a trainer. (member 3)
    @GetMapping("/registrationTrainers")
    public String registrationTrainers(Model model) {
        model.addAttribute("userForm", new User());

        return "registrationTrainers"; // Trainer-specific registration form
    }



//Handles the registration of a user. (member 1)
    @PostMapping("/registration")
    public String registrationClient(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            return "registration"; // If validation fails, return to the registration page
        }

        if (!userService.saveClient(userForm)) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "login";  // If username exists, show an error and return to login
        }

        userService.create(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/"; // Redirect to the home page after successful registration
    }

    //Handles the registration of a client.
    @PostMapping("/registrationClient")
    public String addClient(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationClient"; // If validation fails, return to the registration page
        }

        userService.create(userForm);
        userService.saveClient(userForm);

        return "administration"; // Redirect to the administration page after successful registration
    }

    //Handles the registration of a Trainer.
    @PostMapping("/registrationTrainers")
    public String addTrainer(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {

        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationTrainers";  // If validation fails, return to the registration page
        }

        if (!userService.saveTrainer(userForm)) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "registrationTrainers";  // If username exists, show an error and return to the registration page

        }

        userService.create(userForm);
        return "administration";  // Redirect to the administration page after successful registration
    }

    //Handles the registration of a Admin.
    @PostMapping("/registrationAdmin")
    public String addAdmin(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws Exception {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationAdmin";   // If validation fails, return to the registration page
        }
        if (!userService.saveAdmin(userForm)) {
            model.addAttribute("usernameError", "A user with the same name already exists");
            return "registrationAdmin";   // If username exists, show an error and return to the registration page
        }

        userService.create(userForm);
        return "administration";  // Redirect to the administration page after successful registration
    }
    
    


}