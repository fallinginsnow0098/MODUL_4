package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    @GetMapping("/transform")
    public String showFormConvert(){
        return "money";
    }
    @PostMapping("/transform")
    public ModelAndView convert(@RequestParam("usd") String usd, @RequestParam("rate") String rate){
        ModelAndView modelAndView = new ModelAndView("money");
        double result = Double.parseDouble(usd) * Double.parseDouble(rate);
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
