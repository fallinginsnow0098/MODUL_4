package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeController {
    public ModelAndView showHome(){
        return new ModelAndView("src/main/resources/templates/index.html");
    }
}
