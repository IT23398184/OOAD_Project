package com.victorp.controller;


// Importing necessary Spring framework annotations and classes.
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";// Returns the name of the view to be rendered (index.jsp, index.html, etc.).
    }

    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public String classes() {
        return "classes";// Returns the name of the view to be rendered (classes.jsp, classes.html, etc.).
    }

}
